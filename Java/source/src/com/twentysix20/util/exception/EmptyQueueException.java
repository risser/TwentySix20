/*
 * EmptyQueueException.java
 *
 * Created on August 24, 2004, 10:57 PM
 */

package com.twentysix20.util.exception;

/**
 *
 * @author  Peter Risser
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {}
    public EmptyQueueException(String msg) { super(msg); }
    public EmptyQueueException(Throwable t) { super(t); }
    public EmptyQueueException(String msg, Throwable t) { super(msg, t); }
}
