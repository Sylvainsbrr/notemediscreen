package com.notemediscreen.notemediscreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundExecption extends RuntimeException {

    public DataNotFoundExecption(String errorMessage) {
        super(errorMessage);
    }

    public DataNotFoundExecption(String message, Throwable cause) {
        super(message, cause);
    }

}
