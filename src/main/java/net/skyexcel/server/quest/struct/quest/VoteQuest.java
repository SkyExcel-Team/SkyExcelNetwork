package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.entity.Player;

public class VoteQuest extends Quest {



    public VoteQuest(Player player) {
        super("서버 추천하기", player);
        setMax(0);
    }
}
