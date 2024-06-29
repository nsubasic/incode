package com.nikolasubasic.texttransformer.strategies;

import static com.nikolasubasic.texttransformer.utils.Constants.PARAM_REGEX;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class RemoveRegexMatchTransformerTest {

    private static final String TEST_VALUE = "123abc";
    private static final String TEST_VALUE_LETTERS_ONLY = "abc";
    private static final String NUMBER_PATTERN = "\\d+";
    private RemoveRegexMatchTransformer transformer;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        transformer = new RemoveRegexMatchTransformer();
        parameters = new HashMap<>();
    }

    @Test
    void testTransform_NullValue() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(null, parameters);
        });
        assertEquals("Input value and parameters must not be null.", exception.getMessage());
    }

    @Test
    void testTransform_NullParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(TEST_VALUE, null);
        });
        assertEquals("Input value and parameters must not be null.", exception.getMessage());
    }

    @Test
    void testTransform_MissingRegexParameter() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(TEST_VALUE, parameters);
        });
        assertEquals("Regex parameter must not be null or empty.", exception.getMessage());
    }

    @Test
    void testTransform_EmptyRegexParameter() {
        parameters.put(PARAM_REGEX, "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(TEST_VALUE, parameters);
        });
        assertEquals("Regex parameter must not be null or empty.", exception.getMessage());
    }

    @Test
    void testTransform_InvalidRegexPattern() {
        parameters.put(PARAM_REGEX, "[");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(TEST_VALUE, parameters);
        });
        assertEquals("Invalid regex pattern provided.", exception.getMessage());
    }

    @Test
    void testTransform_NoMatch() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        String result = transformer.transform(TEST_VALUE_LETTERS_ONLY, parameters);
        assertEquals(TEST_VALUE_LETTERS_ONLY, result);
    }

    @Test
    void testTransform_Match() {
        parameters.put(PARAM_REGEX, NUMBER_PATTERN);
        String result = transformer.transform("123abc456", parameters);
        assertEquals(TEST_VALUE_LETTERS_ONLY, result);
    }

    @Test
    void testTransform_PartialMatch() {
        parameters.put(PARAM_REGEX, TEST_VALUE_LETTERS_ONLY);
        String result = transformer.transform("123abc456", parameters);
        assertEquals("123456", result);
    }
}
