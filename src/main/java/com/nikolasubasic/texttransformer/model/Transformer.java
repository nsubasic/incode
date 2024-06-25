package com.nikolasubasic.texttransformer.model;

import lombok.Data;
import java.util.Map;

@Data
public class Transformer {
    private String groupId;
    private String transformerId;
    private Map<String, String> parameters;
}
