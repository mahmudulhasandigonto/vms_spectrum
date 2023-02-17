package com.spectrum.spectrum_vms.error;

public class VehicleDocumentNotFoundException extends Exception {
    public VehicleDocumentNotFoundException() {

    }

    public VehicleDocumentNotFoundException(String message) {
        super(message);
    }

    public VehicleDocumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleDocumentNotFoundException(Throwable cause) {
        super(cause);
    }

    public VehicleDocumentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
