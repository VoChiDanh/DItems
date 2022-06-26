package net.danh.ditems.Listeners;

import net.danh.ditems.DItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Bukkit.getScheduler().scheduleSyncDelayedTask(DItems.getInstance(), () -> p.spigot().respawn(), 2);
    }
}
