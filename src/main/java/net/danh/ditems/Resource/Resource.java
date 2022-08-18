package net.danh.ditems.Resource;

import net.danh.dcore.Resource.Files;
import net.danh.ditems.DItems;
import net.danh.ditems.Resource.FFolder.Ability;
import net.danh.ditems.Resource.FFolder.ItemSaved;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author VoChiDanh
 */
public class Resource {

    public static void reloadFiles() {
        Files message = new Files(net.danh.ditems.DItems.getInstance(), "message");
        Files stats = new Files(net.danh.ditems.DItems.getInstance(), "stats");
        Files config = new Files(net.danh.ditems.DItems.getInstance(), "config");
        message.save();
        message.load();
        new ItemSaved().save();
        new ItemSaved().load();
        stats.save();
        stats.load();
        config.save();
        config.load();
        new Ability().save();
        new Ability().load();
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
        Ability file = new Ability();
        file.load();
        return file.getConfig();
    }
}
