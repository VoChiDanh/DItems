package net.danh.ditems.Manager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.ditems.DItems;
import net.danh.ditems.Utils.Chat;
import net.danh.ditems.Utils.File;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Ability {

    private static final Set<String> cooldown = new HashSet<>();

    public static void executeCMD(Player p, String cmd, Integer delay) {
        if (cooldown.contains(p.getName() + "_" + cmd)) {
            p.sendMessage(Chat.colorize(Objects.requireNonNull(File.getMessage().getString("USER.DELAY")).replaceAll("#ability#", Objects.requireNonNull(File.getAbility_CMD().getString(cmd + ".DISPLAY")))));
            return;
        }
        for (String list_command : File.getAbility_CMD().getStringList(cmd + ".COMMAND")) {
            String papi = PlaceholderAPI.setPlaceholders(p, list_command);
            Bukkit.getServer().dispatchCommand(DItems.getDItems().getServer().getConsoleSender(), papi);
            cooldown.add(p.getName() + "_" + cmd);
        }
        new RemoveCooldown(p.getName() + "_" + cmd).runTaskLater(DItems.getDItems(), delay * 20L);
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

