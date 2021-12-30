package com.example.fsm.exception;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class FsmException extends RuntimeException {
    public FsmException(String message) {
        super(message);
    }

    public FsmException(String message, Throwable cause) {
        super(message, cause);
    }

    public FsmException(Throwable cause) {
        super(cause);
    }
}
