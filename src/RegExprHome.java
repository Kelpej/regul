package regul.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExprHome {
    private static final Pattern idenPattern = Pattern.compile("[a-zA-Z][a-zA-Z\\d_]*");
    private static final Pattern splitter = Pattern.compile("[^a-zA-Z][^a-zA-Z\\d_]*");

    public static boolean onlyOdd(String word) {
        if (Pattern.compile("[ab]+").matcher(word).matches())
            return findOccurrences(word, 'a') % 2 == 1 &&
                    findOccurrences(word, 'b') % 2 == 1;

        return false;
    }

    private static long findOccurrences(String word, char c) {
        return word.chars()
                .mapToObj(i -> (char) i)
                .filter(character -> character != c)
                .count();
    }

    public static boolean isIden(String id) {
        return idenPattern.matcher(id).matches();
    }

    public static int cntIden(String str) {
        return (int) Arrays.stream(str.strip().split(splitter.pattern()))
                .filter(s -> idenPattern.matcher(s).find())
                .count();
    }

    public static int distIden(String str) {
        HashSet<String> set = new HashSet<>();
        Matcher matcher = idenPattern.matcher(str);
        while (matcher.find())
            set.add(str.substring(matcher.start(), matcher.end()));
        return set.size();
    }

    public static Double sumDouble(String str) {
        String[] numbers = str.strip().split("[, ]+");
        return Arrays.stream(numbers)
                .mapToDouble(Double::parseDouble)
                .sum();
    }
}
