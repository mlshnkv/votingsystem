package org.moloshnikov.votingsystem.util.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {

    APP_ERROR("error.appError", HttpStatus.INTERNAL_SERVER_ERROR),
    //  http://stackoverflow.com/a/22358422/548473
    DATA_NOT_FOUND("error.dataNotFound", HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR("error.dataError", HttpStatus.CONFLICT),
    DEADLINE_ERROR("error.deadLineError", HttpStatus.METHOD_NOT_ALLOWED),
    VALIDATION_ERROR("error.validationError", HttpStatus.UNPROCESSABLE_ENTITY),
    ACCESS_ERROR("error.forbiddenError", HttpStatus.FORBIDDEN);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}