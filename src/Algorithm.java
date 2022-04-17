package regul.src;

import java.util.*;
import java.util.regex.*;

public class Algorithm {
    private ArrayList<Rule> base = new ArrayList<>();

    private static class Rule {
        public static final Pattern defaultRulePattern = Pattern.compile("[a-zA-Z0-9#]*\\s*->.?\\s*[a-zA-Z0-9#]*");
        public static final Pattern emptyRulePattern = Pattern.compile("[a-zA-Z0-9#]*\\s*->.?\\s*[a-zA-Z0-9#]*");
        public static final Pattern ruleSplitter = Pattern.compile("\\s*->");
        public static final Pattern isEnd = Pattern.compile("\\s*.\\s*[a-zA-Z0-9#]*");


        private final Pattern left;
        private final String right;
        private final boolean end;

        Rule(Pattern left, String right, boolean end) {
            this.left = left;
            this.right = right;
            this.end = end;
        }

        @Override
        public String toString() {
            return "\"" + left + "\"" + " -> " + (end ? "." : "") + " \"" + right + "\"";
        }
    }

    public Algorithm(String allRules) {
        Arrays.stream(allRules.split("\\s*;\\s*"))
                .forEach(rule -> {
                    rule = rule.strip();
                    if (!(rule.matches(Rule.defaultRulePattern.pattern())
                            || rule.matches(Rule.emptyRulePattern.pattern()))) {
                        base = null;
                        return;
                    }
                    String[] parameters = rule.split(Rule.ruleSplitter.pattern());
                    Pattern left = Pattern.compile(parameters[0].strip());
                    String right = parameters[1].replace(" ", "");
                    boolean end = right.matches(Rule.isEnd.pattern());
                    base.add(new Rule(left, end ? right.substring(1) : right, end));
                });
    }

    public String eval(String begin, int steps) {
        StringBuilder temp = new StringBuilder(begin);

        for (int i = 0; i < steps; i++) {
            String current = temp.toString();
            Optional<Rule> currentRule = base.stream()
                    .filter(rule -> rule.left.matcher(current).find() ||
                            rule.left.pattern().equals(""))
                    .findFirst();

            if (currentRule.isPresent()) {
                Rule rule = currentRule.get();
                Matcher currentMatcher = currentRule.get().left.matcher(current);
                if (!rule.left.pattern().isEmpty()) {
                    temp.replace(0, temp.length(), currentMatcher.replaceFirst(rule.right));
                } else {
                    temp.insert(0, rule.right);
                }

                if (currentRule.get().end)
                    return temp.toString();
            }
        }
        return "undefined";
    }

    public boolean isGood() {
        return base != null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (base == null) {
            res.append("Error Algorithm");
        } else {
            res.append("Algorithm = [");
            for (Rule rule : base) {
                res.append("\n").append(rule.toString());
            }
            res.append("]");
        }
        return res.toString();
    }
}
