package com.nikolasubasic.texttransformer.service;

import com.nikolasubasic.texttransformer.model.Element;
import com.nikolasubasic.texttransformer.model.TransformationResponse;

import java.util.Collection;
import java.util.List;

public interface TransformerService {
    List<TransformationResponse> transformElements(Collection<Element> elements);
}

