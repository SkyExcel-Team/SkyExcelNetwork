package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.Percent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.util.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FishEvent implements Listener, Percent {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();


        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            if (event.getCaught() instanceof Item) {
                Job job = new Job(player);
                job.setJobType(JobType.FISHERMAN);

                if (job.hasJob()) {
                    caught((Item) event.getCaught(), player, job.getType().equals(JobType.FISHERMAN));
                } else {
                    caught((Item) event.getCaught(), player, false);

                }
//     List<FishType> ARank =
                new ArrayList<>(Arrays.stream(FishType.values()).filter(test -> {
                    if (test.hasRank()) {
                        return test.getFishRank().equalRank(FishRank.A);
                    }
                    return false;
                }).toList()).forEach(test -> {
                    System.out.println(test.getFishRank());
                });

            }
        }
    }

    private void caught(Item stack, Player player, boolean upgrade) {
        Random random = new Random();
        List<FishType> fishTypes = new ArrayList<>(Arrays.stream(FishType.values()).filter(FishType::hasRank).toList());


        int rand = random.nextInt(fishTypes.size() - 1);

        boolean whoKnows = random.nextInt(1, 101) <= 50;


        FishType value = fishTypes.get(rand);

        Config config = SkyExcelNetworkJobMain.config;
        if (upgrade) {
            if (chance(config.getDouble("percent.FishCaught.RankUp.Job"))) //낚시꾼이 플러스 물고기를 잡을 확률.
                value.fishRankUp();
        } else {
            if (chance(config.getDouble("percent.FishCaught.RankUp.Default"))) { // 일반 사람이 플러스 물고기를 잡을 확률.
                value.fishRankUp();
            }
        }
        int size;


        if (value.getFishRank() != null) {
            if (chance(config.getDouble("percent.FishCaught.FishCaught.Size.Under"))) {
                size = random.nextInt(10) + 5;
            } else {
                size = random.nextInt(20 - 10 + 1) + 10;
            }

            if (value.getFishRank() == FishRank.S || value.getFishRank() == FishRank.SPlus) {
                Bukkit.getOnlinePlayers().forEach(players -> {
                    players.sendMessage(player.getDisplayName() + "님이 " + "[" + value.getFishRank().getName() + "] "
                            + value.getTranslate() + " 물고기를 잡았습니다! §7(" + size + "cm)");
                });

            } else {
                ActionBar.sendMessage(player, "[" + value.getFishRank().getName() + "] " + value.getTranslate() +
                        " 물고기를 잡았습니다! §7(" + size + "cm)");
            }
            value.setSize(size);
            ItemStack result = value.item(1);

            stack.setItemStack(result); //Your new itemstack here!
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        ItemStack test = new ItemStack(Material.FISHING_ROD, 1);
        test.addUnsafeEnchantment(Enchantment.LURE, 255);
        player.getInventory().addItem(test);

    }

    @EventHandler
    public void pickUp(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();

    }

}
