package org.idris.rts;

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
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
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

    private static final List<String> args = new ArrayList<>();
    private static final Semaphore messageMutex = new Semaphore(1);
    private static final Map<Thread, BlockingQueue<Object>> messages = new HashMap<>();
    private static final Set<Object> feofFiles = new HashSet<>();
    private static final Set<Object> errorFiles = new HashSet<>();
    private static final Map<Object, Process> processHandles = new HashMap<>();

    /**
     * Initialize the command line arguments.
     *
     * @param args arguments to initialize
     */
    public static void idris_initArgs(String[] args) {
        Prelude.args.add(System.getProperty("sun.java.command"));
        Prelude.args.addAll(Arrays.asList(args));
    }
    
    /**
     * Get the number of command line arguments.
     * 
     * @return number of arguments
     */
    public static int idris_numArgs() {
        return args.size();
    }

    /**
     * Get the num-th commandline argument.
     *
     * @param num argument position
     * @return argument previously initialized by {@code idris_initArgs}
     */
    public static String idris_getArg(int num) {
        return args.get(num);
    }
    

    /**
     * Query the system environment for a value. Maps to java properties.
     *
     * @param x query key
     * @return value stored in the system environment for the given key
     */
    public static String getenv(String x) {
        return System.getProperty(x);
    }
    
    /**
     * Set a system environment value. Maps to java properties.
     * 
     * @param key property name to set
     * @param value property value to set
     * @param overwrite a value other than zero indicates, that existing values
     * should be overwritten
     * @return 1 if the operation was successful 0 otherwise.
     */
    public static int setenv(String key, String value, int overwrite) {
        try {
            if (overwrite != 0 && System.getProperty(key) != null) {
                return 0;
            }
            System.setProperty(key, value);
        } catch (SecurityException | NullPointerException | IllegalArgumentException ex) {
            return 0;
        }
        return 1;
    }

    /**
     * Get the n-th "key=value" property from the environment.
     * 
     * @param n
     * @return the property in "key=value" form or null if it does not exist
     */
    public static String getEnvPair(int n) {
        Object [] properties = System.getProperties().entrySet().toArray();
        if (properties.length > n) {
            return properties[n].toString();
        } else {
            return null;
        }
    }
    
    /**
     * Unset a system environment value. Maps to java properties.
     * 
     * @param key the value to clear
     * @return 1 if the value could be removed, 0 otherwise
     */
    public static int unsetenv(String key) {
        try {
            System.clearProperty(key);
        } catch (SecurityException | NullPointerException | IllegalArgumentException ex) {
            return 0;
        }
        return 1;
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
     * Get the time in seconds since 1970-01-01 00:00:00 UTC.
     * 
     * @return the current time
     */
    public static int idris_time() {
        return (int)(System.currentTimeMillis() / 1000);
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
    public static int idris_checkMessages(Object dest) throws InterruptedException {
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
    public static void putchar(int c) {
        System.out.print((char)c);
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
            errorFiles.add(System.in);
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
     * Fork a process and return a file handle for reading or writing from/to its
     * standard output/input.
     * 
     * @param command command to run
     * @param mode "r" for reading, "w" for writing
     * @return the file handle to read/write or null if an error occurred
     */
    public static synchronized Object do_popen(String command, String mode) {
        try{
            Process process = Runtime.getRuntime().exec(command);
            if (mode.equals("r")) {
                InputStream stream = process.getInputStream();
                processHandles.put(stream, process);
                return stream;
            } else {
                OutputStream stream = process.getOutputStream();
                processHandles.put(stream, process);
                return stream;
            }
        } catch (IOException ex) {
            return null;
        }
    }
    
    /**
     * Wait for a forked process and close its file handle.
     * 
     * @param file the file handle to wait for
     * @return the exit code of the terminated process or -1 to indicate an error
     */
    public static synchronized int pclose(Object file) {
        Process process = processHandles.get(file);
        if (process == null) {
            return -1;
        }
        try {
            int result = process.waitFor();
            errorFiles.remove(file);
            feofFiles.remove(file);
            return result;
        } catch (InterruptedException ex) {
            return -1;
        }            
    }

    /**
     * Close a file handle
     * @param file handle to close
     */
    public static synchronized void fileClose(Object file) {
        try {
            synchronized (file) {
                ((Closeable) file).close();
                feofFiles.remove(file);
                errorFiles.remove(file);
            }
        } catch (IOException ex) {
            errorFiles.add(file);
        }
    }
    
    /**
     * Wait for one second or until input becomes available for the given file.
     * 
     * @param file the file to wait for
     * @return -1 on error, 0 if no input became available, 1 if input is available
     */
    public static synchronized int fpoll(Object file) {
        if (file instanceof InputStream) {
            final InputStream stream = (InputStream)file;
            if (!stream.markSupported()) return -1;
            Thread t = new Thread() {
                @Override public void run() {
                    synchronized (stream) {
                        try {
                            stream.mark(1);
                            System.in.read();
                            stream.reset();
                        } catch (IOException ioEx) {
                            errorFiles.add(stream);
                        }
                    }
                }
            };
            try {
                t.join(1000);
                return (errorFiles.contains(stream) ? -1 : 1);
            } catch (InterruptedException intEx) {
                return 0;
            }
        } else if (file instanceof SelectableChannel) {
            try {
                Selector selector = Selector.open();
                ((SelectableChannel) file).register(selector, SelectionKey.OP_READ);
                return selector.select(1000);
            } catch (IOException ioEx) {
                errorFiles.add(file);
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * Write a string to a file handle.
     * 
     * @param file handle to write to. Has to be a PrintStream or a SeekableByteChannel.
     * @param string output
     */
    public static synchronized void fputStr(Object file, String string) {
        try {
            if (file instanceof PrintStream) {
                ((PrintStream) file).print(string);
            } else if (file instanceof SeekableByteChannel) {
                ((SeekableByteChannel) file).write(ByteBuffer.wrap(string.getBytes()));
            }
        } catch (IOException ex) {
            errorFiles.add(file);
        }
    }
    
    /**
     * Read one char from a file and return it or -1 on end of file or errors.
     * 
     * @param file the file to read from
     * @return -1 on errors or the character read
     */
    public static synchronized int fgetc(Object file) {
        synchronized (file) {
            ReadableByteChannel channel = file instanceof InputStream
                    ? Channels.newChannel((InputStream) file)
                    : (ReadableByteChannel) file;
            ByteBuffer buf = ByteBuffer.allocate(1);
            try {
                int read;
                do {
                    read = channel.read(buf);
                    if (read < 0) {
                        feofFiles.add(file);
                        return -1;
                    }
                } while (read == 0);

                return buf.get(0);
            } catch (IOException ex) {
                errorFiles.add(file);
                return -1;
            }
        }
    }

    /**
     * Read a line from a file.
     * Chooses the {@code line.seperator} System property as delimiter.
     * 
     * @param file handle to read from
     * @return next line from the handle
     */
    public static synchronized String idris_readStr(Object file) {
        synchronized (file) {
            ReadableByteChannel channel = file instanceof InputStream
                    ? Channels.newChannel((InputStream) file)
                    : (ReadableByteChannel) file;
            ByteBuffer buf = ByteBuffer.allocate(1);
            StringBuilder resultBuilder = new StringBuilder("");
            String delimiter = System.getProperty("line.separator");
            int read = 0;
            try {
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
            } catch (IOException ioEx) {
                errorFiles.add(file);
            }
            return resultBuilder.toString();
        }
    }
    
    /**
     * Test if a file has reached its end during {@code idris_readStr} operations.
     * 
     * @param file handle to test
     * @return 1 if the file has ended, 0 otherwise
     */
    public static synchronized int fileEOF(Object file) {
        return (feofFiles.contains(file) ? 1 : 0);
    }
    
    /**
     * Tests for a file error.
     * 
     * @param file the handle to check
     * @return 0 if no error is found, 1 if there is an error
     */
    public static synchronized int fileError(Object file) {
        synchronized (file) {
            if (file == System.out) {
                return (System.out.checkError() ? 1 : 0);
            }
            return (errorFiles.contains(file) ? 1 : 0);
        }
    }
    
    /**
     * Flush a file to disk.
     * 
     * @param file handle to write to
     */
    public static void fflush(Object file) {
        if (file instanceof PrintStream) {
            ((PrintStream) file).flush();
        }
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
     * Check if a string is {@code null}
     *
     * @param s string to check
     * @return 1 if the string is {@code null} 0 otherwise
     */
    public static int isNullString(String s) {
        return isNull(s);
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
    
    /**
     * Force garbage collection to be triggered.
     *  
     * @param thread the thread to be forced (currently ignored, since
     * System.gc() is thread agnostic.
     */
    public static void idris_forceGC(Object thread) {
        System.gc();
    }
    
    /**
     * Compare two references for equality.
     * 
     * @param reference1 the first reference to compare
     * @param reference2 the second reference to compare
     * @return 1 if the two references are equal, 0 otherwise.
     */
    public static int idris_eqPtr(Object reference1, Object reference2) {
        return (reference1 == reference2 ? 1 : 0);
    }
}
