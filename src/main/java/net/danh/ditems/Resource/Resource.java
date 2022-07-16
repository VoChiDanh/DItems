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
        Files file = new Files(DItems.getInstance(), "config");
        file.load();
        return file.getConfig();
    }

    /**
     * @return Message File
     */
    public static FileConfiguration getMessage() {
        Files file = new Files(DItems.getInstance(), "message");
        file.load();
        return file.getConfig();
    }

    /**
     * @return Stats File
     */
    public static FileConfiguration getStats() {
        Files file = new Files(DItems.getInstance(), "stats");
        file.load();
        return file.getConfig();
    }

    /**
     * @return CMD File
     */
    public static FileConfiguration getCMD() {
        FileFolder file = new FileFolder(DItems.getInstance(), "cmd", "Ability");
        file.load();
        return file.getConfig();
    }
}
