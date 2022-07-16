package net.danh.ditems.Resource;

import net.danh.dcore.Resource.FileFolder;
import net.danh.dcore.Resource.Files;
import net.danh.ditems.DItems;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author VoChiDanh
 */
public class Resource {

    /**
     * @return Config File
     */
    public static FileConfiguration getConfig() {
        return new Files(DItems.getInstance(), "config").getConfig();
    }

    /**
     * @return Message File
     */
    public static FileConfiguration getMessage() {
        return new Files(DItems.getInstance(), "message").getConfig();
    }

    /**
     * @return Stats File
     */
    public static FileConfiguration getStats() {
        return new Files(DItems.getInstance(), "stats").getConfig();
    }

    /**
     * @return CMD File
     */
    public static FileConfiguration getCMD() {
        return new FileFolder(DItems.getInstance(), "cmd", "Ability").getConfig();
    }
}
