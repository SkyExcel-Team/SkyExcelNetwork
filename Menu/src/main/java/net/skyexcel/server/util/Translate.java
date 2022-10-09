package net.skyexcel.server.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Translate {


    public static List<String> translate(List<String> lore) {
        List<String> newArray = new ArrayList<>();

        for (String line : lore) {
            line = ChatColor.translateAlternateColorCodes('&', line);
            newArray.add(line);

        }

        return newArray;
    }
}
