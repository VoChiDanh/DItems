package net.danh.ditems.Resource;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class FileFolder {
    private final String name;
    private final String foldername;
    private final JavaPlugin core;
    private final File file;
    private FileConfiguration config;

    public FileFolder(JavaPlugin core, String name, String foldername) {
        this.name = name;
        this.foldername = foldername;
        this.core = core;
        this.file = new File(core.getDataFolder(), foldername + File.separator + name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        File file = new File(this.core.getDataFolder(), this.foldername + File.separator + this.name + ".yml");
        if (!file.exists()) {
            try {
                this.core.saveResource(this.file.getPath(), false);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public String getName() {
        return this.name;
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
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
