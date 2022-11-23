package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BreakWheatQuest extends Quest {
    public BreakWheatQuest(Player player) {
        super("밀 캐기", player);
    }
    public BreakWheatQuest(String name) {
        super("광물캐기", Bukkit.getOfflinePlayer(name));
        setMax(5);
    }
}
