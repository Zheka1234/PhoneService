package com.miskevich.exception;

public class EntityIsUsedException extends RuntimeException {

    public EntityIsUsedException(String message) {

        super(message);
    }
}