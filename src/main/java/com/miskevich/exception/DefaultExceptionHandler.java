package com.miskevich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static com.miskevich.constant.ExceptionConst.ENTITY_IS_ALREADY_USED;
import static com.miskevich.constant.ExceptionConst.ENTITY_NOT_FOUND_EXCEPTION;
import static com.miskevich.constant.ExceptionConst.INTERNAL_EXCEPTION;
import static com.miskevich.constant.ExceptionConst.THE_PARAMETER_S_OF_VALUE_S_COULD_NOT_BE_CONVERTED_TO_TYPE_S;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, javax.persistence.EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorMessage message = new ErrorMessage(ENTITY_NOT_FOUND_EXCEPTION, exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityIsUsedException.class)
    public ResponseEntity<ErrorMessage> handleEntityIsUsedException(EntityIsUsedException exception) {
        ErrorMessage message = new ErrorMessage(ENTITY_IS_ALREADY_USED, exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorMessage message = new ErrorMessage(THE_PARAMETER_S_OF_VALUE_S_COULD_NOT_BE_CONVERTED_TO_TYPE_S, ex.getMessage());
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorMessage message = new ErrorMessage(INTERNAL_EXCEPTION, ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}