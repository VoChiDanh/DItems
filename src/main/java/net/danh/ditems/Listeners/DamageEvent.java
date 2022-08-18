package net.danh.ditems.Listeners;

import net.danh.dcore.Random.Number;
import net.danh.ditems.API.Attack;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity killer = e.getDamager();
        if (killer instanceof Player) {
            Player k = ((Player) killer).getPlayer();
            if (k == null) return;
            ItemStack item = k.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR) return;
            if (new NBTItem(item).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(item).getDoubleStats("REQUIRED_LEVEL") > PlayerData.getLevel(k)) {
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                }
            }
            int crit_chance = (int) new NBTItem(item).getDoubleStats("CRIT_CHANCE");
            int chance = Number.getRandomInt(1, 100);
            if (new NBTItem(item).hasData()) {
                if (e.getEntity() instanceof Player) {
                    if (new NBTItem(item).hasDoubleStats("PVP_DAMAGE")) {
                        if (chance >= crit_chance) {
                            Attack.PvPNormalAttack(e);
                        } else {
                            Attack.PvPCritAttack(e);
                        }
                    } else {
                        if (chance >= crit_chance) {
                            Attack.NormalAttack(e);
                        } else {
                            Attack.CritAttack(e);
                        }
                    }
                }
                if (e.getEntity() instanceof Monster || e.getEntity() instanceof Animals) {
                    if (new NBTItem(item).hasDoubleStats("PVE_DAMAGE")) {
                        if (chance >= crit_chance) {
                            Attack.PvENormalAttack(e);
                        } else {
                            Attack.PvECritAttack(e);
                        }
                    } else {
                        if (chance >= crit_chance) {
                            Attack.NormalAttack(e);
                        } else {
                            Attack.CritAttack(e);
                        }
                    }

                }
            }
        }
    }
}

