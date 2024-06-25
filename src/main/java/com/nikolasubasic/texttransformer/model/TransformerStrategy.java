package com.nikolasubasic.texttransformer.model;

import java.util.Map;

public interface TransformerStrategy {
    String transform(String value, Map<String,String> parameters);
}