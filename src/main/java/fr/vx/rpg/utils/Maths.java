package fr.vx.rpg.utils;

import java.math.BigInteger;

public class Maths
{
    public static int Addition(int a, int b)
    {
        return a+b;
    }
    public static double DoubleAddition(double a, double b) {return a+b;}
    public static double DoubleSubstraction(double base, double sub) {return base-sub;}
    public static BigInteger toAsciiInteger(String text) {
        StringBuilder ascii = new StringBuilder("");
        for (int i = 0; i < text.length(); i++)
            ascii.append(Integer.toString((int) text.charAt(i)));
        return new BigInteger(ascii.toString());}
    public static int convertLongToInteger(long x)
    {
        return (int) x;
    }
}