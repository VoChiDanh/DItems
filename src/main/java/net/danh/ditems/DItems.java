package net.danh.ditems;

import net.danh.dcore.DCore;
import net.danh.dcore.Utils.File;
import net.danh.ditems.Listeners.DamageEvent;
import net.danh.ditems.Resource.Files;
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
        new Files("stats").load();
        new Files("message").load();
        File.updateFile(this, new Files("stats").getConfig(), "stats.yml");
        File.updateFile(this, new Files("message").getConfig(), "message.yml");
        new net.danh.ditems.Commands.DItems(this);
    }

    @Override
    public void onDisable() {
        new Files("stats").save();
        new Files("message").save();
    }
}
