package net.skyexcel.server.playerprofile.gui;

import net.skyexcel.server.cashshop.data.Cash;
import net.skyexcel.server.items.data.Items;
import net.skyexcel.server.playerprofile.data.PlayerProfile;
import net.skyexcel.server.seconomy.data.SEConomy;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class TargetSkullButton extends Items {

    public TargetSkullButton(OfflinePlayer offlinePlayer) {
        playerSkull(offlinePlayer.getName(), offlinePlayer.getName() + " 님의 프로필 ", List.of());

        SEConomy SEconomy = new SEConomy(offlinePlayer);
        Cash cash = new Cash(offlinePlayer);

        PlayerProfile playerProfile = new PlayerProfile(offlinePlayer);

        setLore(List.of("돈 : " + SEconomy.getLong(),
                "캐시 : " + cash.getLong(),
                "인기도 : " + playerProfile.getLong()));

        setCustomModelData(2);
    }
}
