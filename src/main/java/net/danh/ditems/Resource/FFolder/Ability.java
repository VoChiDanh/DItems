package net.danh.ditems.Resource.FFolder;

import net.danh.ditems.DItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Ability {
    private final String name;
    private final JavaPlugin core;
    private final File file;
    private FileConfiguration config;

    public Ability() {
        this.name = "cmd";
        this.core = DItems.getInstance();
        this.file = new File(core.getDataFolder(), "Ability" + File.separator + name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        File file = new File(this.core.getDataFolder(), "Ability" + File.separator + this.name + ".yml");
        if (!file.exists()) {
            try {
                this.core.saveResource("Ability" + File.separator + this.name + ".yml", false);
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