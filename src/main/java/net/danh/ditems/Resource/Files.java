package net.danh.ditems.Resource;

import net.danh.ditems.DItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Files {

    private final String name;
    private File file;
    private FileConfiguration config;

    public Files(String name) {
        this.name = name;
        this.file = new File(DItems.getInstance().getDataFolder(), name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        this.file = new File(DItems.getInstance().getDataFolder(), this.name + ".yml");
        if (!this.file.exists()) {
            try {
                DItems.getInstance().saveResource(this.name + ".yml", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
