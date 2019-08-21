package com.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Average {

    public static void main(String[] args) {
        String[] list1 = { "test1", "test2", "test3", "test4", "test5", "test6", "test7" };
        String[] list2 = { "OK", "OK", "OK", "OK", "OK", "OK", "OK" };
        testGame(list1, list2);

        list1 = new String[] { "test1", "test2", "test3", "test4", "test5", "test6", "test7" };
        list2 = new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" };
        testGame(list1, list2);

        list1 = new String[] { "test1", "test2", "test3", "test4", "test5", "test6", "test7" };
        list2 = new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" };
        testGame(list1, list2);

        list1 = new String[] { "test1", "test2", "test3", "test4", "test5", "test6", "test7" };
        list2 = new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" };
        testGame(list1, list2);

        list1 = new String[] { "test1", "test2", "test3", "test4", "test5", "test6", "test7" };
        list2 = new String[] { "OK", "OK", "OK", "OK", "OK", "OK", "OK" };
        testGame(list1, list2);
    }

    private static void testGame(String[] list1, String[] list2) {
        System.out.println("Alex: " + cards[0] + ", Mario: " + cards[1] + " = " + play(cards));
    }

    private static String play(String[] list1, String[] list2) {
        String alex = cards[0];
        String mario = cards[1];

        Integer gameResult = IntStream.range(0, alex.length()).map(i -> {
            Integer alexCard = cardValue.get(alex.charAt(i));
            Integer marioCard = cardValue.get(mario.charAt(i));
            if (alexCard == null || marioCard == null || alexCard == marioCard) return 0;
            else if (alexCard > marioCard) return -1;
            else return 1;
        }).sum();

        return (gameResult < 0 ? "Alex" : (gameResult > 0 ? "Mario" : "-"));
    }
}
