package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.entity.Player;

public class FlyingQuest extends Quest implements Completable {


    public FlyingQuest(Player player) {
        super("날기", player);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void setCompletable(boolean is) {

    }
}
