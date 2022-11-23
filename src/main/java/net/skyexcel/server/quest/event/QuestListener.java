package net.skyexcel.server.quest.event;

import com.bencodez.votingplugin.events.PlayerVoteEvent;
import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.fish.events.PlayerFishCaughtEvent;
import net.skyexcel.server.flyticket.events.PlayerFlyingEvent;
import net.skyexcel.server.quest.data.QuestData;

import net.skyexcel.server.quest.events.QuestCompleteEvent;
import net.skyexcel.server.quest.struct.Quest;
import net.skyexcel.server.quest.struct.quest.BreakTreeQuest;
import net.skyexcel.server.quest.struct.quest.BreakWheatQuest;
import net.skyexcel.server.quest.struct.quest.FishCaughtQuest;
import net.skyexcel.server.quest.struct.quest.VoteQuest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import skyexcel.util.ActionBar;

import java.util.Arrays;


public class QuestListener implements Listener {

    @EventHandler
    public void onCaught(PlayerFishCaughtEvent event) {
        Player player = event.getPlayer();
        FishType fishType = event.getFishType();


        if (fishType.getFishRank() == FishRank.C || fishType.getFishRank() == FishRank.CPlus || fishType.getFishRank() == FishRank.A ||
                fishType.getFishRank() == FishRank.APlus ||
                fishType.getFishRank() == FishRank.S ||
                fishType.getFishRank() == FishRank.SPlus) {

            FishCaughtQuest quest = new FishCaughtQuest(player);
            if (QuestData.hasQuest(quest)) {
                if (!quest.isComplete()) {
                    if (quest.isNotMax()) {
                        quest.add(1);
                    }
                } else {
                    QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, quest);
                    Bukkit.getPluginManager().callEvent(questCompleteEvent);
                }
            }
        }
    }

    @EventHandler
    public void onVote(PlayerVoteEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayer());
        VoteQuest quest = new VoteQuest(player);
        if (QuestData.hasQuest(quest)) {
            if (!quest.isComplete()) {

                QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, quest);
                Bukkit.getPluginManager().callEvent(questCompleteEvent);
            }
        }
    }

    private final Material[] materials = {Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG};

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        BreakTreeQuest breakTreeQuest = new BreakTreeQuest(player);
        BreakWheatQuest wheatQuest = new BreakWheatQuest(player);

        if (QuestData.hasQuest(breakTreeQuest)) {
            if (Arrays.stream(materials).toList().contains(block.getType())) {
                if (!breakTreeQuest.isComplete()) {
                    if (breakTreeQuest.isNotMax()) {

                    } else {
                        QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, breakTreeQuest);
                        Bukkit.getPluginManager().callEvent(questCompleteEvent);
                    }
                }
            }
        } else if (QuestData.hasQuest(wheatQuest)) {
            if (Material.WHEAT.equals(block.getType())) {
                if (!breakTreeQuest.isComplete()) {
                    if (breakTreeQuest.isNotMax()) {

                    } else {
                        QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, wheatQuest);
                        Bukkit.getPluginManager().callEvent(questCompleteEvent);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFlying(PlayerFlyingEvent event) {
        Player player = event.getPlayer();
        if (PlayerFlyingEvent.Flying.FLYING.equals(event.getFlying())) {
            player.sendMessage("");
        }
    }

    @EventHandler
    public void completeQuest(QuestCompleteEvent event) {
        Player player = event.getPlayer();
        Quest quest = event.getQuest();
        if (quest.isNotMax()) {
            quest.complete();
            player.sendMessage(quest.getName() + " 퀘스트를 완료하였습니다!");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        QuestData questData = new QuestData(player);
        questData.resetQuest();
    }
}
