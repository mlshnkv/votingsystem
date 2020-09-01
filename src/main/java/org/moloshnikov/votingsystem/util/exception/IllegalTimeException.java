package org.moloshnikov.votingsystem.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class IllegalTimeException extends RuntimeException {
    public IllegalTimeException(String message) {
        super(message);
    }
}