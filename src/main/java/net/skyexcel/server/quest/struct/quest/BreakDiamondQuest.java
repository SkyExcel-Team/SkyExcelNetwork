package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.gui.button.BreakDiamondButton;
import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.struct.Completable;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class BreakDiamondQuest extends Quest implements Completable {


    private boolean is;

    private Config config;

    private BreakDiamondButton breakDiamondButton;

    public BreakDiamondQuest(OfflinePlayer player) {
        super("광물캐기", player, new BreakDiamondButton());
        setMax(5);
        breakDiamondButton = new BreakDiamondButton();
    }


    public BreakDiamondQuest(String name) {
        super("광물캐기", Bukkit.getOfflinePlayer(name), new BreakDiamondButton());
        setMax(5);
    }




    @Override
    public boolean isComplete() {
        return is;
    }

    @Override
    public void setCompletable(boolean is) {
        this.is = is;
    }
}
