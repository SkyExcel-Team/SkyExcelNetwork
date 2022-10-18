package net.skyexcel.server.util;

import net.skyexcel.server.data.economy.SEconomy;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Translate {

    private static SEconomy economy;


    public static String moneyCheck(Player player, String msg) {
        economy = new SEconomy(player);
        return msg.replaceAll("%money%", String.valueOf(economy.getMoney()));
    }

    public static String moneyCheckTarget(Player target, String msg) {
        economy = new SEconomy(target);
        return msg.replaceAll("%money%", String.valueOf(economy.getMoney()));
    }

    public static String moneyAction(Player player, Player target, int amount, String msg) {
        economy = new SEconomy(player);
        return msg.replaceAll("%money%", String.valueOf(amount)).
                replaceAll("%player%", target.getDisplayName());
    }

    public static String shopAction(String name, String msg) {
        return msg.replaceAll("%name%", name);
    }

    public static String shopResize(String name, int size, String msg) {
        return msg.replaceAll("%name%", name)
                .replaceAll("%size%", String.valueOf(size));
    }
    public static String shopRename(String name, String newName, String msg) {
        return msg.replaceAll("%name%", name)
                  .replaceAll("%set_name%", newName);
    }

    public static String itemAction(ItemStack item, int price, String msg) {
        if (item != null) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                return msg.replaceAll("%item_order%", meta.getDisplayName())
                        .replaceAll("%price%", String.valueOf(price));
            } else {
                return msg.replaceAll("%item_order%", item.getType().name())
                        .replaceAll("%price%", String.valueOf(price));
            }
        }
        return msg.replaceAll("%price%", String.valueOf(price));
    }
    public static String getDate() {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Date date = new Date();
        String nowTime = format2.format(date);
        return nowTime;
    }
}
