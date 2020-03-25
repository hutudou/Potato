package com.example.administrator.potato.activity;

/**
 * happy 1024
 *
 * @author potato
 */
public class SpecialDayActivity {
    public static void main(String arg[]) {
        int[] secret = {72, 97, 112, 112, 121};
        int date = 2 << 9;
        for (int aSecret : secret) {
            System.out.print((char) aSecret);
        }
        System.out.print(" " + date);
    }
}
