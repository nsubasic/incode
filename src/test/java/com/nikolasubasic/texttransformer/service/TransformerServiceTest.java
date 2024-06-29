package com.nikolasubasic.texttransformer.service;

import com.nikolasubasic.texttransformer.model.*;
import com.nikolasubasic.texttransformer.strategies.TransformerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.nikolasubasic.texttransformer.utils.Constants.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TransformerServiceTest {
    private static final String TEST_GROUP_ID = "test_group_id";
    private static final String TEST_REGEX_LOWER = "test";
    private static final String TEST_REGEX_UPPER = "TEST";
    private static final String TEST_VALUE = "TESTtest";
    private static final String TEST_VALUE_AFTER_REPLACEMENT = "replacementForTest_ИнДифферентАлпхабет";
    private static final String TEST_VALUE_TRANSLITERATED = "replacementForTest_InDifferentAlphabet";
    private static final String TEST_VALUE_NOT_FOR_TRANSFORMATION = "value not to be transformed";
    private static final String TEST_NON_EXISTANT_TRANSFORMER = "nonExistantTransformerId";

    @Mock
    private TransformerFactory transformerFactory;

    @InjectMocks
    private TransformerServiceImpl transformerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTransformElementsWithMultipleTransformers() {
        Element element = createElementWithAllTransformers();

        when(transformerFactory.getTransformer("removeRegex")).thenReturn((value, params) -> TEST_REGEX_LOWER);
        when(transformerFactory.getTransformer("replaceRegex")).thenReturn((value, params) -> TEST_VALUE_AFTER_REPLACEMENT);
        when(transformerFactory.getTransformer("transliterate")).thenReturn((value, params) -> TEST_VALUE_TRANSLITERATED);

        List<TransformationResponse> results = transformerService.transformElements(Collections.singletonList(element));
        assertEquals(1, results.size());
        assertEquals(TEST_VALUE_TRANSLITERATED, results.get(0).getTransformedValue());
    }

    @Test
    void testTransformElementsWithEmptyTransformers() {
        Element element = new Element();
        element.setTransformers(Collections.emptyList());
        element.setValue(TEST_VALUE_NOT_FOR_TRANSFORMATION);

        List<TransformationResponse> results = transformerService.transformElements(Collections.singletonList(element));
        assertEquals(1, results.size());
        assertEquals(TEST_VALUE_NOT_FOR_TRANSFORMATION, results.get(0).getTransformedValue());
    }

    @Test
    void testTransformElementsWithNullAndEmptyCollection() {
        List<TransformationResponse> results = transformerService.transformElements(null);
        assertTrue(results.isEmpty());

        results = transformerService.transformElements(Collections.emptyList());
        assertTrue(results.isEmpty());
    }

    @Test
    void testTransformElementsWithNullTransformersList() {
        Element element = new Element();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> transformerService.transformElements(Collections.singletonList(element))
        );

        assertEquals("Transformers list cannot be null", exception.getMessage());
    }

    @Test
    void testApplyTransformersWithMissingStrategy() {
        Element element = createElementWithNonExistingTransformer();

        when(transformerFactory.getTransformer(TEST_NON_EXISTANT_TRANSFORMER)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> transformerService.transformElements(Collections.singletonList(element))
        );

        assertTrue(exception.getMessage().contains(String.format("No transformation strategy found for ID: %s", TEST_NON_EXISTANT_TRANSFORMER)));
    }

    @Test
    void testApplyTransformersExceptionDuringTransformation() {
        Element element = createElementWithAllTransformers();
        TransformerStrategy strategy = mock(TransformerStrategy.class);

        when(transformerFactory.getTransformer(REPLACE_REGEX_TRANSFORMER)).thenReturn(strategy);
        when(strategy.transform(anyString(), any())).thenThrow(new IllegalArgumentException("Transformation failed"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> transformerService.transformElements(Collections.singletonList(element))
        );

        assertTrue(exception.getMessage().contains(String.format("Error during transformation for transformer ID: %s", REPLACE_REGEX_TRANSFORMER)));
    }

    private Element createElementWithAllTransformers() {
        Transformer removeRegexTransformer = getTransformer(REMOVE_REGEX_TRANSFORMER, TEST_REGEX_UPPER);
        Transformer replaceRegexTransformer = getTransformer(REPLACE_REGEX_TRANSFORMER, TEST_REGEX_LOWER);
        Transformer transliterateTransformer = getTransformer(TRANSLITERATE_TRANSFORMER, "");

        Element element = new Element();
        element.setValue(TEST_VALUE);
        element.setTransformers(Arrays.asList(replaceRegexTransformer, removeRegexTransformer, transliterateTransformer));

        return element;
    }

    private Transformer getTransformer(String type, String regex) {
        Transformer transformer = new Transformer();
        Map<String, String> parameters = new HashMap<>();

        switch (type) {
            case REPLACE_REGEX_TRANSFORMER: {
                String replacement = TEST_VALUE_AFTER_REPLACEMENT;
                parameters.put(PARAM_REPLACEMENT, replacement);
            }
            case REMOVE_REGEX_TRANSFORMER: {
                parameters.put(PARAM_REGEX, regex);
            }
            case TRANSLITERATE_TRANSFORMER: {
                transformer.setTransformerId(type);
            }
        }
        transformer.setParameters(parameters);
        transformer.setGroupId(TEST_GROUP_ID);
        return transformer;
    }

    private Element createElementWithNonExistingTransformer() {
        Transformer transformer = new Transformer();
        transformer.setTransformerId(TEST_NON_EXISTANT_TRANSFORMER);
        transformer.setParameters(null);

        Element element = new Element();
        element.setTransformers(Collections.singletonList(transformer));
        element.setValue(TEST_VALUE);

        return element;
    }
}