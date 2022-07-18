package net.danh.ditems;

import net.danh.dcore.DCore;
import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Resource.FileFolder;
import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.File;
import net.danh.ditems.Compatible.PlaceholderAPI;
import net.danh.ditems.Listeners.*;
import net.danh.ditems.Runnable.Health;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import static net.danh.ditems.Listeners.DamageEvent.indicators;


public final class DItems extends JavaPlugin {

    private static DItems INSTANCE;
    final Set<Entity> stands = indicators.keySet();
    final List<Entity> removal = new ArrayList<>();

    public static DItems getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            DCore.dCoreLog("&aEnable mode compatible with &bPlaceholderAPI");
            new PlaceholderAPI().register();
        }
        DCore.RegisterDCore(this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorEquip(), this);
        getServer().getPluginManager().registerEvents(new HealthRegen(), this);
        getServer().getPluginManager().registerEvents(new EquipArmor(), this);
        getServer().getPluginManager().registerEvents(new Interact(), this);
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionGreaterThanOrEqualTo(13)) {
            getServer().getPluginManager().registerEvents(new BlockDispenseArmor(), this);
        }
        Files message = new Files(this, "message");
        FileFolder items = new FileFolder(this, "items", "ItemSaved");
        FileFolder cmd = new FileFolder(this, "cmd", "Ability");
        Files stats = new Files(this, "stats");
        Files config = new Files(this, "config");
        cmd.load();
        message.load();
        items.load();
        stats.load();
        config.load();
        File.updateFile(DItems.getInstance(), stats.getConfig(), "stats.yml");
        File.updateFile(DItems.getInstance(), message.getConfig(), "message.yml");
        File.updateFile(DItems.getInstance(), config.getConfig(), "config.yml");
        new net.danh.ditems.Commands.DItems(this);
        Health health_runnable = new Health();
        net.danh.ditems.Runnable.HealthRegen healthRegen_runnable = new net.danh.ditems.Runnable.HealthRegen();
        health_runnable.runTaskTimer(this, 0L, 0L);
        healthRegen_runnable.runTaskTimer(this, 0L, 100L);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity stand : stands) {
                    int ticksLeft = indicators.get(stand);
                    if (ticksLeft == 0) {
                        stand.remove();
                        removal.add(stand);
                        continue;
                    }
                    ticksLeft--;
                    indicators.put(stand, ticksLeft);
                }
                removal.forEach(stands::remove);
            }
        }.runTaskTimer(this, 0L, 1L);
    }

    @Override
    public void onDisable() {
        for (Entity stand : stands) {
            stand.remove();
            removal.add(stand);
        }
        removal.forEach(stands::remove);
        new Files(this, "stats").save();
        new Files(this, "message").save();
        new FileFolder(this, "items", "ItemSaved").save();
        new FileFolder(this, "cmd", "Ability").save();
    }
}
