package regul.test;

import regul.src.RegExprHome;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;


class RegExprHomeTest {
    @org.junit.jupiter.api.Test
    void onlyOdd() {
        String[] input = {"ab", "aab", "abbaabbabaaa", "aaacbbbbb", "abccaasdaaa", "", "abbb"};
        Boolean[] expected = {true, false, true, false, false, false, true};
        Boolean[] output = Arrays.stream(input)
                .map(s -> (boolean) RegExprHome.onlyOdd(s))
                .collect(Collectors.toList())
                .toArray(Boolean[]::new);

        assertArrayEquals(expected, output);
    }

    @org.junit.jupiter.api.Test
    void isIden() {
        String[] input = {"ab", "1aab", "c_ab"};
        Boolean[] expected = {true, false, true};
        Boolean[] output = Arrays.stream(input)
                .map(s -> (boolean) RegExprHome.isIden(s))
                .collect(Collectors.toList())
                .toArray(Boolean[]::new);

        assertArrayEquals(expected, output);
    }

    @org.junit.jupiter.api.Test
    void cntIden() {
        String[] input = { " ab  8vb , kl  ", "a 2a *a_", ""};
        Integer[] output = Arrays.stream(input)
                .mapToInt(s -> RegExprHome.cntIden(s))
                .boxed()
                .collect(Collectors.toList())
                .toArray(Integer[]::new);

        assertArrayEquals(new Integer[] {2, 1, 0}, output);
    }

    @org.junit.jupiter.api.Test
    void distIden() {
        String[] input = { " ab  8vb , kl, kl  ", "a 2a *a_, 2a, b", ""};
        Integer[] output = Arrays.stream(input)
                .mapToInt(s -> RegExprHome.distIden(s))
                .boxed()
                .collect(Collectors.toList())
                .toArray(Integer[]::new);

        assertArrayEquals(new Integer[] {2, 2, 0}, output);
    }

    @org.junit.jupiter.api.Test
    void sumDouble() {

    }
}