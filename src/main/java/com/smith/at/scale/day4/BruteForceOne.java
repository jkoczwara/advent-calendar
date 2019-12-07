package com.smith.at.scale.day4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BruteForceOne {

    // We should rewrite that method so it construct only inputs that makes sense instead of checking them after it is already too late
    public List<Integer> force(int lower, int upper) {
        return IntStream.range(lower, upper)
                .filter(this::check)
                .boxed()
                .collect(Collectors.toList());
    }

    /*
        It is a six-digit number.
        The value is within the range given in your puzzle input.
        Two adjacent digits are the same (like 22 in 122345).
        Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
        The two adjacent matching digits are not part of a larger group of matching digits.
     */
    public boolean check(int input) {
        String asString = String.valueOf(input);
        return  isSixDigitNumber(input) && hasRepetitions(asString) && repeatedDigitsAreExactlyTwoDigitsLong(asString);
    }

    private static final int SIX_DIGITS = 100000;
    private static final int SEVEN_DIGITS = 1000000;

    private boolean isSixDigitNumber(int input) {
        return (input / SIX_DIGITS > 0 && input / SEVEN_DIGITS == 0);
    }

    private boolean hasRepetitions(String asString) {
        char[] sorted = asString.toCharArray();
        Arrays.sort(sorted);

        boolean isAlwaysIncreasedSequence = Arrays.equals(sorted, asString.toCharArray());
        long counted = asString.chars().distinct().count();
        return isAlwaysIncreasedSequence && sorted.length != counted;
    }

    private boolean repeatedDigitsAreExactlyTwoDigitsLong(String asString) {
        return asString.chars().boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting())).containsValue((long) (2));
    }


}
