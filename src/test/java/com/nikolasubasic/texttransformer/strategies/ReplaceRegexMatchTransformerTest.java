package com.nikolasubasic.texttransformer.strategies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.nikolasubasic.texttransformer.utils.Constants.PARAM_REGEX;
import static com.nikolasubasic.texttransformer.utils.Constants.PARAM_REPLACEMENT;
import static org.junit.jupiter.api.Assertions.*;

class ReplaceRegexMatchTransformerTest {

    private static final String TEST_REPLACEMENT_VALUE = "X";
    private static final String NUMBER_PATTERN = "\\d+";

    private ReplaceRegexMatchTransformer transformer;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        transformer = new ReplaceRegexMatchTransformer();
        parameters = new HashMap<>();
    }

    @Test
    void testTransform_NullValue() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        parameters.put(PARAM_REPLACEMENT, TEST_REPLACEMENT_VALUE);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(null, parameters);
        });
        assertEquals("Input value and parameters must not be null.", exception.getMessage());
    }

    @Test
    void testTransform_NullParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform("123abc", null);
        });
        assertEquals("Input value and parameters must not be null.", exception.getMessage());
    }

    @Test
    void testTransform_MissingRegexParameter() {
        parameters.put(PARAM_REPLACEMENT, TEST_REPLACEMENT_VALUE);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform("123abc", parameters);
        });
        assertEquals("Regex and replacement parameters must not be null.", exception.getMessage());
    }

    @Test
    void testTransform_MissingReplacementParameter() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform("123abc", parameters);
        });
        assertEquals("Regex and replacement parameters must not be null.", exception.getMessage());
    }

    @Test
    void testTransform_InvalidRegexPattern() {
        parameters.put(PARAM_REGEX, "[");
        parameters.put(PARAM_REPLACEMENT, TEST_REPLACEMENT_VALUE);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform("123abc", parameters);
        });
        assertEquals("Invalid regex pattern provided.", exception.getMessage());
    }

    @Test
    void testTransform_NoMatch() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        parameters.put(PARAM_REPLACEMENT, TEST_REPLACEMENT_VALUE);
        String result = transformer.transform("abc", parameters);
        assertEquals("abc", result);
    }

    @Test
    void testTransform_Match() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        parameters.put(PARAM_REPLACEMENT, TEST_REPLACEMENT_VALUE);
        String result = transformer.transform("123abc456", parameters);
        assertEquals("XabcX", result);
    }

    @Test
    void testTransform_PartialMatch() {
        parameters.put(PARAM_REGEX, "abc");
        parameters.put(PARAM_REPLACEMENT, "XYZ");
        String result = transformer.transform("123abc456", parameters);
        assertEquals("123XYZ456", result);
    }
}
