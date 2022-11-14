package net.skyexcel.server.snowy.util;

import net.skyexcel.server.snowy.data.SnowToggleData;
import org.bukkit.entity.Player;

public class SnowSettingUtils {
    public static void setSnowVisibility(Player player, Boolean visibility) {
        SnowToggleData data = new SnowToggleData(player);

        data.setVisibility(visibility);
    }

    public static Boolean getSnowVisibility(Player player) {
        SnowToggleData data = new SnowToggleData(player);

        return data.getVisibility();
    }
}
