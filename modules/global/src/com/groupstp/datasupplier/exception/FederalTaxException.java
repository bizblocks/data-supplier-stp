package com.groupstp.datasupplier.exception;

/**
 * Federal Tax System integration exception
 *
 * @author adiatullin
 */
public class FederalTaxException extends Exception {
    private static final long serialVersionUID = -4031120838173857127L;

    public FederalTaxException(String message) {
        super(message);
    }

    public FederalTaxException(String message, Exception e) {
        super(message, e);
    }
}
