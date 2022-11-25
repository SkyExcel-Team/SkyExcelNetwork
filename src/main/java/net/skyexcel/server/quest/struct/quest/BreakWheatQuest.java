package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.gui.button.WheatQuestButton;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BreakWheatQuest extends Quest {

    private final WheatQuestButton wheatQuestButton = new WheatQuestButton();

    public BreakWheatQuest(OfflinePlayer player) {
        super("밀 캐기", player, new WheatQuestButton());
    }

    public BreakWheatQuest(String name) {
        super("광물캐기", Bukkit.getOfflinePlayer(name), new WheatQuestButton());
        setMax(5);
    }


}
