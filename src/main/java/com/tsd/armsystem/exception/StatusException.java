package com.tsd.armsystem.exception;

public class StatusException extends RuntimeException {
    public StatusException(String status_not_found) {
        super(status_not_found);
    }
}
