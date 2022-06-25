package net.danh.ditems.Listeners;

import net.danh.ditems.Manager.NBTItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageEvent implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity killer = e.getDamager();
        if (killer instanceof Player) {
            Player p = (Player) killer;
            ItemStack item = p.getInventory().getItemInMainHand();
            if (item == null) {
                return;
            }
            if (!new NBTItem(item).isVanilla()) {
                if (new NBTItem(item).hasStats("DAMAGE")) {
                    e.setDamage(new NBTItem(item).getStats("DAMAGE"));
                }
            }
        }
    }
}
