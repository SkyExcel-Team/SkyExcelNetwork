package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.gui.button.NotVoteButton;
import net.skyexcel.server.quest.gui.button.VoteButton;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class VoteQuest extends Quest {


    private VoteButton voteButton;

    private NotVoteButton notVoteButton;

    public VoteQuest(OfflinePlayer player) {
        super("서버 추천하기", player, new VoteButton());
        setMax(0);
        voteButton = new VoteButton();
        notVoteButton = new NotVoteButton();
    }

    public VoteQuest(String name) {
        super("서버 추천하기", Bukkit.getOfflinePlayer(name), new NotVoteButton());
        setMax(0);
    }


}
