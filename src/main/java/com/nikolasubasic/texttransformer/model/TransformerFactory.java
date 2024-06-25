package com.nikolasubasic.texttransformer.model;

import com.nikolasubasic.texttransformer.strategies.TransformerStrategy;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class TransformerFactory {
    private final Map<String, TransformerStrategy> transformers;

    public TransformerFactory(Map<String, TransformerStrategy> transformers) {
        this.transformers = transformers;
    }

    public TransformerStrategy getTransformer(String transformerId) {
        return transformers.get(transformerId);
    }
}