package net.danh.ditems.Manager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.ditems.DItems;
import net.danh.ditems.Resource.Resource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Ability {

    private static final Set<String> cooldown = new HashSet<>();

    public static void executeCMD(Player p, List<String> ability) {
        for (String a : ability) {
            String[] cmd = a.split(";");
            if (cmd[0].equalsIgnoreCase("CMD")) {
                if (cooldown.contains(p.getName() + "_" + cmd[2])) {
                    sendPlayerMessage(p, Objects.requireNonNull(Resource.getMessage().getString("USER.DELAY")).replaceAll("#ability#", Objects.requireNonNull(Resource.getCMD().getString(cmd[2] + ".DISPLAY"))));
                    return;
                }
                for (String list_command : Resource.getCMD().getStringList(cmd[2] + ".COMMAND")) {
                    String papi = PlaceholderAPI.setPlaceholders(p, list_command);
                    Bukkit.getServer().dispatchCommand(DItems.getInstance().getServer().getConsoleSender(), papi);
                    cooldown.add(p.getName() + "_" + cmd[2]);
                }
                new RemoveCooldown(p.getName() + "_" + cmd[2]).runTaskLater(DItems.getInstance(), Integer.parseInt(cmd[3].toString()) * 20L);
            }
        }
    }

    public static class RemoveCooldown extends BukkitRunnable {
        private static String name;

        public RemoveCooldown(String name) {
            RemoveCooldown.name = name;
        }

        @Override
        public void run() {
            cooldown.remove(name);
        }
    }
}

