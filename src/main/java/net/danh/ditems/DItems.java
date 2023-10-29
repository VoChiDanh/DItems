package net.danh.ditems;

import net.danh.ditems.Commands.DItemsCMD;
import net.danh.ditems.Compatible.PlaceholderAPI;
import net.danh.ditems.Listeners.*;
import net.danh.ditems.NMS.NMSAssistant;
import net.danh.ditems.Runnable.Health;
import net.danh.ditems.Utils.File;
import net.xconfig.bukkit.model.SimpleConfigurationManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class DItems extends JavaPlugin {

    private static DItems dItems;

    public static DItems getDItems() {
        return dItems;
    }

    @Override
    public void onEnable() {
        dItems = this;
        SimpleConfigurationManager.register(dItems);
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            getLogger().info("Loaded system compatible with PlaceholderAPI");
            new PlaceholderAPI().register();
        }
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorEquip(), this);
        getServer().getPluginManager().registerEvents(new HealthRegen(), this);
        getServer().getPluginManager().registerEvents(new EquipArmor(), this);
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionGreaterThanOrEqualTo(13)) {
            getServer().getPluginManager().registerEvents(new BlockDispenseArmor(), this);
        }
        File.loadFiles();
        new DItemsCMD();
        Health health_runnable = new Health();
        net.danh.ditems.Runnable.HealthRegen healthRegen_runnable = new net.danh.ditems.Runnable.HealthRegen();
        health_runnable.runTaskTimer(this, 0L, 0L);
        healthRegen_runnable.runTaskTimer(this, 0L, 100L);
    }

    @Override
    public void onDisable() {
        File.saveFiles();
    }
}
