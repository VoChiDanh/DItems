package net.danh.ditems.PlayerData;

import net.danh.dcore.Calculator.Calculator;
import net.danh.ditems.Manager.NBTItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.danh.ditems.Resource.Resource.getConfig;

public class PlayerData {

    /**
     * @param p Player
     * @return Player Level
     */
    public static int getLevel(Player p) {
        if (Objects.requireNonNull(getConfig().getString("SETTINGS.LEVEL")).equalsIgnoreCase("VANILLA")) {
            return p.getLevel();
        }
        return 0;
    }

    /**
     * @param p Player
     */
    public static void updateHealth(Player p) {
        AttributeInstance attribute = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        p.setHealthScale(20);
        p.setHealthScaled(true);
        if (attribute != null) {
            attribute.setBaseValue(20 + PlayerData.getPlayerStats(p, "MAX_HEALTH"));
            if (p.getHealth() > attribute.getBaseValue()) {
                p.setHealth(attribute.getBaseValue());
            }
        }
    }

    /**
     * @param p          Player
     * @param stats_name Stats Name
     * @return Amount of stats from helmet, chestplate, leggings, boots
     */
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
            if (new NBTItem(helmet).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(helmet).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(helmet).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(helmet).getDoubleStats(stats_name.toUpperCase());
                        helmet_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(helmet).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(helmet).getDoubleStats(stats_name.toUpperCase());
                    helmet_stats += stats;
                }
            }
        }
        if (chestplate != null) {
            if (new NBTItem(chestplate).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(chestplate).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(chestplate).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(chestplate).getDoubleStats(stats_name.toUpperCase());
                        chestplate_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(chestplate).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(chestplate).getDoubleStats(stats_name.toUpperCase());
                    chestplate_stats += stats;
                }
            }
        }
        if (leggings != null) {
            if (new NBTItem(leggings).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(leggings).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(leggings).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(leggings).getDoubleStats(stats_name.toUpperCase());
                        leggings_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(leggings).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(leggings).getDoubleStats(stats_name.toUpperCase());
                    leggings_stats += stats;
                }
            }
        }
        if (boots != null) {
            if (new NBTItem(boots).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(boots).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(boots).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(boots).getDoubleStats(stats_name.toUpperCase());
                        boots_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(boots).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(boots).getDoubleStats(stats_name.toUpperCase());
                    boots_stats += stats;
                }
            }
        }
        return Integer.parseInt(Calculator.calculator(String.valueOf((int) (helmet_stats + chestplate_stats + leggings_stats + boots_stats)), 0));
    }

    /**
     * @param p          Player
     * @param stats_name Stats Name
     * @return Amount of stats from helmet, chestplate, leggings, boots, main hand
     */
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
            if (new NBTItem(helmet).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(helmet).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(helmet).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(helmet).getDoubleStats(stats_name.toUpperCase());
                        helmet_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(helmet).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(helmet).getDoubleStats(stats_name.toUpperCase());
                    helmet_stats += stats;
                }
            }
        }
        if (chestplate != null) {
            if (new NBTItem(chestplate).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(chestplate).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(chestplate).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(chestplate).getDoubleStats(stats_name.toUpperCase());
                        chestplate_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(chestplate).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(chestplate).getDoubleStats(stats_name.toUpperCase());
                    chestplate_stats += stats;
                }
            }
        }
        if (leggings != null) {
            if (new NBTItem(leggings).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(leggings).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(leggings).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(leggings).getDoubleStats(stats_name.toUpperCase());
                        leggings_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(leggings).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(leggings).getDoubleStats(stats_name.toUpperCase());
                    leggings_stats += stats;
                }
            }
        }
        if (boots != null) {
            if (new NBTItem(boots).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(boots).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(boots).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(boots).getDoubleStats(stats_name.toUpperCase());
                        boots_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(boots).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(boots).getDoubleStats(stats_name.toUpperCase());
                    boots_stats += stats;
                }
            }
        }
        if (item.getType() != Material.AIR) {
            if (new NBTItem(item).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(item).getDoubleStats("REQUIRED_LEVEL") <= getLevel(p)) {
                    if (new NBTItem(item).hasDoubleStats(stats_name.toUpperCase())) {
                        double stats = new NBTItem(item).getDoubleStats(stats_name.toUpperCase());
                        item_stats += stats;
                    }
                }
            } else {
                if (new NBTItem(item).hasDoubleStats(stats_name.toUpperCase())) {
                    double stats = new NBTItem(item).getDoubleStats(stats_name.toUpperCase());
                    item_stats += stats;
                }
            }
        }
        return Integer.parseInt(Calculator.calculator(String.valueOf((int) (helmet_stats + chestplate_stats + leggings_stats + boots_stats + item_stats)), 0));
    }

}