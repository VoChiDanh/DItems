package net.danh.ditems.Resource;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author VoChiDanh
 */
public class Resource {

    /**
     * @return Config File
     */
    public static FileConfiguration getConfig() {
        return new Files("config").getConfig();
    }

    /**
     * @return Message File
     */
    public static FileConfiguration getMessage() {
        return new Files("message").getConfig();
    }

    /**
     * @return Stats File
     */
    public static FileConfiguration getStats() {
        return new Files("stats").getConfig();
    }
}
