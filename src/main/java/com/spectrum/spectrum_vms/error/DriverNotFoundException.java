package com.spectrum.spectrum_vms.error;

public class DriverNotFoundException extends Exception {
    public DriverNotFoundException() {
    }

    public DriverNotFoundException(String arg0) {
        super(arg0);
    }

    public DriverNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public DriverNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DriverNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
