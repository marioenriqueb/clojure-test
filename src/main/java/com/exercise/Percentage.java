package com.exercise;

import java.util.stream.IntStream;

public class Percentage {

    private static final String TEST_OK = "OK";

    public static void main(String[] args) {
        String[] list1 = { "test1", "test2", "test3", "test4", "test5", "test6", "test7" };
        String[][] list2 = {
                new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" }};

        testPercentage(list1, list2);

        list2 = new String[][] {
                new String[] { "NO", "OK", "OK", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "NO", "OK", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "NO", "OK", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "NO", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "OK", "NO", "OK", "NO" }};

        testPercentage(list1, list2);

        list2 = new String[][] {
                new String[] { "NO", "NO", "OK", "NO", "OK", "OK", "OK" },
                new String[] { "OK", "NO", "OK", "NO", "OK", "OK", "NO" },
                new String[] { "OK", "NO", "NO", "NO", "OK", "OK", "OK" },
                new String[] { "OK", "NO", "OK", "NO", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "NO", "NO", "OK", "NO" }};

        testPercentage(list1, list2);

        list2 = new String[][] {
                new String[] { "NO", "NO", "OK", "NO", "OK", "OK", "OK" },
                new String[] { "OK", "NO", "OK", "NO", "OK", "OK", "NO" },
                new String[] { "NO", "NO", "NO", "NO", "NO", "NO", "NO" },
                new String[] { "OK", "NO", "OK", "NO", "OK", "OK", "OK" },
                new String[] { "OK", "OK", "OK", "NO", "NO", "OK", "NO" }};

        testPercentage(list1, list2);
    }

    private static void testPercentage(String[] tests, String[][] results) {
        System.out.println("Final Percentage = " + play(tests, results));
    }

    private static Double play(String[] tests, String[][] results) {

        return IntStream.range(0, tests.length).mapToDouble(test -> {
            Double percentage = (IntStream.range(0, results.length)
                    .mapToDouble(result -> TEST_OK.equalsIgnoreCase(results[result][test]) ? 1.0 : 0.0)
                    .sum() / results.length) * 100;
                    // System.out.println(tests[test] + " = " + percentage);
                    return percentage;
        }).average().orElse(0);
    }
}
