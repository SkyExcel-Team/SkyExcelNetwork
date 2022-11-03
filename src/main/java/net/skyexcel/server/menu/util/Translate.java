package net.skyexcel.server.menu.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Translate {


    public static List<String> translate(List<String> lore, Player player) {
        List<String> newArray = new ArrayList<>();

        for (String line : lore) {
            line.split("%%");
            line = PlaceholderAPI.setPlaceholders(player,line);
            line = ChatColor.translateAlternateColorCodes('&', line);
            newArray.add(line);

        }

        return newArray;
    }
}
