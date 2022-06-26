package net.danh.ditems.Listeners;

import net.danh.dcore.Calculator.Calculator;
import net.danh.dcore.Random.Number;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.PlayerData.PlayerData;
import net.danh.ditems.Resource.Files;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class DamageEvent implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity target = e.getEntity();
        Entity killer = e.getDamager();
        if (killer instanceof Player && !(target instanceof Player)) {
            Player p = (Player) killer;
            ItemStack item = p.getInventory().getItemInMainHand();
            if (new NBTItem(item).hasData()) {
                if (new NBTItem(item).hasStats("DAMAGE")) {
                    e.setDamage(new NBTItem(item).getStats("DAMAGE"));
                }
            }
        }
        if (killer instanceof Player && target instanceof Player) {
            Player k = ((Player) killer).getPlayer();
            Player t = ((Player) target).getPlayer();
            if (k != null && t != null) {
                ItemStack item = k.getInventory().getItemInMainHand();
                ItemStack helmet = t.getInventory().getHelmet();
                ItemStack chestplate = t.getInventory().getChestplate();
                ItemStack leggings = t.getInventory().getLeggings();
                ItemStack boots = t.getInventory().getBoots();
                if (new NBTItem(item).hasData()) {
                    int crit_chance = (int) new NBTItem(item).getStats("CRIT_CHANCE");
                    int crit_damage = (int) new NBTItem(item).getStats("CRIT_DAMAGE");
                    int chance = Number.getRandomInt(1, 100);
                    if (chance > crit_chance) {
                        if (new NBTItem(item).hasStats("DAMAGE")) {
                            if (helmet == null && chestplate == null && leggings == null && boots == null) {
                                e.setDamage(new NBTItem(item).getStats("DAMAGE"));
                            } else {
                                int damage = (int) new NBTItem(item).getStats("DAMAGE");
                                int armor = PlayerData.getArmorStats(t, "ARMOR");
                                String calculator = Calculator.calculator(Objects.requireNonNull(new Files("stats").getConfig().getString("FORMULA.DAMAGE"))
                                        .replaceAll("#damage#", String.valueOf(damage))
                                        .replaceAll("#armor#", String.valueOf(armor)), 0);
                                double d_damage = Double.parseDouble(calculator);
                                int f_damage = (int) d_damage;
                                if (f_damage > 0) {
                                    e.setDamage(f_damage);
                                } else {
                                    e.setDamage(1);
                                }
                            }
                        }
                    } else {
                        if (new NBTItem(item).hasStats("DAMAGE")) {
                            if (helmet == null && chestplate == null && leggings == null && boots == null) {
                                e.setDamage(new NBTItem(item).getStats("DAMAGE") * crit_damage);
                            } else {
                                int damage = (int) new NBTItem(item).getStats("DAMAGE") * crit_damage;
                                int armor = PlayerData.getArmorStats(t, "ARMOR");
                                String calculator = Calculator.calculator(Objects.requireNonNull(new Files("stats").getConfig().getString("FORMULA.DAMAGE"))
                                        .replaceAll("#damage#", String.valueOf(damage))
                                        .replaceAll("#armor#", String.valueOf(armor)), 0);
                                double d_damage = Double.parseDouble(calculator);
                                int f_damage = (int) d_damage;
                                if (f_damage > 0) {
                                    e.setDamage(f_damage);
                                } else {
                                    e.setDamage(1);
                                }
                            }
                        }
                    }
                }
            } else {
                e.setDamage(0);
            }
        }
    }
}
