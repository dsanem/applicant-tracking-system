package com.ats.exception;

public class ATSException extends Exception {

    public ATSException() {
        super();
    }

    public ATSException(Throwable throwable) {
        super(throwable);
    }

    public ATSException(String message) {
        super(message);
    }
}
