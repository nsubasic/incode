package com.nikolasubasic.texttransformer.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private String message;
    private String error;
}
