package com.nikolasubasic.texttransformer.service;

import com.nikolasubasic.texttransformer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransformerServiceImpl implements TransformerService {

    private final TransformerFactory transformerFactory;

    @Autowired
    public TransformerServiceImpl(TransformerFactory transformerFactory) {
        this.transformerFactory = transformerFactory;
    }

    @Override
    public List<TransformationResponse> transformElements(Collection<Element> elements) {
        List<TransformationResponse> transformedElements = new ArrayList<>();

        for (Element element : elements) {
            List<Transformer> transformers = element.getTransformers();
            String value = element.getValue();
            transformedElements.add(applyTransformers(value, transformers));
        }

        return transformedElements;
    }

    private TransformationResponse applyTransformers(String value, List<Transformer> transformers) {
        String originalValue = value;
        String transformedValue = originalValue;
        for (Transformer transformer : transformers) {
            TransformerStrategy transformerStrategy = transformerFactory.getTransformer(transformer.getTransformerId());
            transformedValue = transformerStrategy.transform(transformedValue, transformer.getParameters());
        }
        return new TransformationResponse(originalValue, transformedValue);
    }
}
