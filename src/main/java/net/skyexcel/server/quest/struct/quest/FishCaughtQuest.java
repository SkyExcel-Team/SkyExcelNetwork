package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.entity.Player;

public class FishCaughtQuest extends Quest implements Completable {
    public FishCaughtQuest(Player player) {
        super("물고기 잡기", player);
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
