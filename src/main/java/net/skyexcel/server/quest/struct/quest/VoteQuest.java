package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VoteQuest extends Quest {



    public VoteQuest(Player player) {
        super("서버 추천하기", player);
        setMax(0);
    }
    public VoteQuest(String name) {
        super("서버 추천하기", Bukkit.getOfflinePlayer(name));
        setMax(0);
    }
}
