public class StringAnswerer {
    private final int answer;

    public StringAnswerer(String request) {
        String[] splits = request.split(", ");
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
        this.answer = max;
    }

    public int getAnswer() {
        return answer;
    }
}
