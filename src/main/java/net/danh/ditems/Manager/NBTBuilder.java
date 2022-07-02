package net.danh.ditems.Manager;

import de.tr7zw.changeme.nbtapi.NBTItem;
import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Items;
import net.danh.ditems.Resource.Files;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NBTBuilder {

    private final NBTItem nbt;
    private final ItemStack item;

    public NBTBuilder(ItemStack item) {
        nbt = new NBTItem(item);
        this.item = item;
    }

    public NBTBuilder(Material m, int amount) {
        item = new ItemStack(m, amount);
        nbt = new NBTItem(item);
    }

    public NBTBuilder(Material m, int amount, byte durability) {
        item = new ItemStack(m, amount, durability);
        nbt = new NBTItem(item);
    }

    public ItemStack toItemStack() {
        return item;
    }

    public NBTBuilder setDurability(short durability) {
        nbt.getItem().setDurability(durability);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder setName(String name) {
        ItemMeta meta = nbt.getItem().getItemMeta();
        meta.setDisplayName(Chat.colorize(name));
        nbt.setString("DITEMS_STATS_NAME", Chat.colorize(name));
        nbt.getItem().setItemMeta(meta);
        nbt.applyNBT(item);
        return this;
    }


    public NBTBuilder setLore(String... lore) {
        ItemMeta im = nbt.getItem().getItemMeta();
        im.setLore(Items.Lore(Arrays.asList(lore)));
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder setLore(List<String> lore) {
        ItemMeta im = nbt.getItem().getItemMeta();
        im.setLore(Items.Lore(lore));
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder addEnchants(Enchantment enchantment, int level) {
        nbt.getItem().addUnsafeEnchantment(enchantment, level);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder removeEnchants(Enchantment enchantment) {
        nbt.getItem().removeEnchantment(enchantment);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder setInfinityDurability(boolean unbreakable) {
        ItemMeta im = nbt.getItem().getItemMeta();
        im.setUnbreakable(unbreakable);
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder removeLoreLine(int index) {
        ItemMeta im = nbt.getItem().getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        im.setLore(Items.Lore(lore));
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder addLoreLine(String line) {
        ItemMeta im = nbt.getItem().getItemMeta();
        List<String> lore = new ArrayList<>();
        if (im.hasLore()) lore = new ArrayList<>(im.getLore());
        lore.add(line);
        im.setLore(Items.Lore(lore));
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder addLoreLine(int pos, String line) {
        ItemMeta im = nbt.getItem().getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(pos, Chat.colorize(line));
        im.setLore(lore);
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) nbt.getItem().getItemMeta();
            im.setColor(color);
            nbt.getItem().setItemMeta(im);
            nbt.applyNBT(item);
        } catch (ClassCastException expected) {
            throw new RuntimeException(expected);
        }
        return this;
    }

    public NBTBuilder setFlag(ItemFlag... flag) {
        ItemMeta im = nbt.getItem().getItemMeta();
        im.addItemFlags(flag);
        nbt.getItem().setItemMeta(im);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder setID(String id) {
        nbt.setString("DITEMS_ID", id);
        nbt.applyNBT(item);
        return this;
    }

    public NBTBuilder setStats(String stats, double number) {
        ItemMeta im = nbt.getItem().getItemMeta();
        if (im.getLore() == null) {
            im.setLore(Items.Lore(Collections.singletonList(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number)))));
            nbt.getItem().setItemMeta(im);
            nbt.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
            nbt.applyNBT(item);
        } else {
            List<String> lore = im.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (lore.contains(Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(new net.danh.ditems.Manager.NBTItem(item).getStats(stats.toUpperCase())))))) {
                    if (lore.get(i).contains(Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(new net.danh.ditems.Manager.NBTItem(item).getStats(stats.toUpperCase())))))) {
                        lore.set(i, Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                        im.setLore(lore);
                        nbt.getItem().setItemMeta(im);
                        nbt.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                        nbt.applyNBT(item);
                        break;
                    }
                } else {
                    lore.add(Chat.colorize(new Files("stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                    im.setLore(lore);
                    nbt.getItem().setItemMeta(im);
                    nbt.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                    nbt.applyNBT(item);
                    break;
                }
            }
        }
        return this;
    }

}
