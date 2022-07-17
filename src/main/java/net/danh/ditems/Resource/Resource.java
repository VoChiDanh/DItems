package net.danh.ditems.Resource;

import net.danh.dcore.Resource.FileFolder;
import net.danh.dcore.Resource.Files;
import net.danh.ditems.DItems;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author VoChiDanh
 */
public class Resource {

    public static void reloadFiles() {
        Files message = new Files(net.danh.ditems.DItems.getInstance(), "message");
        FileFolder items = new FileFolder(net.danh.ditems.DItems.getInstance(), "items", "ItemSaved");
        Files stats = new Files(net.danh.ditems.DItems.getInstance(), "stats");
        Files config = new Files(net.danh.ditems.DItems.getInstance(), "config");
        FileFolder cmd = new FileFolder(net.danh.ditems.DItems.getInstance(), "cmd", "Ability");
        message.save();
        message.load();
        items.save();
        items.load();
        stats.save();
        stats.load();
        config.save();
        config.load();
        cmd.save();
        cmd.load();
    }

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
