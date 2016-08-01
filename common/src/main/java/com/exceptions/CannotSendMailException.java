package com.exceptions;

/**
 * Created by allan on 27/07/16.
 */
public class CannotSendMailException extends Exception {
    public CannotSendMailException() {

    }

    public CannotSendMailException(String msg) {
        super(msg);
    }

    public CannotSendMailException(String msg, Throwable t) {
        super(msg, t);
    }
}
