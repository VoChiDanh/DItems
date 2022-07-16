package net.danh.ditems.Manager;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

    private static final Map<String, Integer> cooldowns = new HashMap<>();

    public static void setCooldown(String string, int time) {
        if (time < 1) {
            cooldowns.remove(string);
        } else {
            cooldowns.put(string, time);
        }
    }

    public static int getCooldown(String string) {
        return cooldowns.getOrDefault(string, 0);
    }
}
