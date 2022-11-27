package net.skyexcel.server.playerprofile.gui;

import net.skyexcel.server.items.data.Items;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class TargetSkullButton extends Items {

    public TargetSkullButton(OfflinePlayer offlinePlayer) {
        playerSkull(offlinePlayer.getName(), offlinePlayer.getName() + " 님의 프로필 ", List.of());
        setCustomModelData(2);
    }
}
