package edu.tienda.core.controller;

import edu.tienda.core.excceptions.BadRequestException;
import edu.tienda.core.excceptions.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.BAD_REQUEST.toString());

        return  new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handlerNotFoundRequest(RuntimeException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
