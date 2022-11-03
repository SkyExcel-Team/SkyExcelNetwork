package net.skyexcel.server.cashshop.util;

import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Translate {
    public static String msgCollapse(String[] args, int index) {
        StringBuilder string = new StringBuilder();
        if (args.length > index) {
            for (int i = index; i < args.length; i++) {
                if (args.length != index) {
                    if (i == index) {
                        string.append(args[i]);
                    } else {
                        string.append(" " + args[i]);
                    }
                }
            }
        }
        return string.toString();
    }

    public static List<String> msgCollapse(List<String> stringList) {
        List<String> newArray = new ArrayList<>();

        for (String line : stringList) {
            line = ChatColor.translateAlternateColorCodes('&', line);
            newArray.add(line);
        }

        return newArray;
    }

    public static String decal(long amount){
        DecimalFormat decFormat = new DecimalFormat("###,###");

        String str = decFormat.format(amount);

        return str;
    }
}
