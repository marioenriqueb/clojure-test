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

        int cadeiaLength = cadeia.length();
        char[] a = cadeia.substring(0, cadeiaLength / 2).toCharArray();
        char[] b = cadeia.substring(cadeiaLength / 2, cadeiaLength).toCharArray();

        return (IntStream.range(0, a.length).map(i -> a[(a.length - 1) - i] == b[i] ? 0 : 1).sum()) == 0;
    }
}
