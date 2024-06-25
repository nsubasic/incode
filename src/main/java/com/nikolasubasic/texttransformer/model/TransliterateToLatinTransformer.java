package com.nikolasubasic.texttransformer.model;

import com.nikolasubasic.texttransformer.util.TransliterationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.stream.Collectors;

@Component("transliterate")
public class TransliterateToLatinTransformer implements TransformerStrategy {

    private static final Logger logger = LoggerFactory.getLogger(TransliterateToLatinTransformer.class);

    @Override
    public String transform(String value, Map<String, String> parameters) {
        return transform(value);
    }

    private String transform(String value) {
        if (value == null) {
            logger.error("Null value provided");
            throw new IllegalArgumentException("Input value cannot be null.");
        }

        return value.chars()
                .mapToObj(c -> TransliterationHelper.getLatinEquivalent((char) c))
                .collect(Collectors.joining());
    }
}