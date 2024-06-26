package com.nikolasubasic.texttransformer.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.nikolasubasic.texttransformer.utils.Constants.PARAM_REGEX;
import static com.nikolasubasic.texttransformer.utils.Constants.PARAM_REPLACEMENT;

@Component("replaceRegex")
public class ReplaceRegexMatchTransformer implements TransformerStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ReplaceRegexMatchTransformer.class);

    @Override
    public String transform(String value, Map<String, String> parameters) {
        if (value == null || parameters == null) {
            logger.error("Null value or parameters provided");
            throw new IllegalArgumentException("Input value and parameters must not be null.");
        }

        String regex = parameters.get(PARAM_REGEX);
        String replacement = parameters.get(PARAM_REPLACEMENT);

        if (regex == null || replacement == null) {
            logger.error("Regex or replacement parameter is missing");
            throw new IllegalArgumentException("Regex and replacement parameters must not be null.");
        }

        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            logger.error("Invalid regex pattern: {}", regex, e);
            throw new IllegalArgumentException("Invalid regex pattern provided.", e);
        }

        return pattern.matcher(value).replaceAll(replacement);
    }
}
