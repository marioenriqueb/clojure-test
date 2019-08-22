package com.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Percentage {

    private static final String TEST_OK = "OK";

    public static void main(String[] args) {
        String[] list1 = { "test1a", "test2", "test1b", "test1c", "test3"};
        String[] list2 = { "Wrong answer", "OK", "Runtime error", "OK", "Time limit exceeded" };

        testPercentage(list1, list2);
    }

    private static void testPercentage(String[] tests, String[] results) {
        System.out.println("Final Percentage = " + play(tests, results));
    }

    private static Integer play(String[] T, String[] R) {

        Map<Integer, Boolean> mapa = new HashMap<>();

        List<Integer> groups = Arrays.stream(T)
                .map(str -> Integer.valueOf(str.replaceAll("\\D+", "")))
                .collect(Collectors.toList());

        int[] results = IntStream
                .range(0, R.length)
                .map(i -> TEST_OK.equalsIgnoreCase(R[i]) ? 1 : 0).toArray();

        IntStream.range(0, R.length).forEach(index -> {
            Integer key = groups.get(index);
            Boolean value = results[index] == 1;
            mapa.put(key, mapa.get(key) == null ? value : mapa.get(key) && value);
        });

        return (int) Math.round(((mapa.keySet().stream().mapToDouble(key -> mapa.get(key) ? 1.0 : 0.0).sum() / T.length) * 100));
    }

}
