package net.skyexcel.server.quest.gui.gui;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.gui.button.NotVoteButton;
import net.skyexcel.server.quest.gui.button.VoteButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class QuestCheckGUI {

    private Inventory inv;

    public void open(Player player) {
        if (SkyExcelNetworkMain.isLoaded(player)) {
            Inventory inv = Bukkit.createInventory(null, 27, "퀘스트");

            NotVoteButton notVoteButton = new NotVoteButton();
            notVoteButton.setInventory(10, inv);

//        VoteButton voteButton = new VoteButton();
//        voteButton.setInventory(10, inv);

            player.openInventory(inv);
        }

    }

    public Inventory getInv() {
        return inv;
    }

    public void buttonChange() {
        VoteButton voteButton = new VoteButton();
        voteButton.setInventory(10, inv);
    }

}
