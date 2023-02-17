package com.spectrum.spectrum_vms.error;

import java.io.Serial;

public class DeleteRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    public DeleteRequestException(String message) {
        super(message);
    }


}
