package com.nikolasubasic.texttransformer.service;

import com.nikolasubasic.texttransformer.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransformerServiceImpl implements TransformerService {

    private final TransformerFactory transformerFactory;
    private static final Logger logger = LoggerFactory.getLogger(ReplaceRegexMatchTransformer.class);

    @Autowired
    public TransformerServiceImpl(TransformerFactory transformerFactory) {
        this.transformerFactory = transformerFactory;
    }

    @Override
    public List<TransformationResponse> transformElements(Collection<Element> elements) {
        if (elements == null || elements.isEmpty()) {
            logger.error("Element list is null or empty");
            return Collections.emptyList();
        }

        List<TransformationResponse> transformedElements = new ArrayList<>();

        for (Element element : elements) {
            List<Transformer> transformers = element.getTransformers();
            String value = element.getValue();
            transformedElements.add(applyTransformers(value, transformers));
        }

        return transformedElements;
    }

    private TransformationResponse applyTransformers(String value, List<Transformer> transformers) {
        if (transformers == null) {
            logger.error("Transformer list is null or empty");
            throw new IllegalArgumentException("Transformers list cannot be null");
        }

        String originalValue = value;
        String transformedValue = value;

        for (Transformer transformer : transformers) {
            TransformerStrategy transformerStrategy = transformerFactory.getTransformer(transformer.getTransformerId());

            if (transformerStrategy == null) {
                String errorMessage = String.format("No transformation strategy found for ID: %s", transformer.getTransformerId());
                logger.error(errorMessage);
                throw new IllegalStateException(errorMessage);
            }

            try {
                transformedValue = transformerStrategy.transform(transformedValue, transformer.getParameters());
            } catch (Exception e) {
                String errorContext = String.format("Error during transformation for transformer ID: %s", transformer.getTransformerId());
                logger.error(errorContext, e);
                throw new RuntimeException(errorContext, e);
            }
        }

        return new TransformationResponse(originalValue, transformedValue);
    }
}
