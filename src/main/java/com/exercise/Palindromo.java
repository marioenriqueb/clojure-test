package com.exercise;

import java.util.stream.IntStream;

public class Palindromo {

    public static void main(String[] args) {
        String cadeia = "holaaloh";
        System.out.println(cadeia + ": " + check(cadeia));

        cadeia = "holachau";
        System.out.println(cadeia + ": " + check(cadeia));

        cadeia = "abcdeedcba";
        System.out.println(cadeia + ": " + check(cadeia));

        cadeia = "abcde";
        System.out.println(cadeia + ": " + check(cadeia));

        cadeia = "123456789987654321";
        System.out.println(cadeia + ": " + check(cadeia));

        cadeia = "12345678987654321";
        System.out.println(cadeia + ": " + check(cadeia));
    }

    private static Boolean check(String cadeia) {

        cadeia = cadeia.trim();

        if (cadeia.isEmpty() || cadeia.length() % 2 > 0) {
            return Boolean.FALSE;
        }

        String temp  = cadeia.trim().toLowerCase();
        return IntStream.range(0, temp.length() / 2)
                .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
    }
}
