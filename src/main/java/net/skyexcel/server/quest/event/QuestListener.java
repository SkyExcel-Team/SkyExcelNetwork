package net.skyexcel.server.quest.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.fish.events.PlayerFishCaughtEvent;
import net.skyexcel.server.quest.data.QuestData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class QuestListener implements Listener {

    @EventHandler
    public void onCaught(PlayerFishCaughtEvent event) {
        Player player = event.getPlayer();
        FishType fishType = event.getFishType();

        if (fishType.getFishRank() == FishRank.A ||
                fishType.getFishRank() == FishRank.APlus ||
                fishType.getFishRank() == FishRank.S ||
                fishType.getFishRank() == FishRank.SPlus) {
            player.sendMessage("테스트");
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();



    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        QuestData questData = new QuestData(player);
        questData.resetQuest();
    }

}
