package net.danh.ditems.Commands;


import net.danh.ditems.API.Items;
import net.danh.ditems.Manager.Check;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.NMS.NMSAssistant;
import net.danh.ditems.Utils.CMDBase;
import net.danh.ditems.Utils.Chat;
import net.danh.ditems.Utils.File;
import net.danh.ditems.Utils.Number;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import java.util.*;

import static net.danh.ditems.API.Items.saveItems;

public class DItemsCMD extends CMDBase {
    public DItemsCMD() {
        super("ditems");
    }

    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("ditems.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    File.getMessage().getStringList("ADMIN.HELP").forEach(s -> p.sendMessage(Chat.colorizewp(s)));
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    File.reloadFiles();
                    p.sendMessage(Chat.colorizewp("&aReload"));
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("unbreakable")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    new NBTItem(item).setInfinityDurability(Boolean.parseBoolean(args[1]));
                }
                if (args[0].equalsIgnoreCase("setflag")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    new NBTItem(item).setFlag(ItemFlag.valueOf(args[1]));
                }
                if (args[0].equalsIgnoreCase("removeflag")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    new NBTItem(item).removeFlag(ItemFlag.valueOf(args[1]));
                }
                if (args[0].equalsIgnoreCase("custom_model_data")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    new NBTItem(item).setCustomModelData(args[1]);
                }
                if (args[0].equalsIgnoreCase("save")) {
                    saveItems(args[1], p.getInventory().getItemInMainHand());
                    p.sendMessage(Chat.colorize("&aSaved item with key " + args[1]));
                }
                if (args[0].equalsIgnoreCase("show")) {
                    if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
                        return;
                    }
                    de.tr7zw.changeme.nbtapi.NBTItem nbtItem = new de.tr7zw.changeme.nbtapi.NBTItem(p.getInventory().getItemInMainHand());
                    p.sendMessage(Chat.colorize(String.valueOf(nbtItem.getDouble("DITEMS_STATS_" + args[1].toUpperCase()))));
                }
                if (args[0].equalsIgnoreCase("removeenchant")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    Enchantment enchant;
                    NMSAssistant nms = new NMSAssistant();
                    if (nms.isVersionLessThanOrEqualTo(12)) {
                        enchant = Enchantment.getByName(args[1]);
                    } else {
                        enchant = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(args[1]));
                    }
                    if (enchant != null) {
                        new NBTItem(item).removeEnchants(enchant);
                    } else {
                        p.sendMessage(Chat.colorize("&c " + args[1] + " is null, Check: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html"));
                    }
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("load")) {
                    String key = args[1];
                    int amount = Number.getInteger(args[2]);
                    Items.loadItems(p, key, amount);
                }
                if (args[0].equalsIgnoreCase("stats")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String stats_name = args[1].toUpperCase();
                    if (Check.isDouble(args[2])) {
                        double amount = Double.parseDouble(args[2]);
                        for (String name : Objects.requireNonNull(File.getStats().getConfigurationSection("STATS")).getKeys(false)) {
                            if (name.equalsIgnoreCase(stats_name)) {
                                new NBTItem(item).setStats(stats_name, amount);
                                p.sendMessage(Chat.colorize(Objects.requireNonNull(File.getMessage().getString("ADMIN.SET_STATS")).replaceAll("#item#", p.getInventory().getItemInMainHand().getType().toString()).replaceAll("#stats_amount#", args[2]).replaceAll("#stats_name#", args[1])));
                            }
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("addenchant")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    Enchantment enchant;
                    NMSAssistant nms = new NMSAssistant();
                    if (nms.isVersionLessThanOrEqualTo(12)) {
                        enchant = Enchantment.getByName(args[1]);
                    } else {
                        enchant = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(args[1]));
                    }
                    if (enchant != null) {
                        new NBTItem(item).addEnchants(enchant, Number.getInteger(args[2]));
                    } else {
                        p.sendMessage(Chat.colorize("&c " + args[1] + " is null, Check: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html"));
                    }
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("removelore")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    int line = Number.getInteger(args[1]);
                    new NBTItem(item).removeLore(line);
                }
            }
            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("setname")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String name = String.join(" ", Arrays.asList(args).subList(1, args.length));
                    new NBTItem(item).setName(name);
                }
                if (args[0].equalsIgnoreCase("addlore")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String lore = String.join(" ", Arrays.asList(args).subList(1, args.length));
                    new NBTItem(item).addLore(lore);
                }
                if (args[0].equalsIgnoreCase("setlore")) {
                    int line = Number.getInteger(args[1]);
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String lore = String.join(" ", Arrays.asList(args).subList(2, args.length));
                    new NBTItem(item).setLore(line, lore);
                }
            }
        }
    }

    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                File.getMessage().getStringList("ADMIN.HELP").forEach(s -> c.sendMessage(Chat.colorizewp(s)));
            }
            if (args[0].equalsIgnoreCase("reload")) {
                File.reloadFiles();
                c.sendMessage(Chat.colorizewp("&aReload"));
            }
        }
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("load")) {
                String key = args[1];
                int amount = Number.getInteger(args[2]);
                Player p = Bukkit.getPlayer(args[3]);
                if (p == null) return;
                Items.loadItems(p, key, amount);
            }
        }
    }

    @Override
    public void execute(CommandSender c, String[] args) {
        if (c instanceof ConsoleCommandSender) {
            consoleexecute((ConsoleCommandSender) c, args);
        }
        if (c instanceof Player) {
            playerexecute((Player) c, args);
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (sender.hasPermission("ditems.admin")) {
            if (args.length == 1) {
                commands.add("setname");
                commands.add("setlore");
                commands.add("addlore");
                commands.add("removelore");
                commands.add("stats");
                commands.add("show");
                commands.add("help");
                commands.add("reload");
                commands.add("addenchant");
                commands.add("removeenchant");
                commands.add("save");
                commands.add("unbreakable");
                commands.add("setflag");
                commands.add("removeflag");
                commands.add("removeflag");
                commands.add("load");
                commands.add("custom_model_data");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("stats") || args[0].equalsIgnoreCase("show")) {
                    StringUtil.copyPartialMatches(args[1], Objects.requireNonNull(File.getStats().getConfigurationSection("STATS")).getKeys(false), completions);
                }
                if (args[0].equalsIgnoreCase("load")) {
                    StringUtil.copyPartialMatches(args[1], File.getItemSaved_Items().getKeys(false), completions);
                }
                if (args[0].equalsIgnoreCase("addenchant") || args[0].equalsIgnoreCase("removeenchant")) {
                    for (Enchantment enchantment : Enchantment.values()) {
                        NMSAssistant nms = new NMSAssistant();
                        if (nms.isVersionGreaterThanOrEqualTo(13)) {
                            StringUtil.copyPartialMatches(args[1], Collections.singleton(enchantment.getKey().getKey()), completions);
                        } else {
                            StringUtil.copyPartialMatches(args[1], Collections.singleton(enchantment.getName()), completions);
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("setflag") || args[0].equalsIgnoreCase("removeflag")) {
                    for (ItemFlag itemFlag : ItemFlag.values()) {
                        StringUtil.copyPartialMatches(args[1], Collections.singleton(itemFlag.name()), completions);
                    }
                }
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
