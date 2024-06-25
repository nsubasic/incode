package com.nikolasubasic.texttransformer.strategies;

import java.util.Map;

public interface TransformerStrategy {
    String transform(String value, Map<String,String> parameters);
}