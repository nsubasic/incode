package com.nikolasubasic.texttransformer.strategies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransliterateToLatinTransformerTest {

    private static final String CYRILLIC_UPPER = "АБВГДЂЕЖЗИЈКЛЉМНЊОПРСТЋУФХЦЧЏШ";
    private static final String CYRILLIC_LOWER = "абвгдђежзијклљмнњопрстћуфхцчџш";
    private static final String GREEK_UPPER = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
    private static final String GREEK_LOWER = "αβγδεζηθικλμνξοπρστυφχψω";

    private static final String CYRILLIC_UPPER_TRANSLITERATED = "ABVGDDjEZZIJKLLjMNNjOPRSTCUFHCCDzS";
    private static final String GREEK_UPPER_TRANSLITERATED = "ABGDEZEThIKLMNXOPRSTYPhChPsO";


    private TransliterateToLatinTransformer transformer;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        transformer = new TransliterateToLatinTransformer();
        parameters = new HashMap<>();
    }

    @Test
    void testTransform_NullValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(null, parameters);
        });
        assertEquals("Input value cannot be null.", exception.getMessage());
    }

    @Test
    void testTransform_EmptyValue() {
        String result = transformer.transform("", parameters);
        assertEquals("", result);
    }

    @Test
    void testTransform_CyrillicUpper() {
        String result = transformer.transform(CYRILLIC_UPPER, parameters);
        assertEquals(CYRILLIC_UPPER_TRANSLITERATED, result);
    }

    @Test
    void testTransform_CyrillicLower() {
        String result = transformer.transform(CYRILLIC_LOWER, parameters);
        assertEquals(CYRILLIC_UPPER_TRANSLITERATED.toLowerCase(), result);
    }

    @Test
    void testTransform_GreekUpper() {
        String result = transformer.transform(GREEK_UPPER, parameters);
        assertEquals(GREEK_UPPER_TRANSLITERATED, result);
    }

    @Test
    void testTransform_GreekLower() {
        String result = transformer.transform(GREEK_LOWER, parameters);
        assertEquals(GREEK_UPPER_TRANSLITERATED.toLowerCase(), result);
    }
}