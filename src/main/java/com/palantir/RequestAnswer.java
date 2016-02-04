package com.palantir;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestAnswer {
    private final static Logger logger = LoggerFactory.getLogger(RequestAnswer.class);
    Pattern twoSumPattern  = Pattern.compile(".*what is the sum of (\\d+) and (\\d+)");
    Pattern twoSumPattern2 = Pattern.compile(".*what is (\\d+) plus (\\d+)");
    Pattern largestPattern = Pattern.compile(".*which of the following numbers is the largest: (.*)");
    Pattern cubePattern = Pattern.compile(".*which of the following numbers is both a square and a cube: (.*)");
    Map< Pattern, Function< Matcher, String > > matchers;

    public RequestAnswer() {
        matchers = ImmutableMap
                .<Pattern, Function<Matcher, String>>builder()
                .put(twoSumPattern, RequestAnswer::sum)
                .put(twoSumPattern2, RequestAnswer::sum)
                .put(largestPattern, RequestAnswer::largest)
                .put(cubePattern, RequestAnswer::findSquareAndCube)
                .build();
    }

    public String answer(String parameter) {
        for ( Map.Entry< Pattern, Function< Matcher, String > > e : matchers.entrySet() ) {
            Matcher matcher = e.getKey().matcher(parameter);
            if ( matcher.matches() ) {
                String response = e.getValue().apply(matcher);
                logger.info("Returning response: {}", response);
                return response;
            }
        }
        logger.warn("No response for {}", parameter);
        return "";
    }

    public static String sum(Matcher matcher) {
        String group = matcher.group(1), group1 = matcher.group(2);
        logger.info("sum {} + {}", group, group1);
        return Integer.toString(Integer.parseInt(group) + Integer.parseInt(group1));
    }

    public static String largest(Matcher matcher) {
        String group = matcher.group(1);
        logger.info("largest {}", group);
        String[] splits = group.split(", ");
        int      max    = Integer.MIN_VALUE;
        for ( String s : splits ) {
            try {
                int i = Integer.parseInt(s);
                if ( i > max ) {
                    max = i;
                }
            } catch ( Exception ex ) {

            }
        }
        return String.valueOf(max);
    }
    private static String findSquareAndCube(Matcher matcher) {
        String group = matcher.group(1);
        logger.info("find square and cube {}", group);
        String[] splits = group.split(", ");

        List<Integer> values = new ArrayList<Integer>();
        values.add(0);
        values.add(1);
        values.add(64);
        values.add(729);
        values.add(15625);

        for (String numberStr : splits) {
            Integer number = Integer.parseInt(numberStr);
            if (values.contains(number)) {
                return number.toString();
            }
        }

        return "";
    }
}
