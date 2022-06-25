package net.danh.ditems.Manager;

public class Check {

    public static boolean isDouble(java.lang.String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
