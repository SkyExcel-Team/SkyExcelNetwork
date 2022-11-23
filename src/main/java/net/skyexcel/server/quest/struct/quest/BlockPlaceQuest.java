package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BlockPlaceQuest extends Quest {
    public BlockPlaceQuest(Player player) {
        super("블록 설치하기", player);
        setMax(5);
    }

    public BlockPlaceQuest(String name) {
        super("블록 설치하기", Bukkit.getOfflinePlayer(name));
        setMax(5);
    }
}
