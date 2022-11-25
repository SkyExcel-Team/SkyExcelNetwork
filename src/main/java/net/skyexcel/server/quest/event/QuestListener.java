package net.skyexcel.server.quest.event;

import com.bencodez.votingplugin.events.PlayerVoteEvent;
import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.fish.events.PlayerFishCaughtEvent;
import net.skyexcel.server.flyticket.data.FlyTime;
import net.skyexcel.server.quest.data.QuestData;

import net.skyexcel.server.quest.events.QuestCompleteEvent;
import net.skyexcel.server.quest.struct.Quest;
import net.skyexcel.server.quest.struct.quest.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;


public class QuestListener implements Listener {

    private final Material[] materials = {Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG};

    @EventHandler
    public void onCaught(PlayerFishCaughtEvent event) {
        Player player = event.getPlayer();
        FishType fishType = event.getFishType();


        QuestData questData = new QuestData(player);
        if (questData.isAllComplete()) {
            if (fishType.getFishRank() == FishRank.C || fishType.getFishRank() == FishRank.CPlus || fishType.getFishRank() == FishRank.A ||
                    fishType.getFishRank() == FishRank.APlus ||
                    fishType.getFishRank() == FishRank.S ||
                    fishType.getFishRank() == FishRank.SPlus) {

                FishCaughtQuest quest = new FishCaughtQuest(player);
                if (questData.hasQuest(quest)) {

                    if (quest.isNotMax()) {
                        quest.add(1);

                    } else {
                        QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, quest);
                        Bukkit.getPluginManager().callEvent(questCompleteEvent);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVote(PlayerVoteEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayer());
        VoteQuest quest = new VoteQuest(player);
        QuestData questData = new QuestData(player);
        if (questData.isAllComplete()) {
            if (questData.hasQuest(quest)) {
                if (!quest.isComplete()) {

                    QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, quest);
                    Bukkit.getPluginManager().callEvent(questCompleteEvent);
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        QuestData questData = new QuestData(player);

        if (questData.isAllComplete()) {
            isBreakTreeQuest(player, block);
            isBreakWheatQuest(player, block);
            isBreakDiamondQuest(player, block);
        } else {
            QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player);
            Bukkit.getPluginManager().callEvent(questCompleteEvent);
        }
    }

    private void isBreakTreeQuest(Player player, Block block) {

        BreakTreeQuest breakTreeQuest = new BreakTreeQuest(player);
        QuestData questData = new QuestData(player);

        if (!questData.hasQuest(breakTreeQuest)) {
            return;
        }
        if (Arrays.stream(materials).toList().contains(block.getType())) {
            if (breakTreeQuest.isNotMax()) {
                breakTreeQuest.add(1);

            } else {
                QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, breakTreeQuest);
                Bukkit.getPluginManager().callEvent(questCompleteEvent);
            }
        }
    }


    private void isBreakDiamondQuest(Player player, Block block) {
        BreakDiamondQuest breakDiamondQuest = new BreakDiamondQuest(player);
        QuestData questData = new QuestData(player);
        if (!questData.hasQuest(breakDiamondQuest)) {
            return;
        }

        if (questData.hasQuest(breakDiamondQuest)) {
            if (Material.DIAMOND_ORE.equals(block.getType())) {
                if (breakDiamondQuest.isNotMax()) {
                    breakDiamondQuest.add(1);
                } else {
                    QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, breakDiamondQuest);
                    Bukkit.getPluginManager().callEvent(questCompleteEvent);
                }
            }
        }
    }

    private void isBreakWheatQuest(Player player, Block block) {
        QuestData questData = new QuestData(player);
        BreakWheatQuest wheatQuest = new BreakWheatQuest(player);
        if (!questData.hasQuest(wheatQuest)) {
            return;
        }

        if (Material.WHEAT.equals(block.getType())) {

            if (wheatQuest.isNotMax()) {
                wheatQuest.add(1);
            } else {
                QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, wheatQuest);
                Bukkit.getPluginManager().callEvent(questCompleteEvent);
            }


        }
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        BlockPlaceQuest blockPlaceQuest = new BlockPlaceQuest(player);
        QuestData questData = new QuestData(player);
        if (questData.hasQuest(blockPlaceQuest)) {
            if (blockPlaceQuest.isNotMax()) {
                blockPlaceQuest.add(1);
            } else {
                QuestCompleteEvent questCompleteEvent = new QuestCompleteEvent(player, blockPlaceQuest);
                Bukkit.getPluginManager().callEvent(questCompleteEvent);
            }
        }
    }

    @EventHandler
    public void onFlying(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isFlying()) {
            FlyTime flyTime = new FlyTime(player);
            flyTime.getTime();
        }
    }

    @EventHandler
    public void completeQuest(QuestCompleteEvent event) {
        Player player = event.getPlayer();
        Quest quest = event.getQuest();

        QuestData questData = new QuestData(player);
        if (!questData.isAllComplete()) {
            quest.complete();
            player.sendMessage(quest.getName() + " 퀘스트를 완료하였습니다!");
        } else {
            quest.complete();
            player.sendMessage("모든 퀘스트를 완료 하였습니다.");
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        QuestData questData = new QuestData(player);
        questData.resetQuest();
    }
}
