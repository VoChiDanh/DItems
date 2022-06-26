package net.danh.ditems.PlayerData;

import net.danh.dcore.Calculator.Calculator;
import net.danh.ditems.Manager.NBTItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerData {

    public static void updateHealth(Player p) {
        AttributeInstance attribute = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        p.setHealthScale(20);
        p.setHealthScaled(true);
        if (attribute != null) {
            attribute.setBaseValue(20 + PlayerData.getPlayerStats(p, "MAX_HEALTH"));
        }
    }

    public static int getArmorStats(Player p, String stats_name) {
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        double helmet_stats = 0d;
        double chestplate_stats = 0d;
        double leggings_stats = 0d;
        double boots_stats = 0d;
        if (helmet != null) {
            if (new NBTItem(helmet).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(helmet).getStats(stats_name.toUpperCase());
                helmet_stats += stats;
            }
        }
        if (chestplate != null) {
            if (new NBTItem(chestplate).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(chestplate).getStats(stats_name.toUpperCase());
                chestplate_stats += stats;
            }
        }
        if (leggings != null) {
            if (new NBTItem(leggings).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(leggings).getStats(stats_name.toUpperCase());
                leggings_stats += stats;
            }
        }
        if (boots != null) {
            if (new NBTItem(boots).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(boots).getStats(stats_name.toUpperCase());
                boots_stats += stats;
            }
        }
        return Integer.parseInt(Calculator.calculator(String.valueOf((int) (helmet_stats + chestplate_stats + leggings_stats + boots_stats)), 0));
    }

    public static int getPlayerStats(Player p, String stats_name) {
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        ItemStack item = p.getInventory().getItemInMainHand();
        double helmet_stats = 0d;
        double chestplate_stats = 0d;
        double leggings_stats = 0d;
        double boots_stats = 0d;
        double item_stats = 0d;
        if (helmet != null) {
            if (new NBTItem(helmet).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(helmet).getStats(stats_name.toUpperCase());
                helmet_stats += stats;
            }
        }
        if (chestplate != null) {
            if (new NBTItem(chestplate).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(chestplate).getStats(stats_name.toUpperCase());
                chestplate_stats += stats;
            }
        }
        if (leggings != null) {
            if (new NBTItem(leggings).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(leggings).getStats(stats_name.toUpperCase());
                leggings_stats += stats;
            }
        }
        if (boots != null) {
            if (new NBTItem(boots).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(boots).getStats(stats_name.toUpperCase());
                boots_stats += stats;
            }
        }
        if (item != null && item.getType() != Material.AIR) {
            if (new NBTItem(item).hasStats(stats_name.toUpperCase())) {
                double stats = new NBTItem(item).getStats(stats_name.toUpperCase());
                item_stats += stats;
            }
        }
        return Integer.parseInt(Calculator.calculator(String.valueOf((int) (helmet_stats + chestplate_stats + leggings_stats + boots_stats + item_stats)), 0));
    }

}