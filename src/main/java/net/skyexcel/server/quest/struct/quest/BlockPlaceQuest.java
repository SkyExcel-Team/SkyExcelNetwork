package net.skyexcel.server.quest.struct.quest;

import net.skyexcel.server.quest.data.QuestData;
import net.skyexcel.server.quest.gui.button.BlockPlaceQuestButton;
import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BlockPlaceQuest extends Quest {

    private BlockPlaceQuestButton placeQuestButton;

    private OfflinePlayer player;

    public BlockPlaceQuest(OfflinePlayer player) {
        super("블록 설치하기", player, new BlockPlaceQuestButton());
        setMax(5);
        this.player = player;

    }

    public BlockPlaceQuest(String name) {
        super("블록 설치하기", Bukkit.getOfflinePlayer(name), new BlockPlaceQuestButton());
        setMax(5);
    }



    public void onPlace(int slot, Inventory inv) {
        QuestData questData = new QuestData(player);
        if (questData.hasQuest(this)) {
            placeQuestButton.setNow(getNow());
            placeQuestButton.setInventory(slot, inv);
        }
    }
}
