package com.palantir;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RequestAnswerShould {
    RequestAnswer requestAnswer = new RequestAnswer();
    @Test public void
    when_request_contains_what_is_bigger_should_return_max_value() {
        assertThat(requestAnswer.answer("which of the following numbers is the largest: 419, 16"),
                   is("419"));
    }

    @Test public void
    when_request_is_two_sum() {
        assertThat(requestAnswer.answer("what is the sum of 3 and 5"), is("8"));
    }

    @Test public void
    what_is_multiplied_by() {
        assertThat(requestAnswer.answer("what is 5 plus 3"), is("8"));
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
    
    @Test public void
    when_request_is_multiply() {
    	assertThat(requestAnswer.answer("f2ab7470: what is the 5th number in the Fibonacci sequence"), is("5"));
    }
}
