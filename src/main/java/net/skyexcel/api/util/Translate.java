package net.skyexcel.api.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    public List<String> translate(List<String> lore, Player player) {
        List<String> newArray = new ArrayList<>();

        for (String line : lore) {
            line.split("%%");
            line = PlaceholderAPI.setPlaceholders(player, line);
            line = ChatColor.translateAlternateColorCodes('&', line);
            newArray.add(line);

        }

        return newArray;
    }

    public String getDate() {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Date date = new Date();
        return format2.format(date);
    }

    public String collapse(String[] args, int from) {
        return String.join(" ", Arrays.copyOfRange(args, from, args.length));
    }

    public String decal(long amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        String str = decFormat.format(amount);

        return str;
    }
}
