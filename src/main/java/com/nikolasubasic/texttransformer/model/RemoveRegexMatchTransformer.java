package com.nikolasubasic.texttransformer.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.nikolasubasic.texttransformer.util.Constants.PARAM_REGEX;

@Component("removeRegex")
public class RemoveRegexMatchTransformer implements TransformerStrategy {

    private static final Logger logger = LoggerFactory.getLogger(RemoveRegexMatchTransformer.class);

    @Override
    public String transform(String value, Map<String, String> parameters) {
        if (value == null || parameters == null) {
            logger.error("Null value or parameters provided");
            throw new IllegalArgumentException("Input value and parameters must not be null.");
        }

        String regex = parameters.get(PARAM_REGEX);
        if (regex == null || regex.isEmpty()) {
            logger.error("Regex parameter is missing");
            throw new IllegalArgumentException("Regex parameter must not be null or empty.");
        }

        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            logger.error("Invalid regex pattern: {}", regex, e);
            throw new IllegalArgumentException("Invalid regex pattern provided.", e);
        }

        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) {
            return value;
        }

        return matcher.replaceAll("");
    }
}
