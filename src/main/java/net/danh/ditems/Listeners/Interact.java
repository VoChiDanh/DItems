package net.danh.ditems.Listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import net.danh.ditems.Manager.Ability;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Interact implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.getString("CMD") != null) {
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    if (!p.isSneaking()) {
                        Ability.executeCMD(p, nbtItem.getString("CMD_RIGHT_CLICK"), nbtItem.getInteger("DELAY_RIGHT_CLICK"));
                    } else {
                        Ability.executeCMD(p, nbtItem.getString("CMD_SHIFT_RIGHT_CLICK"), nbtItem.getInteger("DELAY_SHIFT_RIGHT_CLICK"));
                    }
                }
                if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    if (!p.isSneaking()) {
                        Ability.executeCMD(p, nbtItem.getString("CMD_LEFT_CLICK"), nbtItem.getInteger("DELAY_LEFT_CLICK"));
                    } else {
                        Ability.executeCMD(p, nbtItem.getString("CMD_SHIFT_LEFT_CLICK"), nbtItem.getInteger("DELAY_SHIFT_LEFT_CLICK"));

                    }
                }
            }
        }
    }
}
