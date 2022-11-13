package net.skyexcel.server.cashshop.util;

import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Translate {

    public List<String> msgCollapse(List<String> stringList) {
        List<String> newArray = new ArrayList<>();

        for (String line : stringList) {
            line = ChatColor.translateAlternateColorCodes('&', line);
            newArray.add(line);
        }

        return newArray;
    }


    public String decal(long amount){
        DecimalFormat decFormat = new DecimalFormat("###,###");

        String str = decFormat.format(amount);

        return str;
    }
}
