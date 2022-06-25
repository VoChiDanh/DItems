package net.danh.ditems.Commands;

import net.danh.dcore.Commands.CMDBase;
import net.danh.ditems.Manager.Check;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.Resource.Files;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

import static net.danh.dcore.Random.Number.isInteger;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class DItems extends CMDBase {
    public DItems(JavaPlugin core) {
        super(core, "ditems");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("ditems.admin")) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("show")) {
                    if (p.getInventory().getItemInMainHand() == null) {
                        return;
                    }
                    de.tr7zw.changeme.nbtapi.NBTItem nbtItem = new de.tr7zw.changeme.nbtapi.NBTItem(p.getInventory().getItemInMainHand());
                    sendPlayerMessage(p, String.valueOf(nbtItem.getDouble("DITEMS_STATS_" + args[1].toUpperCase())));
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("stats")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item == null) {
                        return;
                    }
                    String stats_name = args[1].toUpperCase();
                    if (Check.isDouble(args[2])) {
                        double amount = Double.parseDouble(args[2]);
                        for (String name : new Files("stats").getConfig().getConfigurationSection("STATS").getKeys(false)) {
                            if (name.equalsIgnoreCase(stats_name)) {
                                new NBTItem(item).setStats(stats_name, amount);
                                sendPlayerMessage(p, new Files("message").getConfig().getString("ADMIN.SET_STATS")
                                        .replaceAll("#item#", p.getInventory().getItemInMainHand().getType().toString())
                                        .replaceAll("#stats_amount#", args[2])
                                        .replaceAll("#stats_name#", args[1]));
                            }
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("removelore")) {
                    if (isInteger(args[1])) {
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null) {
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
                    if (item == null) {
                        return;
                    }
                    String name = String.join(" ", Arrays.asList(args).subList(1, args.length));
                    new NBTItem(item).setName(name);
                }
                if (args[0].equalsIgnoreCase("addlore")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item == null) {
                        return;
                    }
                    String lore = String.join(" ", Arrays.asList(args).subList(1, args.length));
                    new NBTItem(item).addLore(lore);
                }
                if (args[0].equalsIgnoreCase("setlore")) {
                    if (isInteger(args[1])) {
                        int line = Integer.parseInt(args[1]);
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null) {
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

    }
}
