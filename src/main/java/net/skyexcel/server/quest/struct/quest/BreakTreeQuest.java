package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;

public class BreakTreeQuest extends Quest implements Completable {
    public BreakTreeQuest() {
        super("나부 베기");
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void setCompletable(boolean is) {

    }
}
