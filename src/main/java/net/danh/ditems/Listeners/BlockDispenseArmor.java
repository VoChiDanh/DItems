package net.danh.ditems.Listeners;

import net.danh.ditems.API.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;

public class BlockDispenseArmor implements Listener {

    @EventHandler
    public void onDispense(BlockDispenseArmorEvent event) {
        ArmorEquipEvent.ArmorType type = ArmorEquipEvent.ArmorType.matchType(event.getItem());
        if (type != null && event.getTargetEntity() instanceof Player)
            Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent((Player) event.getTargetEntity(), type, null, event.getItem()));
    }
}
