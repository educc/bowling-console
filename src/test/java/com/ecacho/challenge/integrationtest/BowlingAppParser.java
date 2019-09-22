package com.ecacho.challenge.integrationtest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BowlingAppParser {

    public static Map<String, Integer> findUsersAndScore(String content) {
        Map<String, Integer> result = new HashMap<>();

        Pattern p = Pattern.compile("^\\w+$");
        List<String> lines = Arrays.asList(content.split("\n"))
                .stream()
                .map(String::trim)
                .filter(it -> p.matcher(it).find() || it.startsWith("Score"))
                .collect(Collectors.toList());

        String username = "";
        for (String line: lines) {
            if (line.startsWith("Score")) {
                Integer score = BowlingAppParser.parseScoreLine(line);
                if (!username.isEmpty()) {
                    result.put(username, score);
                }
                username = "";
            } else {
                username = line;
            }
        }

        return result;
    }

    private static Integer parseScoreLine(String line) {
        StringBuilder sb = new StringBuilder(line).reverse();
        StringBuilder sbLastNumber = new StringBuilder();

        for (char c: sb.toString().toCharArray()) {
            if (Character.isDigit(c)) {
                sbLastNumber.insert(0, c);
            } else {
                break;
            }
        }
        return Integer.valueOf(sbLastNumber.toString());
    }
}
