package com.palantir;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RequestAnswerShould {
    RequestAnswer requestAnswer = new RequestAnswer();
    @Test public void
    when_request_contains_what_is_bigger_should_return_max_value() {
        assertThat(requestAnswer.answer("Which of the following numbers is the largest: 419, 16"),
                   is("419"));

    }

    @Test public void
    when_request_is_two_sum() {
        assertThat(requestAnswer.answer("what is the sum of 3 and 5"), is("8"));
    }

}