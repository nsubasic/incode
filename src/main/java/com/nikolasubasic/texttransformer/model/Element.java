package com.nikolasubasic.texttransformer.model;

import lombok.Data;

import java.util.List;

@Data
public class Element {
    private String value;
    private List<Transformer> transformers;
}
