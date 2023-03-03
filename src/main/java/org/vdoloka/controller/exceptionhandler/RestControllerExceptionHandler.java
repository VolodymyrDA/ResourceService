package org.vdoloka.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vdoloka.exception.OrderNotFoundException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handle(OrderNotFoundException exception) {
        return new ResponseError(exception.getMessage(),  HttpStatus.NOT_FOUND);
    }
}