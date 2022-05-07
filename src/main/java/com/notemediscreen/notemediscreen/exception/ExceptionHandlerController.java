package com.notemediscreen.notemediscreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = DataNotFoundExecption.class)
    public ResponseEntity<Object> handlePatientNotFoundException(DataNotFoundExecption p){
        //1. Create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        PatientException patientNoteException = new PatientException(p.getMessage(), notFound, ZonedDateTime.now()
        );
        //2. Return response entity
        return new ResponseEntity<>(patientNoteException, notFound);
    }
}
