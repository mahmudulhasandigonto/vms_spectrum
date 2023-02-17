package com.spectrum.spectrum_vms.error;

public class VehicleRequestNotFoundException extends Exception {
    public VehicleRequestNotFoundException() {
    }

    public VehicleRequestNotFoundException(String message) {
        super(message);
    }

    public VehicleRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleRequestNotFoundException(Throwable cause) {
        super(cause);
    }

    public VehicleRequestNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
