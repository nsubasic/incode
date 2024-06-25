package com.nikolasubasic.texttransformer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransformationResponse {
    private String originalValue;
    private String transformedValue;
}
