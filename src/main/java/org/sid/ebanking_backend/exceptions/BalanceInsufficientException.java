package org.sid.ebanking_backend.exceptions;

public class BalanceInsufficientException extends Throwable {
    public BalanceInsufficientException(String message) {
        super(message);
    }
}
