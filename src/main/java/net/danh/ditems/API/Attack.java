package net.danh.ditems.API;

import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.dcore.Calculator.Calculator;
import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.Chat;
import net.danh.ditems.DItems;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.danh.ditems.Listeners.DamageEvent.getRandomOffset;
import static net.danh.ditems.Listeners.DamageEvent.indicators;
import static net.danh.ditems.Resource.Resource.getStats;

public class Attack {

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/Entity)
     *          Stats: Damage, Crit_Damage, Crit_Chance, Armor
     */
    public static void CritAttack(EntityDamageByEntityEvent e, Player k, Entity t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        int crit_damage = (int) new NBTItem(item).getDoubleStats("CRIT_DAMAGE");
        int crit_chance = (int) new NBTItem(item).getDoubleStats("CRIT_CHANCE");
        if (t instanceof Player) {
            int armor = PlayerData.getArmorStats((Player) t, "ARMOR");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        } else {
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        }
    }

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/Entity)
     *          Stats: Damage, Armor
     */
    public static void NormalAttack(EntityDamageByEntityEvent e, Player k, Entity t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        if (t instanceof Player) {
            int armor = PlayerData.getArmorStats((Player) t, "ARMOR");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        } else {
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        }
    }

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/Entity)
     *          Stats: Damage, Armor, Crit_Damage, Crit_Chance, PvP_Damage (t == Player) / PvE_Damage (t == null)
     */
    public static void PvPCritAttack(EntityDamageByEntityEvent e, Player k, Entity t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        int crit_damage = (int) new NBTItem(item).getDoubleStats("CRIT_DAMAGE");
        int crit_chance = (int) new NBTItem(item).getDoubleStats("CRIT_CHANCE");
        int pvp_damage;
        if (t instanceof Player) {
            int armor = PlayerData.getArmorStats((Player) t, "ARMOR");
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVP_DAMAGE");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_CRIT_ATTACK")).replaceAll("#crit_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        } else {
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVE_DAMAGE");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_CRIT_ATTACK")).replaceAll("#crit_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        }
    }

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/Entity)
     *          Stats: Damage, Armor, PvP_Damage (t == Player) / PvE_Damage (t == null)
     */
    public static void PvPNormalAttack(EntityDamageByEntityEvent e, Player k, Entity t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        int pvp_damage;
        if (t instanceof Player) {
            int armor = PlayerData.getArmorStats((Player) t, "ARMOR");
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVP_DAMAGE");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_NORMAL_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        } else {
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVE_DAMAGE");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_NORMAL_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
                if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE") && !new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.SUPPORT_OTHER_PLUGIN")) {
                    Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                    k.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setSmall(true);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                        indicators.put(armorStand, 30);
                    });
                }
            }
        }
    }
}
