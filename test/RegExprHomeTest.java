package regul.test;

import regul.src.RegExprHome;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class RegExprHomeTest {
    @org.junit.jupiter.api.Test
    void onlyOdd() {
        String[] input = {"ab", "aab", "abbaabbabaaa", "aaacbbbbb", "abccaasdaaa", "", "abbb"};
        Boolean[] expected = {true, false, true, false, false, false, true};
        Boolean[] output = Arrays.stream(input)
                .map(s -> (boolean) RegExprHome.onlyOdd(s)).toList()
                .toArray(Boolean[]::new);

        assertArrayEquals(expected, output);
    }

    @org.junit.jupiter.api.Test
    void isIden() {
        String[] input = {"ab", "1aab", "c_ab"};
        Boolean[] expected = {true, false, true};
        Boolean[] output = Arrays.stream(input)
                .map(RegExprHome::isIden)
                .toArray(Boolean[]::new);

        assertArrayEquals(expected, output);
    }

    @org.junit.jupiter.api.Test
    void cntIden() {
        String[] input = {" ab  8vb , kl  ", "a 2a *a_", "", "klds dsa8sj,    21sas  _opel"};
        Integer[] output = Arrays.stream(input)
                .mapToInt(RegExprHome::cntIden)
                .boxed()
                .toArray(Integer[]::new);

        assertArrayEquals(new Integer[]{3, 3, 0, 4}, output);
    }

    @org.junit.jupiter.api.Test
    void distIden() {
        String[] input = {" ab  8vb , kl, kl  ", "a 2a *a_, 2a, b", ""};
        Integer[] output = Arrays.stream(input)
                .mapToInt(RegExprHome::distIden)
                .boxed()
                .toArray(Integer[]::new);

        assertArrayEquals(new Integer[]{3, 2, 0}, output);
    }

    @org.junit.jupiter.api.Test
    void sumDouble() {

    }
}