package net.danh.ditems.Listeners;

import net.danh.dcore.Random.Number;
import net.danh.ditems.API.Attack;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageEvent implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity target = e.getEntity();
        Entity killer = e.getDamager();
        if (killer instanceof Player) {
            Player k = ((Player) killer).getPlayer();
            if (k == null) return;
            ItemStack item = k.getInventory().getItemInMainHand();
            if (new NBTItem(item).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(item).getDoubleStats("REQUIRED_LEVEL") > PlayerData.getLevel(k)) {
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                }
            }
            int crit_chance = (int) new NBTItem(item).getDoubleStats("CRIT_CHANCE");
            int chance = Number.getRandomInt(1, 100);
            if (target instanceof Player) {
                Player t = ((Player) target).getPlayer();
                if (t != null) {
                    if (new NBTItem(item).hasData()) {
                        if (!new NBTItem(item).hasDoubleStats("PVP_DAMAGE")) {
                            if (chance > crit_chance) {
                                Attack.NormalAttack(e, k, t);
                            } else {
                                Attack.CritAttack(e, k, t);
                            }
                        } else {
                            if (chance > crit_chance) {
                                Attack.PvPNormalAttack(e, k, t);
                            } else {
                                Attack.PvPCritAttack(e, k, t);
                            }
                        }
                    }
                }
            } else if (target instanceof Monster || target instanceof Animals) {
                if (chance > crit_chance) {
                    Attack.NormalAttack(e, k, null);
                } else {
                    Attack.CritAttack(e, k, null);
                }
            } else {
                if (chance > crit_chance) {
                    Attack.PvPNormalAttack(e, k, null);
                } else {
                    Attack.PvPCritAttack(e, k, null);
                }
            }
        }
    }
}

