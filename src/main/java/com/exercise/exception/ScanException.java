package com.exercise.exception;

public class ScanException extends Exception {

    private static final long serialVersionUID = 1L;

    public ScanException() {
        super();
    }

    public ScanException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public ScanException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ScanException(String arg0) {
        super(arg0);
    }

    public ScanException(Throwable arg0) {
        super(arg0);
    }
}
