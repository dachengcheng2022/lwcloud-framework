package com.autumn.exception;

public class RpcRemoteException extends Exception {
    public RpcRemoteException() {
        super();
    }

    public RpcRemoteException(String message) {
        super(message);
    }

    public RpcRemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public static <E extends Exception> void doThrow(Exception e) throws E {
        throw (E) e;
    }
}
