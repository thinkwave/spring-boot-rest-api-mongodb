package com.exam.api.common.error.exception;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = -868921409926065587L;

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}