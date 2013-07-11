package org.idris.rts;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Backend primitives needed for Prelude
 *
 * @author Jan Bessai
 */
@SuppressWarnings("unchecked")
public class Prelude {

    private static final Map<Thread, List<String>> args = new HashMap<>();
    private static final Semaphore messageMutex = new Semaphore(1);
    private static final Map<Thread, BlockingQueue<Object>> messages = new HashMap<>();
    private static final Set<Object> feofFiles = new HashSet<>();

    /**
     * Initialize the command line arguments for the given thread.
     *
     * @param t initialize command line arguments for this thread
     * @param args arguments to initialize
     */
    public static synchronized void idris_initArgs(Thread t, String[] args) {
        Prelude.args.put(t, Arrays.asList(args));
    }

    /**
     * Get the number of command line arguments for the given thread.
     *
     * @param thread get the number of arguments for this thread
     * @return number of arguments
     */
    public static synchronized int idris_numArgs(Object thread) {
        List<String> argsForThread = Prelude.args.get((Thread) thread);
        return (argsForThread == null ? 0 : argsForThread.size());
    }

    /**
     * Get the num-th commandline argument.
     *
     * @param thread get the commandline argument for the given thread
     * @param num argument position
     * @return argument previously initialized by {@code idris_initArgs}
     */
    public static synchronized String idris_getArg(Object thread, int num) {
        List<String> argsForThread = Prelude.args.get((Thread) thread);
        return (argsForThread == null ? null : argsForThread.get(num));
    }

    /**
     * Query the system environment for a value.
     *
     * @param x query key
     * @return value stored in the system environment for the given key
     */
    public static String getenv(String x) {
        return System.getenv(x);
    }

    /**
     * Exit the the current program.
     *
     * @param exitCode program result
     */
    public static void exit(int exitCode) {
        System.exit(exitCode);
    }

    /**
     * Sleep for some time.
     *
     * @param microsecs number of microseconds to sleep
     */
    public static void usleep(int microsecs) {
        try {
            Thread.sleep(microsecs / 1000, microsecs % 1000);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Pass a message to another thread. Block until message queue access is
     * possible.
     *
     * @param from source thread
     * @param dest target thread
     * @param message object containing the message
     * @throws InterruptedException if waiting for the message queue failed
     */
    public static void idris_sendMessage(Object from, Object dest, Object message) throws InterruptedException {
        messageMutex.acquire();
        BlockingQueue<Object> messagesForTarget = messages.get((Thread) dest);
        if (messagesForTarget == null) {
            messagesForTarget = new LinkedBlockingQueue<>();
            messages.put((Thread) dest, messagesForTarget);
        }
        messagesForTarget.put(message);
        messageMutex.release();
    }

    /**
     * Check if a message has arrived. Block until message queue access is
     * possible.
     *
     * @param dest thread to check messages for
     * @return number of messages which arrived
     * @throws InterruptedException if waiting for the message queue failed
     */
    public static int idris_checkMessage(Object dest) throws InterruptedException {
        messageMutex.acquire();
        BlockingQueue<Object> messagesForTarget = messages.get((Thread) dest);
        messageMutex.release();
        return (messagesForTarget == null ? 0 : messagesForTarget.size());
    }

    /**
     * Receive a message. Block until a message has arrived.
     * 
     * @param dest thread to receive messages for
     * @return the received message
     * @throws InterruptedException if waiting for the message failed
     */
    public static Object idris_recvMessage(Object dest) throws InterruptedException {
        messageMutex.acquire();
        BlockingQueue<Object> messagesForTarget = messages.get((Thread) dest);
        if (messagesForTarget == null) {
            messagesForTarget = new LinkedBlockingQueue();
            messages.put((Thread) dest, messagesForTarget);
        }
        messageMutex.release();
        return messagesForTarget.take();
    }
    
    /**
     * Output a string to {@code System.out}
     * 
     * @param str message to write to stdout
     */
    public static void putStr(String str) {
        System.out.print(str);
    }

    /**
     * Output a character to {@code System.out}
     * 
     * @param c char to write to stdout
     */
    public static void putchar(char c) {
        System.out.print(c);
    }

    /**
     * Receive a char from {@code System.in}
     * 
     * @return the character read from stdin or -1 if non was available
     */
    public static int getchar() {
        try {
            return (char) System.in.read();
        } catch (IOException ex) {
            return -1;
        }
    }

    /**
     * Open a file.
     * 
     * @param name filename
     * @param privs privileges as described in man fopen(3)
     * @return handle to the newly opened file or null if an error occurred
     */
    public static SeekableByteChannel fileOpen(String name, String privs) {
        try {
            OpenOption[] options;
            switch (privs) {
                case "r":
                    options = new StandardOpenOption[]{StandardOpenOption.READ};
                    break;
                case "r+":
                    options = new StandardOpenOption[]{StandardOpenOption.READ,
                        StandardOpenOption.WRITE};
                    break;
                case "w":
                    options = new StandardOpenOption[]{StandardOpenOption.WRITE,
                        StandardOpenOption.CREATE};
                    break;
                case "w+":
                    options = new StandardOpenOption[]{StandardOpenOption.READ,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.CREATE};
                    break;
                case "a":
                    options = new StandardOpenOption[]{StandardOpenOption.WRITE,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND};
                    break;
                case "a+":
                    options = new StandardOpenOption[]{StandardOpenOption.READ,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND};
                    break;
                default:
                    options = new StandardOpenOption[]{};
                    break;
            }
            return Files.newByteChannel(new File(name).toPath(), options);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Close a file handle
     * @param file handle to close
     * @throws IOException if an error occurs during close IO
     */
    public static synchronized void fileClose(Object file) throws IOException {
        ((Closeable) file).close();
        feofFiles.remove(file);
    }

    /**
     * Write a string to a file handle.
     * 
     * @param file handle to write to. Has to be a PrintStream or a SeekableByteChannel.
     * @param string output
     * @throws IOException if an error occurs during write IO
     */
    public static void fputStr(Object file, String string) throws IOException {
        if (file instanceof PrintStream) {
            ((PrintStream) file).print(string);
        } else if (file instanceof SeekableByteChannel) {
            ((SeekableByteChannel) file).write(ByteBuffer.wrap(string.getBytes()));
        }
    }

    /**
     * Read a line from a file.
     * Chooses the {@code line.seperator} System property as delimiter.
     * 
     * @param file handle to read from
     * @return next line from the handle
     * @throws IOException if an error occurs during read IO
     */
    public static synchronized String idris_readStr(Object file) throws IOException {
        ReadableByteChannel channel = file instanceof InputStream
                ? Channels.newChannel((InputStream) file)
                : (ReadableByteChannel) file;
        ByteBuffer buf = ByteBuffer.allocate(1);
        StringBuilder resultBuilder = new StringBuilder("");
        String delimiter = System.getProperty("line.separator");
        int read = 0;
        do {
            buf.rewind();
            read = channel.read(buf);
            if (read > 0) {
                resultBuilder.append(new String(buf.array()));
                if (resultBuilder.lastIndexOf(delimiter) > -1) {
                    return resultBuilder.toString();
                }
            }
            if (read < 0) {
                feofFiles.add(file);
            }
        } while (read >= 0);
        return resultBuilder.toString();
    }
    
    /**
     * Test if a file has reached its end during {@code idris_readStr} operations.
     * 
     * @param file handle to test
     * @return 1 if the file has ended, 0 otherwise
     */
    public static int fileEOF(Object file) {
        return (feofFiles.contains(file) ? 1 : 0);
    }

    /**
     * Check if an object is {@code null}
     * 
     * @param o object to check
     * @return 1 if the object is {@code null} 0 otherwise
     */
    public static int isNull(Object o) {
        return (o == null ? 1 : 0);
    }

    /**
     * Allocate raw memory.
     * 
     * @param size number of bytes to allocate
     * @return a {@code ByteBuffer} capable of storing the requested amount of bytes
     */
    public static Object malloc(int size) {
        return ByteBuffer.allocate(size);
    }

    /**
     * Initialize a memory location.
     * 
     * @param buf {@code ByteBuffer} to initialize
     * @param offset bytes to skip at the beginning of the buffer
     * @param c byte value to use for initialization
     * @param size number of bytes to initialize
     */
    public static void idris_memset(Object buf, int offset, Object c, int size) {
        ByteBuffer buffer = (ByteBuffer) buf;
        buffer.rewind();
        buffer.position(offset);
        byte init[] = new byte[size];
        Arrays.fill(init, (Byte) c);
        buffer.put(init, offset, size);
        buffer.rewind();
    }

    /**
     * Release a memory buffer.
     * 
     * @param buf buffer to release
     */
    public static void free(Object buf) {
        buf = null;
    }

    /**
     * Read a byte from a buffer.
     * 
     * @param buf {@code ByteBuffer} to read from
     * @param offset address to read
     * @return the byte read
     */
    public static byte idris_peek(Object buf, int offset) {
        ByteBuffer buffer = (ByteBuffer) buf;
        return buffer.get(offset);
    }

    /**
     * Write a byte to a buffer.
     * 
     * @param buf {@code ByteBuffer} to write to
     * @param offset address to write at
     * @param data byte to write out
     */
    public static void idris_poke(Object buf, int offset, Object data) {
        ByteBuffer buffer = (ByteBuffer) buf;
        buffer.put(offset, (Byte) data);
    }

    /**
     * Copy data in buffers.
     * 
     * @param dstBuf {@code ByteBuffer} to copy data to
     * @param srcBuf {@code ByteBuffer} to copy data from
     * @param dstOffset bytes to skip from the destination start
     * @param srcOffset bytes to skip from the source start
     * @param size number of bytes to move
     */
    public static void idris_memmove(Object dstBuf, Object srcBuf, int dstOffset, int srcOffset, int size) {
        ByteBuffer dst = (ByteBuffer) dstBuf;
        ByteBuffer src = (ByteBuffer) srcBuf;
        byte[] srcData = new byte[size];
        src.rewind();
        src.position(srcOffset);
        src.get(srcData, 0, size);
        src.rewind();
        dst.rewind();
        dst.position(dstOffset);
        dst.put(srcData, 0, size);
        dst.rewind();
    }
}
