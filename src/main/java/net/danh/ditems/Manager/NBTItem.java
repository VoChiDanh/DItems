package net.danh.ditems.Manager;

import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Items;
import net.danh.ditems.Resource.Files;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean isVanilla() {
        return !nbtItem.hasCustomNbtData() && !nbtItem.hasNBTData();
    }

    public double getStats(String stats) {
        return nbtItem.getDouble("DITEMS_STATS_" + stats.toUpperCase());
    }

    public boolean hasStats(String stats) {
        return getStats(stats) >= 1d;
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
        nbtItem.applyNBT(item);
    }

    public void setLore(Integer line, String lore) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        List<String> new_lore;
        if (im.getLore() != null) {
            new_lore = im.getLore();
            if (new_lore.get(line) != null) {
                new_lore.set(line, Chat.colorize(lore));
                im.setLore(new_lore);
                nbtItem.getItem().setItemMeta(im);
                nbtItem.applyNBT(item);
            }
        }
    }

    public void removeLore(Integer line) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        List<String> new_lore;
        if (im != null && im.getLore() != null) {
            new_lore = im.getLore();
            if (new_lore.get(line) != null) {
                new_lore.remove(new_lore.get(line));
                im.setLore(new_lore);
                nbtItem.getItem().setItemMeta(im);
                nbtItem.applyNBT(item);
            }
        }
    }

    public void setStats(String stats, Double number) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        if (im.getLore() == null) {
            im.setLore(Items.Lore(Collections.singletonList(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number)))));
            nbtItem.getItem().setItemMeta(im);
            nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
            nbtItem.applyNBT(item);
        } else {
            List<String> lore = im.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (lore.contains(Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(getStats(stats.toUpperCase())))))) {
                    if (lore.get(i).contains(Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(getStats(stats.toUpperCase())))))) {
                        lore.set(i, Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                        im.setLore(lore);
                        nbtItem.getItem().setItemMeta(im);
                        nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                        nbtItem.applyNBT(item);
                        break;
                    }
                } else {
                    lore.add(Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                    im.setLore(lore);
                    nbtItem.getItem().setItemMeta(im);
                    nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                    nbtItem.applyNBT(item);
                    break;
                }
            }
        }
    }
}
