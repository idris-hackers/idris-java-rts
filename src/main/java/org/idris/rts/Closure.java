package org.idris.rts;

import java.util.concurrent.Callable;

/**
 * Closures encapsulate function calls in order to allow tail recursion
 * elimination.
 * 
 * @author Jan Bessai
 */
public abstract class Closure implements Callable<Object>, Runnable {
    /**
     * Method including the function call encapsulated by this closure.
     * 
     * @return result of the function or null if it has none.
     */
    @Override
    public abstract Object call();
    
    /**
     * Unfold tail calls by invoking the call method of the given parameter
     * and its results until they can no longer be casted to {@code Closure}.
     * 
     * @param o Closure to be unwrapped
     * @return The result of the last {@code call} or the parameter if it is
     * not an instance of closure.
     */
    public static Object unwrapTailCall(Object o) {
        while (o instanceof Closure) {
            o = ((Closure)o).call();
        }
        return o;
    }
    
    /**
     * Run unwrapTailCall on {@code this}.
     */
    @Override
    public void run() {
	unwrapTailCall(call());
    }
    
    /**
     * Fork a new thread using {@code this} as its Runnable.
     *
     * @return the newly forked thread
     */
    public Thread fork() {
        Thread childProcess = new Thread(this);
        childProcess.start();
        return childProcess;
    }
}
