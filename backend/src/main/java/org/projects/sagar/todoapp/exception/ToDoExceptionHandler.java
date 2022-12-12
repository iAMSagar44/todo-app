package org.projects.sagar.todoapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestControllerAdvice
public class ToDoExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ToDoExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ToDoError invalidRequestErrorHandler(MethodArgumentNotValidException ex){
        ToDoError toDoError= new ToDoError(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpStatus.BAD_REQUEST.value(), "Invalid Request");
        logger.info("The errors are : {}", ex.getBindingResult());
        ex.getBindingResult().getFieldErrors().forEach(e -> toDoError.addMap(e.getField(), e.getDefaultMessage()));
        return toDoError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ToDoError invalidRequestParamErrorHandler(MethodArgumentTypeMismatchException ex){
        ToDoError toDoError = new ToDoError(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpStatus.BAD_REQUEST.value(), "Invalid Request");
        logger.info("The errors are: {}", ex.getLocalizedMessage());
        toDoError.addMap(ex.getName(), ex.getLocalizedMessage());
        return toDoError;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ToDoError invalidRequestURIErrorHandler(MissingServletRequestParameterException ex){
        ToDoError toDoError = new ToDoError(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpStatus.BAD_REQUEST.value(), "Invalid Request");
        logger.info("The errors are: {}", ex.getLocalizedMessage());
        toDoError.addMap(ex.getParameterName(), ex.getLocalizedMessage());
        return toDoError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ToDoNotFoundException.class)
    public ToDoError todoItemNotFound(ToDoNotFoundException ex){
        ToDoError toDoError = new ToDoError(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return toDoError;
    }

}
