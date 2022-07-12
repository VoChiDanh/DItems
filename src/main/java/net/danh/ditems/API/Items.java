package net.danh.ditems.API;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.Resource.FileFolder;
import net.danh.ditems.Resource.Files;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Objects;

public class Items {

    /**
     * @param p      Player
     * @param key    Item Key
     * @param amount Amount
     */
    public static void loadItems(Player p, String key, Integer amount) {
        FileFolder get = new FileFolder("items", "ItemSaved");
        if (get.getConfig().getConfigurationSection(key) != null) {
            Material material = Material.getMaterial(Objects.requireNonNull(get.getConfig().getString(key + ".Material")));
            ItemStack item;
            if (material != null) {
                item = new ItemStack(material, amount);
            } else {
                item = new ItemStack(Material.STONE, 1);
            }
            NBTItem nbt = new NBTItem(item);
            nbt.setID(key);
            if (get.getConfig().getConfigurationSection(key + ".Enchantments") != null) {
                for (String enchant : Objects.requireNonNull(get.getConfig().getConfigurationSection(key + ".Enchantments")).getKeys(false)) {
                    nbt.addEnchants(enchant, get.getConfig().getInt(key + ".Enchantments." + enchant));
                }
            }
            if (get.getConfig().getConfigurationSection(key + ".Stats") != null) {
                for (String stats : Objects.requireNonNull(get.getConfig().getConfigurationSection(key + ".Stats")).getKeys(false)) {
                    nbt.setStats(stats, get.getConfig().getDouble(key + ".Stats." + stats));
                }
            }
            if (get.getConfig().getConfigurationSection(key + ".Flag") != null) {
                for (String flag : Objects.requireNonNull(get.getConfig().getConfigurationSection(key + ".Flag")).getKeys(false)) {
                    nbt.setFlag(ItemFlag.valueOf(flag));
                }
            }
            if (get.getConfig().getBoolean(key + ".Unbreakable")) {
                nbt.setInfinityDurability(true);
            }
            if (get.getConfig().getString(key + ".Name") != null) {
                nbt.setName(get.getConfig().getString(key + ".Name"));
            }
            if (!get.getConfig().getStringList(key + ".Lore").isEmpty()) {
                for (String lore : get.getConfig().getStringList(key + ".Lore")) {
                    nbt.addLore(lore);
                }
            }
            if (get.getConfig().getString(key + ".Custom_Model_Data") != null) {
                nbt.setCustomModelData(get.getConfig().getString(key + ".Custom_Model_Data"));
            }
            p.getInventory().addItem(item);
        }
    }

    /**
     * @param key  Item Key
     * @param item ItemStack
     */
    public static void saveItems(String key, ItemStack item) {
        FileFolder get = new FileFolder("items", "ItemSaved");
        get.getConfig().set(key + ".Material", item.getType().toString());
        Map<Enchantment, Integer> enchants = item.getEnchantments();
        NMSAssistant nms = new NMSAssistant();
        for (Enchantment e : item.getEnchantments().keySet()) {
            if (nms.isVersionLessThanOrEqualTo(12)) {
                get.getConfig().set(key + ".Enchantments." + e.getName(), enchants.get(e));
            } else {
                get.getConfig().set(key + ".Enchantments." + e.getKey(), enchants.get(e));
            }
        }
        for (String stats : Objects.requireNonNull(new Files("stats").getConfig().getConfigurationSection("STATS")).getKeys(false)) {
            NBTItem nbt = new NBTItem(item);
            double level = nbt.getDoubleStats(stats);
            if (nbt.hasDoubleStats(stats) && level > 0d) {
                get.getConfig().set(key + ".Stats." + stats, level);
            }
        }
        if (new NBTItem(item).hasStringListStats("LORE")) {
            get.getConfig().set(key + ".Lore", new NBTItem(item).getStringListStats("LORE"));
        }
        if (item.getDurability() != 0) {
            get.getConfig().set(key + ".Custom_Model_Data", item.getDurability());
        }
        if (item.getItemMeta() != null) {
            for (ItemFlag flag : ItemFlag.values()) {
                if (item.getItemMeta().hasItemFlag(flag)) {
                    get.getConfig().set(key + ".Flag." + flag, true);
                }
            }
            if (nms.isVersionGreaterThanOrEqualTo(14)) {
                if (item.getItemMeta().hasCustomModelData()) {
                    get.getConfig().set(key + ".Custom_Model_Data", item.getItemMeta().getCustomModelData());
                }
            }
            if (item.getItemMeta().isUnbreakable()) {
                get.getConfig().set(key + ".Unbreakable", item.getItemMeta().isUnbreakable());
            }
            get.getConfig().set(key + ".Name", item.getItemMeta().getDisplayName());
        }
        get.save();
    }
}
