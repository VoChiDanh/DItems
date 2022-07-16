package net.danh.ditems.Listeners;

import net.danh.ditems.Manager.Ability;
import net.danh.ditems.Manager.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Interact implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType() != Material.AIR) {
            List<String> cmd = new NBTItem(item).getAbilityCommand();
            if (!cmd.isEmpty()) {
                for (String ability : cmd) {
                    String[] a = ability.split(";");
                    if (a[0].equalsIgnoreCase("CMD")) {
                        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                            if (!p.isSneaking()) {
                                if (a[1].equalsIgnoreCase("RIGHT_CLICK")) {
                                    Ability.executeCMD(p, cmd);
                                }
                            } else {
                                if (a[1].equalsIgnoreCase("SHIFT_RIGHT_CLICK")) {
                                    Ability.executeCMD(p, cmd);
                                }
                            }
                        }
                        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                            if (!p.isSneaking()) {
                                if (a[1].equalsIgnoreCase("LEFT_CLICK")) {
                                    Ability.executeCMD(p, cmd);
                                }
                            } else {
                                if (a[1].equalsIgnoreCase("SHIFT_LEFT_CLICK")) {
                                    Ability.executeCMD(p, cmd);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
