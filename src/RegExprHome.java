package regul.src;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegExprHome {
    private static final Pattern splitter = Pattern.compile("[, ]+");

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
        return Pattern.compile("[a-zA-Z]([a-zA-Z\\d_])*").matcher(id).matches();
    }

    public static int cntIden(String str) {
        return (int) Arrays.stream(str.strip().split(splitter.pattern()))
                .filter(RegExprHome::isIden)
                .count();
    }

    public static int distIden(String str) {
        Set<String> stringSet = Arrays.stream(str.strip().split(splitter.pattern()))
                .filter(RegExprHome::isIden)
                .collect(Collectors.toSet());

        return stringSet.size();
    }

    public static Double sumDouble(String str) {
        String[] numbers = str.strip().toUpperCase(Locale.ROOT).split(splitter.pattern());
        try {
            List<Double> parsed = Arrays.stream(numbers)
                    .mapToDouble(Double::parseDouble)
                    .boxed()
                    .toList();
            return parsed
                    .stream()
                    .mapToDouble(number -> number)
                    .sum();
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
