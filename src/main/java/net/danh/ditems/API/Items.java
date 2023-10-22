package net.danh.ditems.API;

import net.danh.ditems.DItems;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.NMS.NMSAssistant;
import net.danh.ditems.Utils.File;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Items {

    /**
     * @param p      Player
     * @param key    Item Key
     * @param amount Amount
     */
    public static void loadItems(Player p, String key, Integer amount) {
        FileConfiguration get = File.getItemSaved_Items();
        if (get.getConfigurationSection(key) != null) {
            Material material = Material.getMaterial(Objects.requireNonNull(get.getString(key + ".Material")));
            ItemStack item;
            if (material != null) {
                item = new ItemStack(material, amount);
            } else {
                item = new ItemStack(Material.STONE, 1);
            }
            NBTItem nbt = new NBTItem(item);
            nbt.setID(key);
            if (get.getConfigurationSection(key + ".Enchantments") != null) {
                for (String enchant : Objects.requireNonNull(get.getConfigurationSection(key + ".Enchantments")).getKeys(false)) {
                    nbt.addEnchants(enchant, get.getInt(key + ".Enchantments." + enchant));
                }
            }
            if (get.getConfigurationSection(key + ".Ability") != null) {
                for (String list : Objects.requireNonNull(get.getConfigurationSection(key + ".Ability")).getKeys(false)) {
                    for (String cmd : Objects.requireNonNull(get.getConfigurationSection(key + ".Ability." + list)).getKeys(false)) {
                        Integer delay = get.getInt(key + ".Ability." + list + "." + cmd);
                        nbt.addAbilityCommand(list, cmd, delay);
                    }
                }
            }
            if (get.getConfigurationSection(key + ".Stats") != null) {
                for (String stats : Objects.requireNonNull(get.getConfigurationSection(key + ".Stats")).getKeys(false)) {
                    nbt.setStats(stats, get.getDouble(key + ".Stats." + stats));
                }
            }
            if (get.getConfigurationSection(key + ".Flag") != null) {
                for (String flag : Objects.requireNonNull(get.getConfigurationSection(key + ".Flag")).getKeys(false)) {
                    nbt.setFlag(ItemFlag.valueOf(flag));
                }
            }
            if (get.getBoolean(key + ".Unbreakable")) {
                nbt.setInfinityDurability(true);
            }
            if (get.getString(key + ".Name") != null) {
                nbt.setName(get.getString(key + ".Name"));
            }
            if (!get.getStringList(key + ".Lore").isEmpty()) {
                for (String lore : get.getStringList(key + ".Lore")) {
                    nbt.addLore(lore);
                }
            }
            if (get.getString(key + ".Custom_Model_Data") != null) {
                nbt.setCustomModelData(get.getString(key + ".Custom_Model_Data"));
            }
            p.getInventory().addItem(item);
        }
    }

    /**
     * @param key  Item Key
     * @param item ItemStack
     */
    public static void saveItems(String key, ItemStack item) {
        FileConfiguration get = File.getItemSaved_Items();
        get.set(key + ".Material", item.getType().toString());
        Map<Enchantment, Integer> enchants = item.getEnchantments();
        NMSAssistant nms = new NMSAssistant();
        for (Enchantment e : item.getEnchantments().keySet()) {
            if (nms.isVersionLessThanOrEqualTo(12)) {
                get.set(key + ".Enchantments." + e.getName(), enchants.get(e));
            } else {
                get.set(key + ".Enchantments." + e.getKey(), enchants.get(e));
            }
        }
        for (String stats : Objects.requireNonNull(File.getStats().getConfigurationSection("STATS")).getKeys(false)) {
            NBTItem nbt = new NBTItem(item);
            double level = nbt.getDoubleStats(stats);
            if (nbt.hasDoubleStats(stats) && level > 0d) {
                get.set(key + ".Stats." + stats, level);
            }
        }
        for (String click_type : File.getConfig().getStringList("ACTION")) {
            de.tr7zw.changeme.nbtapi.NBTItem nbtItem = new de.tr7zw.changeme.nbtapi.NBTItem(item);
            if (nbtItem.getString("CLICK_" + click_type) != null && nbtItem.getString("CMD_" + click_type) != null) {
                get.set(key + ".Ability." + click_type + "." + nbtItem.getString("CMD_" + click_type), nbtItem.getInteger("DELAY_" + click_type));
            }
        }
        if (new NBTItem(item).hasStringListStats("LORE")) {
            get.set(key + ".Lore", new NBTItem(item).getStringListStats("LORE"));
        }
        if (item.getDurability() != 0) {
            get.set(key + ".Custom_Model_Data", item.getDurability());
        }
        if (item.getItemMeta() != null) {
            for (ItemFlag flag : ItemFlag.values()) {
                if (item.getItemMeta().hasItemFlag(flag)) {
                    get.set(key + ".Flag." + flag, true);
                }
            }
            if (nms.isVersionGreaterThanOrEqualTo(14)) {
                if (item.getItemMeta().hasCustomModelData()) {
                    get.set(key + ".Custom_Model_Data", item.getItemMeta().getCustomModelData());
                }
            }
            if (item.getItemMeta().isUnbreakable()) {
                get.set(key + ".Unbreakable", item.getItemMeta().isUnbreakable());
            }
            get.set(key + ".Name", item.getItemMeta().getDisplayName());
        }
        try {
            get.save(new java.io.File(DItems.getDItems().getDataFolder(), "ItemSaved" + java.io.File.separator + "items.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
