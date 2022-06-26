package net.danh.ditems.PlayerData;

import net.danh.dcore.Calculator.Calculator;
import net.danh.ditems.Manager.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Armor {
    public static int getArmor(Player p) {
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        double helmet_stats = 0d;
        double chestplate_stats = 0d;
        double leggings_stats = 0d;
        double boots_stats = 0d;
        if (helmet != null) {
            if (new NBTItem(helmet).hasStats("ARMOR")) {
                double stats = new NBTItem(helmet).getStats("ARMOR");
                helmet_stats += stats;
            }
        }
        if (chestplate != null) {
            if (new NBTItem(chestplate).hasStats("ARMOR")) {
                double stats = new NBTItem(chestplate).getStats("ARMOR");
                chestplate_stats += stats;
            }
        }
        if (leggings != null) {
            if (new NBTItem(leggings).hasStats("ARMOR")) {
                double stats = new NBTItem(leggings).getStats("ARMOR");
                leggings_stats += stats;
            }
        }
        if (boots != null) {
            if (new NBTItem(boots).hasStats("ARMOR")) {
                double stats = new NBTItem(boots).getStats("ARMOR");
                boots_stats += stats;
            }
        }
        return Integer.parseInt(Calculator.calculator(String.valueOf((int) (helmet_stats + chestplate_stats + leggings_stats + boots_stats)), 0));
    }

}