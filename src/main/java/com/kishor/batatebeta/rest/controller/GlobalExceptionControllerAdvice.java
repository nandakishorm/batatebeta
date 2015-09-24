package com.kishor.batatebeta.rest.controller;

import com.kishor.batatebeta.exception.BatateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Nandakishor on 9/23/2015.
 */

@ControllerAdvice
//@ComponentScan
public class GlobalExceptionControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionControllerAdvice.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Batate exception occurred")
    @ExceptionHandler(BatateException.class)
    public String batateExceptionAdvice(BatateException ex){
        logger.info("BATATE EXCEPTION ::::: Batate exception was handled");
        return ex.getMessage();
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Null pointer exception occurred")
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionAdvice(NullPointerException ex){
        logger.info("BATATE - NULL POINTER EXCEPTION ::::: Null pointer exception was handled");
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Illegal argument exception occurred")
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionExceptionAdvice(IllegalArgumentException ex){
        logger.info("BATATE - ILLEGAL ARGUMENT EXCEPTION ::::: Illegal argument exception was handled");
        return ex.getMessage();
    }
}
