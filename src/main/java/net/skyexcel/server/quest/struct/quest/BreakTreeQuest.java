package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.gui.button.BreakTreeButton;
import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BreakTreeQuest extends Quest implements Completable {


    private BreakTreeButton breakTreeButton;

    public BreakTreeQuest(OfflinePlayer player) {
        super("나부 베기", player, new BreakTreeButton());
        breakTreeButton = new BreakTreeButton();
        setMax(5);
    }


    public BreakTreeQuest(String name) {
        super("나부 베기", Bukkit.getOfflinePlayer(name), new BreakTreeButton());
        setMax(5);
    }


    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void setCompletable(boolean is) {

    }
}
