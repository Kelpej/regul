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
        String[] input = {"ab", "1aab", "(_ab", "_cc", "2", "c_ab"};
        Boolean[] expected = {true, false, false, false, false, true};
        Boolean[] output = Arrays.stream(input)
                .map(RegExprHome::isIden)
                .toArray(Boolean[]::new);

        assertArrayEquals(expected, output);
    }

    @org.junit.jupiter.api.Test
    void cntIden() {
        String[] input = {" ab  8vb , kl  ", "a 2a *a_, b", "", ".c 1c b,", "c *aal ac*ac", " % 6 ()96 ="};
        Integer[] output = Arrays.stream(input)
                .mapToInt(RegExprHome::cntIden)
                .boxed()
                .toArray(Integer[]::new);
        assertArrayEquals(new Integer[]{3, 4, 0, 3, 4, 0}, output);
    }

    @org.junit.jupiter.api.Test
    void distIden() {
        String[] input = {" ab  8vb , kl, kl  ", "a 2a *a_, 2a, b", "", "ab", "c *aal ac*ac", " % 6 ()96 ="};
        Integer[] output = Arrays.stream(input)
                .mapToInt(RegExprHome::distIden)
                .boxed()
                .toArray(Integer[]::new);

        assertArrayEquals(new Integer[]{3, 3, 0, 1, 3, 0}, output);
    }

//    @org.junit.jupiter.api.Test
//    void sumDouble() {
//        String[] input = {" 0.567e3, +0.433 ", " -0.7e-2"};
//        Double[] output = Arrays.stream(input)
//                .mapToDouble(RegExprHome::sumDouble)
//                .boxed()
//                .toArray(Double[]::new);
//
//        assertArrayEquals(output, new Double[]{567.443, -0.007}, 0.001, "you messed");
//    }
}