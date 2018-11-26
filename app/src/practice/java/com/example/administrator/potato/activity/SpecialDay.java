package com.example.administrator.potato.activity;

public class SpecialDay {
    public static void main(String arg[]) {
        int[] secret = {72, 97, 112, 112, 121};
        int date = 2 << 9;
        for (int i = 0; i < secret.length; i++) {
            System.out.print((char) secret[i]);
        }
        System.out.print(" " + date);
    }
}
