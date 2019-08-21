package com.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Cards {

    private static final Map<Character, Integer> cardValue = new HashMap<Character, Integer>() {{
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
        System.out.println("Alex: " + cards[0] + ", Mario: " + cards[1] + " = " + play(cards));
    }

    private static String play(String[] cards) {
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
