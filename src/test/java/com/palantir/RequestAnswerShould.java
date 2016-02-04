package com.palantir;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RequestAnswerShould {
    @Test public void
    when_request_contains_what_is_bigger_should_return_max_value() {
        RequestAnswer requestAnswer = new RequestAnswer();
        assertThat(requestAnswer.answer("which of the following numbers is the largest: 419, 16"),
                   is("419"));

    }

    @Test public void
    when_request_is_two_sum() {

    }

    @Test public void
    when_request_is_both_square_and_cube_no_answer() {
    	RequestAnswer requestAnswer = new RequestAnswer();
        assertThat(requestAnswer.answer("a1087040: which of the following numbers is both a square and a cube: 671, 4, 2209, 475"),
                   is(""));
    }
    
    @Test public void
    when_request_is_both_square_and_cube_have_answer() {
    	RequestAnswer requestAnswer = new RequestAnswer();
        assertThat(requestAnswer.answer("a1087040: which of the following numbers is both a square and a cube: 671, 4, 64, 475"),
                   is("64"));
    }
}