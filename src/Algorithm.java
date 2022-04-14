package regul.src;

import java.util.*;
import java.util.regex.*;

public class Algorithm {
	private ArrayList <Rule> base = new ArrayList<>();

	private class Rule {
		public static final Pattern defaultRulePattern = Pattern.compile("[\\w#]+\\s*-\\s*>\\s*.?\\s*\\w+");
		public static final Pattern emptyRulePattern = Pattern.compile("([\\w#]*\\s*-\\s*>\\s*.?\\s*[\\w#]*)");
		public static final Pattern ruleSplitter = Pattern.compile("\\s*-\\s*>\\s*");
		public static final Pattern isEnd = Pattern.compile("\\s*.\\s*\\w*");

		private final Pattern left;
		private final String right;
		private final boolean end;

		Rule (Pattern left, String right, boolean end){
			this.left = left;
			this.right = right;
			this.end = end;
		}

		@Override
		public String toString() {
			return "\"" + left + "\"" + " -> " + (end ? "." : "") + " \"" + right + "\"";
		}
	}

	public Algorithm (String allRules) {
		Arrays.stream(allRules.split("\\s*;\\s*"))
				.forEach(rule -> {
					rule = rule.strip();
					if (!(rule.matches(Rule.defaultRulePattern.pattern())
						|| rule.matches(Rule.emptyRulePattern.pattern())))
						throw new IllegalArgumentException(rule + " is an illegal rule.");

					String[] parameters = rule.split(Rule.ruleSplitter.pattern());
					Pattern left = Pattern.compile(parameters[0].strip());
					String right = parameters[1].replace(" ", "");
					boolean end = right.matches(Rule.isEnd.pattern());
					base.add(new Rule(left, end ? right.substring(1) : right, end));
				});
	}

	public String eval(String begin, int cnt) {
		StringBuffer temp = new StringBuffer(begin);

        for (int i = 0; i < cnt; i++) {
			String current = temp.toString();
			Optional<Rule> currentRule = base.stream()
					.filter(rule -> rule.left.matcher(current).find() ||
							rule.left.pattern().equals(""))
					.findFirst();

			if (currentRule.isPresent()) {
				Matcher currentMatcher = currentRule.get().left.matcher(current);

				if (!currentRule.get().left.pattern().equals("")) {
					temp.replace(0, temp.length(), currentMatcher.replaceFirst(currentRule.get().right));
				} else {
					temp.insert(0, currentRule.get().right);
				}

				if (currentRule.get().end)
					return temp.toString();
			}
		}

		return "undefined";
	}

	public boolean isGood() { return base != null;}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		if (base == null) {
			res.append("Error Algorithm");
		} else {
			res.append("Algorithm = [");
	        for (int i = 0; i < base.size(); i++) {
				res.append("  \n " + base.get(i).toString());
			}
			res.append("]");
		}

		return res.toString();
	}
}
