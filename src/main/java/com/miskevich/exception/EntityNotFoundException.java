package com.miskevich.exception;

import static com.miskevich.constant.ExceptionConst.ENTITY_IS_NOT_FOUND_ID;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super(ENTITY_IS_NOT_FOUND_ID + id);
    }

}