package com.palantir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtremeStartup extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ExtremeStartup.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("q");

        logger.info("A request has arrived:");
        logger.info(parameter);

        resp.getWriter().write(answer(parameter));
    }

    String answer(String parameter) {
        if (parameter == null)
            return "Scala Rulez";
        StringAnswerer stringAnswerer = new StringAnswerer(parameter);
        Matcher additionMatcher = Pattern.compile(".*what is the sum of (\\d+) and (\\d+)").matcher(parameter);
        if (additionMatcher.matches()) {
            return String.valueOf(Integer.parseInt(additionMatcher.group(1))
                    + Integer.parseInt(additionMatcher.group(2)));
        }
        String response = Integer.toString(stringAnswerer.getAnswer());
        logger.info("Returning response: " + response);
        return response;
    }

}