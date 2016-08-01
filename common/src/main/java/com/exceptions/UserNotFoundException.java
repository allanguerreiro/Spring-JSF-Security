package com.exceptions;

/**
 * Created by allan on 27/07/16.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {

    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
