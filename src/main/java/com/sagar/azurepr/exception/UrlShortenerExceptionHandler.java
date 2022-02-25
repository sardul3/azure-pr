package com.sagar.azurepr.exception;

import com.sagar.azurepr.exception.exceptions.UrlNotFoundException;
import com.sagar.azurepr.model.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
@Slf4j
public class UrlShortenerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest) {
        ErrorMessage errorMessage =  ErrorMessage.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(webRequest.getDescription(true))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public final ResponseEntity<Object> handleUrlNotFoundExceptions(UrlNotFoundException exception, WebRequest webRequest) {
        ErrorMessage errorMessage =  ErrorMessage.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .description(webRequest.getDescription(false))
                .build();
        log.info("URL not present");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
