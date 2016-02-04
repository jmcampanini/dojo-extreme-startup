package com.palantir;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestAnswer {
    Logger logger = LoggerFactory.getLogger(RequestAnswer.class);
    Pattern twoSumPattern = Pattern.compile(".*what is the sum of (\\d+) and (\\d+)");
    Pattern largestPattern = Pattern.compile(".*which of the following numbers is the largest: (.*)");
    Pattern squareAndCubePattern = Pattern.compile(".*which of the following numbers is both a square and a cube: (.*)");


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
        matcher = squareAndCubePattern.matcher(parameter);
        if(matcher.matches()) {
        	answer = findSquareAndCube(matcher.group(1));
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
    
    private String findSquareAndCube(String group) {
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
