package com.palantir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestAnswer {
    Logger logger = LoggerFactory.getLogger(RequestAnswer.class);
    Pattern twoSumPattern = Pattern.compile(".*what is the sum of (\\d+) and (\\d+)");
    Pattern largestPattern = Pattern.compile(".*Which of the following numbers is the largest: (.*)");



    public String answer(String parameter) {
        String answer = "";
        Matcher matcher = largestPattern.matcher(parameter);
        if (matcher.matches()) {
            answer = largest(matcher.group(1));
        }
        matcher = twoSumPattern.matcher(parameter);
        if (matcher.matches()) {
            answer = sum(matcher.group(1), matcher.group(2));
        }
        logger.info("The answer is {}", answer);
        return answer;
    }

    private String sum(String group, String group1) {
        logger.info("sum {} + {}", group, group1);
        return Integer.toString(Integer.parseInt(group) + Integer.parseInt(group1));
    }

    private String largest(String group) {
        logger.info("largest {}", group);
        String[] splits = group.split(", ");
        int max = Integer.MIN_VALUE;
        for (String s : splits) {
            try {
                int i = Integer.parseInt(s);
                if (i > max) {
                    max = i;
                }
            } catch (Exception ex) {

            }
        }
        return String.valueOf(max);
    }
}
