package com.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Cards {

    private static final Map<Character, Integer> CARD_VALUES = new HashMap<Character, Integer>() {{
        put('A', 14);
        put('K', 13);
        put('Q', 12);
        put('J', 11);
        put('T', 10);
        put('9', 9);
        put('8', 8);
        put('7', 7);
        put('6', 6);
        put('5', 5);
        put('4', 4);
        put('3', 3);
        put('2', 2);
    }};

    public static void main(String[] args) {
        testGame(new String[] { "A", "K" });
        testGame(new String[] { "5", "K" });
        testGame(new String[] { "5J", "J5" });
        testGame(new String[] { "3", "3" });
        testGame(new String[] { "AKQ234KQ", "AKQ234KQ" });
        testGame(new String[] { "AK234KQ", "K7463JJ" });
        testGame(new String[] { "87463JJ", "AKT34KQ" });
        testGame(new String[] { "AKQ234Q", "6732544" });
    }

    private static void testGame(String[] cards) {
        System.out.println("Alec: " + cards[0] + ", Bob: " + cards[1] + " = " + play(cards[0], cards[1]));
    }

    private static Integer play(String A, String B) {

        return IntStream
                .range(0, A.length())
                .map(i -> {
                    Integer alecCard = CARD_VALUES.get(A.charAt(i));
                    Integer bobCard = CARD_VALUES.get(B.charAt(i));
                    return alecCard > bobCard ? 1 : 0;
                }).sum();
    }
}
