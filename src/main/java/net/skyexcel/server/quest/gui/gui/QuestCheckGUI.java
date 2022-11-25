package net.skyexcel.server.quest.gui.gui;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.data.QuestData;
import net.skyexcel.server.quest.gui.button.*;
import net.skyexcel.server.quest.struct.Quest;
import net.skyexcel.server.quest.struct.quest.BlockPlaceQuest;
import net.skyexcel.server.quest.struct.quest.BreakWheatQuest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class QuestCheckGUI {

    private Inventory inv;

    public void open(Player player) {
        if (SkyExcelNetworkMain.isLoaded(player)) {
            Inventory inv = Bukkit.createInventory(null, 27, "퀘스트");
            QuestData questData = new QuestData(player);
//            for (Quest quest : questData.getQuests()) {
            BlockPlaceQuest blockPlaceQuest = new BlockPlaceQuest(player);
            BreakWheatQuest breakWheatQuest = new BreakWheatQuest(player);

            if (questData.hasQuest(blockPlaceQuest)) {
                blockPlaceQuest.getButton().setInventory(blockPlaceQuest.getButton().getSlot(), inv);
            } else if (questData.hasQuest(breakWheatQuest)) {
                breakWheatQuest.getButton().setInventory(breakWheatQuest.getButton().getSlot(), inv);
            }


//                int slot = button.getSlot();


            player.openInventory(inv);
        }
    }

    public Inventory getInv() {
        return inv;
    }
}
