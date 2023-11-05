package net.danh.ditems.Manager;

import net.danh.ditems.NMS.NMSAssistant;
import net.danh.ditems.Utils.Chat;
import net.danh.ditems.Utils.File;
import net.danh.ditems.Utils.Items;
import net.danh.ditems.Utils.Number;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NBTItem {

    private final ItemStack item;
    private final de.tr7zw.changeme.nbtapi.NBTItem nbtItem;

    public NBTItem(ItemStack item) {
        this.item = item;
        this.nbtItem = new de.tr7zw.changeme.nbtapi.NBTItem(item);
    }

    public ItemStack getItem() {
        return item;
    }

    public String getID() {
        return nbtItem.getString("DITEMS_ID");
    }

    public void setID(String id) {
        nbtItem.setString("DITEMS_ID", id.toUpperCase());
        nbtItem.applyNBT(item);
    }

    public boolean isDItems() {
        return getID() != null;
    }

    public boolean hasData() {
        return nbtItem.hasCustomNbtData() || nbtItem.hasNBTData();
    }

    public double getDoubleStats(String stats) {
        return nbtItem.getItem() != null ? nbtItem.getDouble("DITEMS_STATS_" + stats.toUpperCase()) : 0;
    }

    public boolean hasDoubleStats(String stats) {
        return getDoubleStats(stats) > 0d;
    }

    public List<String> getStringListStats(String stats) {
        return nbtItem.getItem() != null ? nbtItem.getStringList("DITEMS_STATS_" + stats.toUpperCase()) : null;
    }

    public boolean hasStringListStats(String stats) {
        return !nbtItem.getStringList(stats).isEmpty();
    }

    public void setName(String name) {
        nbtItem.setString("DITEMS_STATS_NAME", Chat.colorize(name));
        ItemMeta im = nbtItem.getItem().getItemMeta();
        im.setDisplayName(Chat.colorize(name));
        nbtItem.getItem().setItemMeta(im);
        nbtItem.applyNBT(item);
    }

    public void addLore(String lore) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        List<String> new_lore;
        if (im.getLore() == null) {
            new_lore = new ArrayList<>();
        } else {
            new_lore = im.getLore();
        }
        new_lore.add(lore);
        im.setLore(Items.Lore(new_lore));
        nbtItem.getItem().setItemMeta(im);
        nbtItem.getStringList("DITEMS_STATS_LORE").add(Chat.colorize(lore));
        nbtItem.getStringList("DITEMS_STATS_LORE_LINE").add("LINE;" + (new_lore.size()));
        nbtItem.applyNBT(item);
    }

    public void setLore(Integer line, String lore) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        List<String> new_lore;
        if (im.getLore() != null) {
            new_lore = im.getLore();
            if (new_lore.get(Number.getInteger(nbtItem.getStringList("DITEMS_STATS_LORE_LINE").get(line).split(";")[1])) != null) {
                new_lore.set(Number.getInteger(nbtItem.getStringList("DITEMS_STATS_LORE_LINE").get(line).split(";")[1]), Chat.colorize(lore));
                im.setLore(new_lore);
                nbtItem.getItem().setItemMeta(im);
                nbtItem.getStringList("DITEMS_STATS_LORE").set(Number.getInteger(nbtItem.getStringList("DITEMS_STATS_LORE_LINE").get(line).split(";")[1]), Chat.colorize(lore));
                nbtItem.applyNBT(item);
            }
        }
    }

    public void removeLore(Player p, Integer line) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        List<String> new_lore;
        if (im != null && im.getLore() != null) {
            new_lore = im.getLore();
            List<String> stats_lore_line = nbtItem.getStringList("DITEMS_STATS_LORE_LINE");
            if ((stats_lore_line.size() - 1) >= line) {
                String lore_line = stats_lore_line.get(line);
                String lore_item = nbtItem.getStringList("DITEMS_STATS_LORE").get(line);
                String last_lore_item = stats_lore_line.get(stats_lore_line.size() - 1);
                int lline = Number.getInteger(lore_line.split(";")[1]) - 1;
                if (new_lore.get(lline) != null) {
                    new_lore.remove(new_lore.get(lline));
                    im.setLore(new_lore);
                    nbtItem.getItem().setItemMeta(im);
                    nbtItem.getStringList("DITEMS_STATS_LORE").remove(lore_item);
                    nbtItem.getStringList("DITEMS_STATS_LORE_LINE").remove(last_lore_item);
                    nbtItem.applyNBT(item);
                }
            } else {
                p.sendMessage(Chat.colorize(Objects.requireNonNull(File.getMessage().getString("ADMIN.OUT_OF_LORE"))
                        .replace("#lol_number#", String.valueOf((stats_lore_line.size() - 1)))));
            }
        }
    }

    public void addEnchants(Enchantment enchantments, Integer level) {
        if (enchantments != null) {
            nbtItem.getItem().addUnsafeEnchantment(enchantments, level);
            nbtItem.applyNBT(item);
        }
    }

    public void removeEnchants(Enchantment enchantments) {
        if (enchantments != null && nbtItem.getItem().containsEnchantment(enchantments)) {
            nbtItem.getItem().removeEnchantment(enchantments);
            nbtItem.applyNBT(item);
        }
    }

    public void setStats(String stats, Double number) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        if (im.getLore() == null) {
            im.setLore(Items.Lore(Collections.singletonList(File.getStats().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number)))));
            nbtItem.getItem().setItemMeta(im);
            nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
            nbtItem.applyNBT(item);
        } else {
            List<String> lore = im.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (lore.contains(Chat.colorize(File.getStats().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(getDoubleStats(stats.toUpperCase())))))) {
                    if (lore.get(i).contains(Chat.colorize(File.getStats().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(getDoubleStats(stats.toUpperCase())))))) {
                        lore.set(i, Chat.colorize(File.getStats().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                        im.setLore(lore);
                        nbtItem.getItem().setItemMeta(im);
                        nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                        nbtItem.applyNBT(item);
                        break;
                    }
                } else {
                    lore.add(Chat.colorize(File.getStats().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                    im.setLore(lore);
                    nbtItem.getItem().setItemMeta(im);
                    nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                    nbtItem.applyNBT(item);
                    break;
                }
            }
        }
    }

    public void setInfinityDurability(Boolean type) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        im.setUnbreakable(type);
        nbtItem.getItem().setItemMeta(im);
        nbtItem.applyNBT(item);
    }

    public void setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) nbtItem.getItem().getItemMeta();
            im.setColor(color);
            nbtItem.getItem().setItemMeta(im);
            nbtItem.applyNBT(item);
        } catch (ClassCastException expected) {
            throw new RuntimeException(expected);
        }
    }

    public void setFlag(ItemFlag... flag) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        im.addItemFlags(flag);
        nbtItem.getItem().setItemMeta(im);
        nbtItem.applyNBT(item);
    }

    public void removeFlag(ItemFlag... flag) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        im.removeItemFlags(flag);
        nbtItem.getItem().setItemMeta(im);
        nbtItem.applyNBT(item);
    }

    public void setCustomModelData(String data) {
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionGreaterThanOrEqualTo(14)) {
            ItemMeta im = nbtItem.getItem().getItemMeta();
            im.setCustomModelData(Integer.getInteger(data));
            nbtItem.getItem().setItemMeta(im);
        } else {
            nbtItem.getItem().setDurability(Short.parseShort(data));
        }
        nbtItem.applyNBT(item);
    }

}
