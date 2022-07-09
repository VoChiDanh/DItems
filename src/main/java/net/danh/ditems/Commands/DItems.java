package net.danh.ditems.Commands;

import net.danh.dcore.Commands.CMDBase;
import net.danh.dcore.NMS.NMSAssistant;
import net.danh.ditems.API.Items;
import net.danh.ditems.Manager.Check;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.Resource.FileFolder;
import net.danh.ditems.Resource.Files;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.*;

import static net.danh.dcore.Random.Number.isInteger;
import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.ditems.API.Items.saveItems;

public class DItems extends CMDBase {
    public DItems(JavaPlugin core) {
        super(core, "ditems");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("ditems.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    sendPlayerMessage(p, new Files("message").getConfig().getStringList("ADMIN.HELP"));
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    Files message = new Files("message");
                    FileFolder items = new FileFolder("items", "ItemSaved");
                    Files stats = new Files("stats");
                    Files config = new Files("config");
                    message.save();
                    message.load();
                    items.save();
                    items.load();
                    stats.save();
                    stats.load();
                    config.save();
                    config.load();
                    sendPlayerMessage(p, "&aReloaded");
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
                if (args[0].equalsIgnoreCase("save")) {
                    saveItems(args[1], p.getInventory().getItemInMainHand());
                    sendPlayerMessage(p, "&aSaved item with key " + args[1]);
                }
                if (args[0].equalsIgnoreCase("show")) {
                    if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
                        return;
                    }
                    de.tr7zw.changeme.nbtapi.NBTItem nbtItem = new de.tr7zw.changeme.nbtapi.NBTItem(p.getInventory().getItemInMainHand());
                    sendPlayerMessage(p, String.valueOf(nbtItem.getDouble("DITEMS_STATS_" + args[1].toUpperCase())));
                }
                if (args[0].equalsIgnoreCase("removeenchant")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String enchantments = args[1].toUpperCase();
                    Enchantment enchant;
                    NMSAssistant nms = new NMSAssistant();
                    if (nms.isVersionLessThanOrEqualTo(12)) {
                        enchant = Enchantment.getByName(enchantments.toUpperCase());
                    } else {
                        enchant = Enchantment.getByKey(new NamespacedKey(net.danh.ditems.DItems.getInstance(), enchantments.toUpperCase()));
                    }
                    if (enchant != null) {
                        new NBTItem(item).removeEnchants(enchantments);
                    } else {
                        sendPlayerMessage(p, "&c " + args[1] + " is null, Check: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html");
                    }
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("load")) {
                    String key = args[1];
                    if (isInteger(args[2])) {
                        int amount = Integer.parseInt(args[2]);
                        Items.loadItems(p, key, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("stats")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String stats_name = args[1].toUpperCase();
                    if (Check.isDouble(args[2])) {
                        double amount = Double.parseDouble(args[2]);
                        for (String name : Objects.requireNonNull(new Files("stats").getConfig().getConfigurationSection("STATS")).getKeys(false)) {
                            if (name.equalsIgnoreCase(stats_name)) {
                                new NBTItem(item).setStats(stats_name, amount);
                                sendPlayerMessage(p, Objects.requireNonNull(new Files("message").getConfig().getString("ADMIN.SET_STATS")).replaceAll("#item#", p.getInventory().getItemInMainHand().getType().toString()).replaceAll("#stats_amount#", args[2]).replaceAll("#stats_name#", args[1]));
                            }
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("addenchant")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() == Material.AIR) {
                        return;
                    }
                    String enchantments = args[1].toUpperCase();
                    Enchantment enchant;
                    NMSAssistant nms = new NMSAssistant();
                    if (nms.isVersionLessThanOrEqualTo(12)) {
                        enchant = Enchantment.getByName(enchantments.toUpperCase());
                    } else {
                        enchant = Enchantment.getByKey(new NamespacedKey(net.danh.ditems.DItems.getInstance(), enchantments.toUpperCase()));
                    }
                    if (enchant != null) {
                        if (isInteger(args[2])) {
                            new NBTItem(item).addEnchants(enchantments, Integer.parseInt(args[2]));
                        }
                    } else {
                        sendPlayerMessage(p, "&c " + args[1] + " is null, Check: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html");
                    }
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("removelore")) {
                    if (isInteger(args[1])) {
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item.getType() == Material.AIR) {
                            return;
                        }
                        int line = Integer.parseInt(args[1]);
                        new NBTItem(item).removeLore(line);
                    }
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
                    if (isInteger(args[1])) {
                        int line = Integer.parseInt(args[1]);
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
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        sendConsoleMessage(c, "&cOnly player can do this command!");
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
                StringUtil.copyPartialMatches(args[0], commands, completions);
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("stats") || args[0].equalsIgnoreCase("show")) {
                    StringUtil.copyPartialMatches(args[1], Objects.requireNonNull(new Files("stats").getConfig().getConfigurationSection("STATS")).getKeys(false), completions);
                }
                if (args[0].equalsIgnoreCase("load")) {
                    StringUtil.copyPartialMatches(args[1], new FileFolder("items", "ItemSaved").getConfig().getKeys(false), completions);
                }
                if (args[0].equalsIgnoreCase("addenchant") || args[0].equalsIgnoreCase("removeenchant")) {
                    for (Enchantment enchantment : Enchantment.values()) {
                        NMSAssistant nms = new NMSAssistant();
                        if (nms.isVersionGreaterThanOrEqualTo(13)) {
                            StringUtil.copyPartialMatches(args[1], Collections.singleton(enchantment.getKey().toString()), completions);
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
