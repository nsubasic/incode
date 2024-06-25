package com.nikolasubasic.texttransformer.api;

import com.nikolasubasic.texttransformer.model.Element;
import com.nikolasubasic.texttransformer.model.TransformationResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface TransformerApi {
    ResponseEntity<List<TransformationResponse>> transformText(Collection<Element> elements) throws Exception;
}
