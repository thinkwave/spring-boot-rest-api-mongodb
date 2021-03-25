package com.exam.api.common.error.exception;

public class InvalidValueException extends BusinessException {

    private static final long serialVersionUID = 7716549015700045567L;

    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}