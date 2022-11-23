package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BreakTreeQuest extends Quest implements Completable {
    public BreakTreeQuest(Player player) {
        super("나부 베기", player);
    }


    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void setCompletable(boolean is) {

    }
}
