package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.gui.button.FlyingQuestButton;
import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class FlyingQuest extends Quest implements Completable {


    private FlyingQuestButton flyingQuestButton;

    public FlyingQuest(OfflinePlayer player) {
        super("날기", player, new FlyingQuestButton());
    }

    public FlyingQuest(String name) {
        super("날기", Bukkit.getOfflinePlayer(name), new FlyingQuestButton());
        flyingQuestButton = new FlyingQuestButton();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public Button getButton() {
        return flyingQuestButton;
    }

    @Override
    public void setCompletable(boolean is) {

    }
}
