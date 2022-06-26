package net.danh.ditems.Runnable;

import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthRegen extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            AttributeInstance pa = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            double health = p.getHealth();
            double maxHealth = pa != null ? pa.getBaseValue() : 0;
            double stats = PlayerData.getPlayerStats(p, "HEALTH_REGEN");
            double newHealth;
            if (stats > 1) {
                newHealth = (health + stats);
            } else {
                newHealth = (health + 1);
            }
            if (health < maxHealth) {
                p.setHealth(Math.min(newHealth, maxHealth));
            }
        }
    }
}
