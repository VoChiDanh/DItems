package net.danh.ditems.Resource;

import net.danh.ditems.DItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileFolder {

    private final String name;
    private final String foldername;
    private File file;
    private FileConfiguration config;

    public FileFolder(String name, String foldername) {
        this.name = name;
        this.foldername = foldername;
        this.file = new File(DItems.getInstance().getDataFolder() + File.separator + foldername, name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        File folder = new File(DItems.getInstance().getDataFolder(), foldername);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        this.file = new File(folder, this.name + ".yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
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
