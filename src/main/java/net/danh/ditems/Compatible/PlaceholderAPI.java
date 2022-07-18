package net.danh.ditems.Compatible;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.ditems.DItems;
import net.danh.ditems.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "ditems";
    }

    @Override
    public @NotNull String getAuthor() {
        return DItems.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return DItems.getInstance().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player p, @NotNull String args) {
        if (p == null) return null;
        if (args.startsWith("stats_")) {
            String stats = args.substring(6);
            return String.valueOf(PlayerData.getPlayerStats(p, stats.toUpperCase()));
        }
        return null;
    }
}
