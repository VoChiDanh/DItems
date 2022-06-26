package net.danh.ditems;

import net.danh.dcore.DCore;
import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Utils.File;
import net.danh.ditems.Listeners.*;
import net.danh.ditems.Resource.Files;
import net.danh.ditems.Runnable.Health;
import org.bukkit.plugin.java.JavaPlugin;

public final class DItems extends JavaPlugin {

    private static DItems INSTANCE;

    public static DItems getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        DCore.RegisterDCore(this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorEquip(), this);
        getServer().getPluginManager().registerEvents(new HealthRegen(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionGreaterThanOrEqualTo(13)) {
            getServer().getPluginManager().registerEvents(new BlockDispenseArmor(), this);
        }
        new Files("message").load();
        new Files("message").save();
        new Files("stats").load();
        new Files("stats").save();
        File.updateFile(DItems.getInstance(), new Files("stats").getConfig(), "stats.yml");
        File.updateFile(DItems.getInstance(), new Files("message").getConfig(), "message.yml");
        new net.danh.ditems.Commands.DItems(this);
        Health health_runnable = new Health();
        net.danh.ditems.Runnable.HealthRegen healthRegen_runnable = new net.danh.ditems.Runnable.HealthRegen();
        health_runnable.runTaskTimer(this, 0L, 0L);
        healthRegen_runnable.runTaskTimer(this, 0L, 100L);
    }

    @Override
    public void onDisable() {
        new Files("stats").save();
        new Files("message").save();
    }
}
