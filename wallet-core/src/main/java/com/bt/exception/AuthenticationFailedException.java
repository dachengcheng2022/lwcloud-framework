package com.bt.exception;

public class AuthenticationFailedException extends RuntimeException{

    public AuthenticationFailedException() {
        super();
    }

    public AuthenticationFailedException(String message) {

        super(message,null,false,false);
    }

    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause,false,false);
    }
}
