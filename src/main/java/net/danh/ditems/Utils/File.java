package net.danh.ditems.Utils;

import com.tchristofferson.configupdater.ConfigUpdater;
import net.danh.ditems.DItems;
import net.xconfig.bukkit.model.SimpleConfigurationManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class File {

    public static SimpleConfigurationManager getFileSetting() {
        return SimpleConfigurationManager.get();
    }

    public static FileConfiguration getConfig() {
        return getFileSetting().get("config.yml");
    }

    public static FileConfiguration getMessage() {
        return getFileSetting().get("message.yml");
    }

    public static FileConfiguration getStats() {
        return getFileSetting().get("stats.yml");
    }


    public static FileConfiguration getItemSaved_Items() {
        return getFileSetting().get("ItemSaved/items.yml");
    }

    public static void saveConfig() {
        getFileSetting().save("config.yml");
    }

    public static void saveMessage() {
        getFileSetting().save("message.yml");
    }

    public static void saveStats() {
        getFileSetting().save("stats.yml");
    }


    public static void saveItemSaved_Items() {
        getFileSetting().save("ItemSaved/items.yml");
    }

    public static void loadFiles() {
        getFileSetting().build("", false, "config.yml", "message.yml", "stats.yml", "ItemSaved/items.yml");
        updateConfig();
        updateMessage();
    }

    public static void reloadFiles() {
        getFileSetting().reload("config.yml", "message.yml", "stats.yml", "ItemSaved/items.yml");
    }

    public static void saveFiles() {
        getFileSetting().save("config.yml", "message.yml", "stats.yml", "ItemSaved/items.yml");
    }

    public static void updateConfig() {
        DItems.getDItems().getLogger().log(Level.WARNING, "Your config is updating...");
        getFileSetting().save("config.yml");
        java.io.File configFile = new java.io.File(DItems.getDItems().getDataFolder(), "config.yml");
        try {
            ConfigUpdater.update(DItems.getDItems(), "config.yml", configFile, "CLICK_TYPE");
            DItems.getDItems().getLogger().log(Level.WARNING, "Your config have been updated successful");
        } catch (IOException e) {
            DItems.getDItems().getLogger().log(Level.WARNING, "Can not update config by it self, please backup and rename your config then restart to get newest config!!");
            e.printStackTrace();
        }
        getFileSetting().reload("config.yml");
    }

    public static void updateMessage() {
        DItems.getDItems().getLogger().log(Level.WARNING, "Your message is updating...");
        getFileSetting().save("message.yml");
        java.io.File configFile = new java.io.File(DItems.getDItems().getDataFolder(), "message.yml");
        FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(DItems.getDItems().getResource("message.yml")), StandardCharsets.UTF_8));
        FileConfiguration currentConfig = YamlConfiguration.loadConfiguration(configFile);
        List<String> default_admin_help = defaultConfig.getStringList("admin.help");
        List<String> default_user_help = defaultConfig.getStringList("user.help");
        List<String> current_admin_help = currentConfig.getStringList("admin.help");
        List<String> current_user_help = currentConfig.getStringList("user.help");
        if (default_admin_help.size() != current_admin_help.size()) {
            getConfig().set("admin.help", default_admin_help);
            getFileSetting().save("message.yml");
        }
        if (default_user_help.size() != current_user_help.size()) {
            getConfig().set("user.help", default_user_help);
            getFileSetting().save("message.yml");
        }
        try {
            ConfigUpdater.update(DItems.getDItems(), "message.yml", configFile);
            DItems.getDItems().getLogger().log(Level.WARNING, "Your message have been updated successful");
        } catch (IOException e) {
            DItems.getDItems().getLogger().log(Level.WARNING, "Can not update message by it self, please backup and rename your message then restart to get newest message!!");
            e.printStackTrace();
        }
        getFileSetting().reload("message.yml");
    }
}
