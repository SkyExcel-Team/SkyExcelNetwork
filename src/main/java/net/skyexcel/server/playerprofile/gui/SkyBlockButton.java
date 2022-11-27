package net.skyexcel.server.playerprofile.gui;

import net.skyexcel.server.items.data.Items;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class SkyBlockButton extends Items {
    public SkyBlockButton(OfflinePlayer offlinePlayer) {
        getHeadItemFromHDB("38200");
        setDisplay("섬 방문하기");
        setCustomModelData(1);
        setLore(List.of("§7클릭시 " + offlinePlayer.getName() + " 님의 섬을 방문합니다."));
    }
}
