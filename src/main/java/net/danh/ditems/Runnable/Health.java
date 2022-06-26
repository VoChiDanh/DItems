package net.danh.ditems.Runnable;

import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Health extends BukkitRunnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            PlayerData.updateHealth(p);
        }
    }
}
