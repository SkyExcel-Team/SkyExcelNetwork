package net.skyexcel.server.quest.gui.gui;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.gui.button.*;
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

            FishQuestButton fishQuestButton = new FishQuestButton();
            fishQuestButton.setInventory(12, inv);

            BlockPlaceQuestButton placeQuestButton = new BlockPlaceQuestButton();
            placeQuestButton.setInventory(13, inv);

            WheatQuestButton wheatQuestButton = new WheatQuestButton();
            wheatQuestButton.setInventory(14, inv);

            WoodQuestButton woodQuestButton = new WoodQuestButton();
            woodQuestButton.setInventory(15, inv);

            FlyingQuestButton flyingQuestButton = new FlyingQuestButton();
            flyingQuestButton.setInventory(16, inv);

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
