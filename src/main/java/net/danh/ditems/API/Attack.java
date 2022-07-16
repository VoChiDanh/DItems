package net.danh.ditems.API;

import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.dcore.Calculator.Calculator;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.danh.ditems.Resource.Resource.getStats;

public class Attack {

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/null)
     *          Stats: Damage, Crit_Damage, Crit_Chance, Armor
     */
    public static void CritAttack(EntityDamageByEntityEvent e, Player k, Player t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        int crit_damage = (int) new NBTItem(item).getDoubleStats("CRIT_DAMAGE");
        int crit_chance = (int) new NBTItem(item).getDoubleStats("CRIT_CHANCE");
        if (t != null) {
            int armor = PlayerData.getArmorStats(t, "ARMOR");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        } else {
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        }
    }

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/null)
     *          Stats: Damage, Armor
     */
    public static void NormalAttack(EntityDamageByEntityEvent e, Player k, Player t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        if (t != null) {
            int armor = PlayerData.getArmorStats(t, "ARMOR");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        } else {
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        }
    }

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/null)
     *          Stats: Damage, Armor, Crit_Damage, Crit_Chance, PvP_Damage (t == Player) / PvE_Damage (t == null)
     */
    public static void PvPCritAttack(EntityDamageByEntityEvent e, Player k, Player t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        int crit_damage = (int) new NBTItem(item).getDoubleStats("CRIT_DAMAGE");
        int crit_chance = (int) new NBTItem(item).getDoubleStats("CRIT_CHANCE");
        int pvp_damage;
        if (t != null) {
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVP_DAMAGE");
            int armor = PlayerData.getArmorStats(t, "ARMOR");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_CRIT_ATTACK")).replaceAll("#crit_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        } else {
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVE_DAMAGE");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_CRIT_ATTACK")).replaceAll("#crit_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.CRIT_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#crit_damage#", String.valueOf(crit_damage)).replaceAll("#crit_chance#", String.valueOf(crit_chance)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        }
    }

    /**
     * @param e EntityDamageByEntityEvent
     * @param k Killer (Player)
     * @param t Target (Player/null)
     *          Stats: Damage, Armor, PvP_Damage (t == Player) / PvE_Damage (t == null)
     */
    public static void PvPNormalAttack(EntityDamageByEntityEvent e, Player k, Player t) {
        ItemStack item = k.getInventory().getItemInMainHand();
        int damage = (int) new NBTItem(item).getDoubleStats("DAMAGE");
        int pvp_damage;
        if (t != null) {
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVP_DAMAGE");
            int armor = PlayerData.getArmorStats(t, "ARMOR");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_NORMAL_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(armor)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#armor#", String.valueOf(armor)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        } else {
            pvp_damage = (int) new NBTItem(item).getDoubleStats("PVE_DAMAGE");
            if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                String calculator = Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.PVP_NORMAL_ATTACK")).replaceAll("#normal_attack#", Calculator.calculator(Objects.requireNonNull(getStats().getString("FORMULA.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#armor#", String.valueOf(1)), 0)).replaceAll("#damage#", String.valueOf(damage)).replaceAll("#pvp_damage#", String.valueOf(pvp_damage)).replaceAll("#armor#", String.valueOf(1)), 0);
                String papi = PlaceholderAPI.setPlaceholders(k, calculator);
                double d_damage = Double.parseDouble(papi);
                int f_damage = (int) d_damage;
                e.setDamage(Math.max(f_damage, 0));
            }
        }
    }
}
