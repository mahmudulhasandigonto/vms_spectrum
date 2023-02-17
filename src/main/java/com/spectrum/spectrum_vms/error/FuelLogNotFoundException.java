package com.spectrum.spectrum_vms.error;

public class FuelLogNotFoundException extends Exception {
    public FuelLogNotFoundException() {
    }

    public FuelLogNotFoundException(String message) {
        super(message);
    }

    public FuelLogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FuelLogNotFoundException(Throwable cause) {
        super(cause);
    }

    public FuelLogNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
