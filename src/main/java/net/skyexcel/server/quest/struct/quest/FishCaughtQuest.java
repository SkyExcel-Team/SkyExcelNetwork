package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.gui.button.FishQuestButton;
import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class FishCaughtQuest extends Quest implements Completable {

    private FishQuestButton fishQuestButton;

    public FishCaughtQuest(OfflinePlayer player) {
        super("물고기 잡기", player,new FishQuestButton());
        setMax(2);
        fishQuestButton = new FishQuestButton();
    }

    public FishCaughtQuest(String name) {
        super("물고기 잡기", Bukkit.getOfflinePlayer(name),new FishQuestButton());
        setMax(2);
    }



    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void setCompletable(boolean is) {

    }
}
