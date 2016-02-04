import com.google.common.base.Splitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtremeStartup extends HttpServlet {

    public static final String TEAM_NAME = "team kickass";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("q");

        System.out.println("A request has arrived:");
        System.out.println(parameter);

        resp.getWriter().write(answer(parameter));
    }

    String answer(String parameter) {
        if (parameter == null)
            return TEAM_NAME;

        String PATTERN_SUM = ".*what is (\\d+) plus (\\d+)";
        String PATTERN_LARGEST = ".*which of the following numbers is the largest: (.*)";

        Pattern regexSum = Pattern.compile(PATTERN_SUM);
        Matcher sumMatcher = regexSum.matcher(parameter);
        if (sumMatcher.matches()) {
            int first = Integer.parseInt(sumMatcher.group(1));
            int second = Integer.parseInt(sumMatcher.group(2));

            String sum = Integer.toString(first + second);
            System.out.println("answer for sum: " + sum);
            return sum;
        }

        Pattern regexLargest = Pattern.compile(PATTERN_LARGEST);
        Matcher largetsMatcher = regexLargest.matcher(parameter);
        if (largetsMatcher.matches()) {
            String listOfNumbers = largetsMatcher.group(1);
            OptionalInt optionalMax = Splitter.on(",")
                    .omitEmptyStrings()
                    .trimResults()
                    .splitToList(listOfNumbers)
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .max();

            int maxValue = optionalMax.orElse(0);
            System.out.println("answer for max: " + maxValue);
            return Integer.toString(maxValue);
        }


        alertNewQuestion();
        return TEAM_NAME;
    }

    private void alertNewQuestion() {
        System.out.println("NEW QUESTION");
        System.out.println("NEW QUESTION");
        System.out.println("NEW QUESTION");
    }
}
