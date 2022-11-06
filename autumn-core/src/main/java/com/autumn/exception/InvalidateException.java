package com.autumn.exception;


public class InvalidateException extends Exception {
    public static <E extends Exception> void doThrow(Exception e) throws E {
        throw (E) e;
    }

}
