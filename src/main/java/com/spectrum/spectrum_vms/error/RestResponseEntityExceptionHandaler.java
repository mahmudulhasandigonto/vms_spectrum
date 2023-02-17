package com.spectrum.spectrum_vms.error;

import com.spectrum.spectrum_vms.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandaler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ErrorMessage> adminNotFoundException(DriverNotFoundException exception,
                                                               WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(FuelLogNotFoundException.class)
    public ResponseEntity<ErrorMessage> adminNotFoundException(FuelLogNotFoundException exception,
                                                               WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ErrorMessage> adminNotFoundException(VehicleNotFoundException exception,
                                                               WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(VehicleDocumentNotFoundException.class)
    public ResponseEntity<ErrorMessage> adminNotFoundException(VehicleDocumentNotFoundException exception,
                                                               WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(VehicleRequestNotFoundException.class)
    public ResponseEntity<ErrorMessage> adminNotFoundException(VehicleRequestNotFoundException exception,
                                                               WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }




    @ExceptionHandler(DeleteRequestException.class)
    public ResponseEntity<ErrorMessage> handleDeleteRequestException(DeleteRequestException exception) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

}
