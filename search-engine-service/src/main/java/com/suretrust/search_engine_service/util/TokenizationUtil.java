package com.suretrust.search_engine_service.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TokenizationUtil {

    // Tokenize the input string by splitting on spaces and special characters
    public static List<String> tokenize(String input) {
        if (input == null || input.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(input.split("\\s+|\\W+"))
                .map(String::toLowerCase)
                .filter(token -> !token.isEmpty())
                .collect(Collectors.toList());
    }
}
