package com.nikolasubasic.texttransformer.error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TextTransformerExceptionHandlerTest {

    private TextTransformerExceptionHandler exceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exceptionHandler = new TextTransformerExceptionHandler();
        webRequest = mock(WebRequest.class);
    }

    @Test
    void testHandleIllegalArgumentException() {
        String errorMessage = "This is a bad request";
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        when(webRequest.getDescription(false)).thenReturn("uri=/test");

        ResponseEntity<ApiError> responseEntity = exceptionHandler.handleIllegalArgumentException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("A processing error occurred.", responseEntity.getBody().getMessage());
        assertEquals(errorMessage, responseEntity.getBody().getError());
    }

    @Test
    void testHandleRuntimeException() {
        String errorMessage = "This is a runtime error";
        Throwable cause = new RuntimeException(errorMessage);
        RuntimeException exception = new RuntimeException(errorMessage, cause);


        when(webRequest.getDescription(false)).thenReturn("uri=/test");

        ResponseEntity<ApiError> responseEntity = exceptionHandler.handleRuntimeException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("A runtime error occurred.", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleRuntimeExceptionWithCause() {
        String causeMessage = "This is the cause of the runtime error";
        RuntimeException exception = new RuntimeException(new Throwable(causeMessage));

        when(webRequest.getDescription(false)).thenReturn("uri=/test");

        ResponseEntity<ApiError> responseEntity = exceptionHandler.handleRuntimeException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("A runtime error occurred.", responseEntity.getBody().getMessage());
        assertEquals(causeMessage, responseEntity.getBody().getError());
    }
}
