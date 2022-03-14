package fr.vx.rpg.utils;

public class Maths
{
    public static int Addition(int a, int b)
    {
        return a+b;
    }
    public static double DoubleAddition(double a, double b) {return a+b;}
    public static double DoubleSubstraction(double base, double sub) {return base-sub;}
    public static long toAsciiLong(String text) {
        StringBuilder ascii = new StringBuilder("");
        for (int i = 0; i < text.length(); i++)
            ascii.append(Integer.toString((int) text.charAt(i)));
        return Long.parseLong(ascii.toString());}
    public static int convertLongToInteger(long x)
    {
        return (int) x;
    }
}