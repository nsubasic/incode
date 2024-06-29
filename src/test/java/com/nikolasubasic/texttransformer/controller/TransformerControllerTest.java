package com.nikolasubasic.texttransformer.controller;

import com.nikolasubasic.texttransformer.model.Element;
import com.nikolasubasic.texttransformer.model.TransformationResponse;
import com.nikolasubasic.texttransformer.service.TransformerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransformerControllerTest {

    private static final String VALUE_1 = "Привет";
    private static final String VALUE_2 = "123";
    private static final String TRANSFORMED_VALUE_1 = "Privet";
    private static final String TRANSFORMED_VALUE_2 = "123";

    @Mock
    private TransformerService transformerService;

    @InjectMocks
    private TransformerController transformerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransformText() {
        List<Element> elements = Arrays.asList(createElement(VALUE_1), createElement(VALUE_2));
        List<TransformationResponse> expectedResponses = Arrays.asList(
                createTransformationResponse(VALUE_1, TRANSFORMED_VALUE_1),
                createTransformationResponse(VALUE_2, TRANSFORMED_VALUE_2)
        );

        when(transformerService.transformElements(elements)).thenReturn(expectedResponses);

        ResponseEntity<List<TransformationResponse>> responseEntity = transformerController.transformText(elements);

        assertEquals(ResponseEntity.ok(expectedResponses), responseEntity);
        assertEquals(expectedResponses, responseEntity.getBody());

        verify(transformerService, times(1)).transformElements(elements);
    }

    @Test
    void testTransformText_EmptyElements() {
        List<Element> elements = Collections.emptyList();
        List<TransformationResponse> expectedResponses = Collections.emptyList();

        when(transformerService.transformElements(elements)).thenReturn(expectedResponses);

        ResponseEntity<List<TransformationResponse>> responseEntity = transformerController.transformText(elements);

        assertEquals(ResponseEntity.ok(expectedResponses), responseEntity);
        assertEquals(expectedResponses, responseEntity.getBody());

        verify(transformerService, times(1)).transformElements(elements);
    }

    @Test
    void testTransformText_NullElements() {
        List<Element> elements = null;
        List<TransformationResponse> expectedResponses = Collections.emptyList();

        when(transformerService.transformElements(elements)).thenReturn(expectedResponses);

        ResponseEntity<List<TransformationResponse>> responseEntity = transformerController.transformText(elements);

        assertEquals(ResponseEntity.ok(expectedResponses), responseEntity);
        assertEquals(expectedResponses, responseEntity.getBody());

        verify(transformerService, times(1)).transformElements(elements);
    }

    private Element createElement(String value) {
        Element element = new Element();
        element.setValue(value);
        return element;
    }

    private TransformationResponse createTransformationResponse(String id, String transformedValue) {
        return new TransformationResponse(id, transformedValue);
    }
}
