package net.danh.ditems.Manager;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Items;
import net.danh.ditems.DItems;
import net.danh.ditems.Resource.Resource;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
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
                nbtItem.getStringList("DITEMS_STATS_LORE").set(line, Chat.colorize(lore));
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
                nbtItem.getStringList("DITEMS_STATS_LORE").remove(new_lore.get(line));
                nbtItem.applyNBT(item);
            }
        }
    }

    public void addEnchants(String enchantments, Integer level) {
        NMSAssistant nms = new NMSAssistant();
        Enchantment enchant;
        if (nms.isVersionLessThanOrEqualTo(12)) {
            enchant = Enchantment.getByName(enchantments.toUpperCase());
        } else {
            enchant = Enchantment.getByKey(new NamespacedKey(DItems.getInstance(), enchantments.toUpperCase()));
        }
        if (enchant != null) {
            nbtItem.getItem().addUnsafeEnchantment(enchant, level);
            nbtItem.applyNBT(item);
        }
    }

    public void removeEnchants(String enchantments) {
        NMSAssistant nms = new NMSAssistant();
        Enchantment enchant;
        if (nms.isVersionLessThanOrEqualTo(12)) {
            enchant = Enchantment.getByName(enchantments.toUpperCase());
        } else {
            enchant = Enchantment.getByKey(new NamespacedKey(DItems.getInstance(), enchantments.toUpperCase()));
        }
        if (enchant != null && nbtItem.getItem().containsEnchantment(enchant)) {
            nbtItem.getItem().removeEnchantment(enchant);
            nbtItem.applyNBT(item);
        }
    }

    public void removeAbilityCommand(String action) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        if (im == null) {
            return;
        }
        if (im.getLore() == null) return;
        List<String> lore = im.getLore();
        for (int i = 0; i < lore.size(); i++) {
            if (action.equals(nbtItem.getString("CLICK"))) {
                if (lore.contains(Chat.colorize(Objects.requireNonNull(Resource.getConfig().getString("SETTINGS.ABILITY"))
                        .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                        .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(nbtItem.getString("CMD") + ".DISPLAY")))
                        .replaceAll("#delay#", String.valueOf(nbtItem.getString("CLICK")))))) {
                    if (lore.get(i).contains(Chat.colorize(Objects.requireNonNull(Resource.getConfig().getString("SETTINGS.ABILITY"))
                            .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                            .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(nbtItem.getString("CMD") + ".DISPLAY")))
                            .replaceAll("#delay#", String.valueOf(nbtItem.getString("CLICK")))))) {
                        lore.remove(lore.get(i));
                        im.setLore(lore);
                        nbtItem.getItem().setItemMeta(im);
                        nbtItem.removeKey(action);
                        nbtItem.applyNBT(item);
                    }
                }
            }
        }
    }


    public void addAbilityCommand(String action, String command, Integer delay) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        if (im.getLore() == null) {
            im.setLore(Items.Lore(Collections.singletonList(Objects.requireNonNull(Resource.getConfig().getString("SETTINGS.ABILITY"))
                    .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                    .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(command + ".DISPLAY")))
                    .replaceAll("#delay#", String.valueOf(delay)))));
            nbtItem.getItem().setItemMeta(im);
            nbtItem.setString("CMD_" + action, command);
            nbtItem.setInteger("DELAY_" + action, delay);
            nbtItem.applyNBT(item);
        } else {
            List<String> lore = im.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (action.equals(nbtItem.getString("CLICK")) && lore.contains(Chat.colorize(Objects.requireNonNull(Resource.getConfig().getString("SETTINGS.ABILITY"))
                        .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                        .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(nbtItem.getString("CMD") + ".DISPLAY")))
                        .replaceAll("#delay#", String.valueOf(nbtItem.getString("DELAY")))))) {
                    if (lore.get(i).contains(Chat.colorize(Objects.requireNonNull(Resource.getConfig().getString("SETTINGS.ABILITY"))
                            .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                            .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(nbtItem.getString("CMD") + ".DISPLAY")))
                            .replaceAll("#delay#", String.valueOf(nbtItem.getString("DELAY")))))) {
                        lore.set(i, Chat.colorize(Objects.requireNonNull(Resource.getConfig().getString("SETTINGS.ABILITY"))
                                .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                                .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(command + ".DISPLAY")))
                                .replaceAll("#delay#", String.valueOf(delay))));
                        im.setLore(lore);
                        nbtItem.getItem().setItemMeta(im);
                        nbtItem.setString("CMD_" + action, command);
                        nbtItem.setInteger("DELAY_" + action, delay);
                        nbtItem.applyNBT(item);
                        break;
                    }
                } else {
                    lore.add(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("SETTINGS.ABILITY"))
                            .replaceAll("#action#", Objects.requireNonNull(Resource.getConfig().getString("CLICK_TYPE." + action)))
                            .replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(command + ".DISPLAY")))
                            .replaceAll("#delay#", String.valueOf(delay)));
                    im.setLore(Items.Lore(lore));
                    nbtItem.getItem().setItemMeta(im);
                    nbtItem.setString("CMD_" + action, command);
                    nbtItem.setInteger("DELAY_" + action, delay);
                    nbtItem.applyNBT(item);
                    break;
                }
            }
        }
    }

    public void setStats(String stats, Double number) {
        ItemMeta im = nbtItem.getItem().getItemMeta();
        if (im.getLore() == null) {
            im.setLore(Items.Lore(Collections.singletonList(new Files(DItems.getInstance(), "stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number)))));
            nbtItem.getItem().setItemMeta(im);
            nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
            nbtItem.applyNBT(item);
        } else {
            List<String> lore = im.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (lore.contains(Chat.colorize(new Files(DItems.getInstance(), "stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(getDoubleStats(stats.toUpperCase())))))) {
                    if (lore.get(i).contains(Chat.colorize(new Files(DItems.getInstance(), "stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(getDoubleStats(stats.toUpperCase())))))) {
                        lore.set(i, Chat.colorize(new Files(DItems.getInstance(), "stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
                        im.setLore(lore);
                        nbtItem.getItem().setItemMeta(im);
                        nbtItem.setDouble("DITEMS_STATS_" + stats.toUpperCase(), number);
                        nbtItem.applyNBT(item);
                        break;
                    }
                } else {
                    lore.add(Chat.colorize(new Files(DItems.getInstance(), "stats").getConfig().getString("STATS." + stats.toUpperCase()).replaceAll("#amount#", String.valueOf(number))));
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
