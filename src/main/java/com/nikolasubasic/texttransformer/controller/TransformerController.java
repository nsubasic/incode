package com.nikolasubasic.texttransformer.controller;

import com.nikolasubasic.texttransformer.api.TransformerApi;
import com.nikolasubasic.texttransformer.model.Element;
import com.nikolasubasic.texttransformer.model.TransformationResponse;
import com.nikolasubasic.texttransformer.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/transform")
public class TransformerController implements TransformerApi {

    @Autowired
    private TransformerService transformerService;

    @Override
    @PostMapping
    public ResponseEntity<List<TransformationResponse>> transformText(@RequestBody Collection<Element> elements) {
        List<TransformationResponse> responses = transformerService.transformElements(elements);
        return ResponseEntity.ok(responses);
    }
}
