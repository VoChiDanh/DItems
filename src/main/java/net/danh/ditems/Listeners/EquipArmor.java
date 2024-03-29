package net.danh.ditems.Listeners;

import net.danh.ditems.API.ArmorEquipEvent;
import net.danh.ditems.Manager.NBTItem;
import net.danh.ditems.PlayerData.PlayerData;
import net.danh.ditems.Utils.Chat;
import net.danh.ditems.Utils.File;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class EquipArmor implements Listener {

    @EventHandler
    public void onEquip(ArmorEquipEvent e) {
        Player p = e.getPlayer();
        if (e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
            if (new NBTItem(e.getNewArmorPiece()).hasDoubleStats("REQUIRED_LEVEL")) {
                if ((int) new NBTItem(e.getNewArmorPiece()).getDoubleStats("REQUIRED_LEVEL") > PlayerData.getLevel(p)) {
                    p.sendMessage(Chat.colorize(File.getMessage().getString("USER.NOT_ENOUGH_LEVEL")));
                }
            }
        }
    }
}
