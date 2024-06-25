package com.nikolasubasic.texttransformer.error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TextTransformerExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleIllegalStateException(IllegalStateException ex, WebRequest request) {

        Logger logger = LoggerFactory.getLogger(TextTransformerExceptionHandler.class);
        logger.error("Error: ", ex);

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "A processing error occurred.",
                ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex, WebRequest request) {

        Logger logger = LoggerFactory.getLogger(TextTransformerExceptionHandler.class);
        logger.error("Error: ", ex);

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "A runtime error occurred.",
                ex.getCause().getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
