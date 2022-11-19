package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.Percent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

            }
        }
    }

    private void caught(Item stack, Player player, boolean upgrade) {
        Random random = new Random();
        List<FishType> fishTypes = new ArrayList<>(Arrays.stream(FishType.values()).filter(FishType::hasRank).toList());


        Config config = SkyExcelNetworkJobMain.config;

        FishType fishType = null;

        int size = 0;

        if (upgrade) {
            //낚시꾼이 플러스 물고기를 잡을 확률.
            if (chance(config.getDouble("percent.FishCaught.RankUp.Job"))) {

            } else {
                float index = random.nextFloat(0, 100);

                double SChance = config.getDouble("percent.FishCaught.Rank.S");
                double AChance = config.getDouble("percent.FishCaught.Rank.A");
                double BChance = config.getDouble("percent.FishCaught.Rank.B");
                double CChance = config.getDouble("percent.FishCaught.Rank.C");

                if (index > SChance) {
                    List<FishType> S = getByRank(FishRank.S);
                    fishType = selector(S);
                    size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));


                } else if (index > AChance) {
                    List<FishType> A = getByRank(FishRank.A);
                    fishType = selector(A);
                    size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));

                } else if (index > BChance) {
                    List<FishType> B = getByRank(FishRank.B);
                    fishType = selector(B);
                    size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));

                } else if (index > CChance) {
                    List<FishType> C = getByRank(FishRank.C);
                    fishType = selector(C);
                    size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
                }
            }

            ActionBar.sendMessage(player, "[" + fishType.getFishRank().getName() + "] " + fishType.getTranslate() +
                    " 물고기를 잡았습니다! §7(" + size + "cm)");

            ItemStack result = fishType.item(1);
            stack.setItemStack(result);

        } else {
            if (chance(config.getDouble("percent.FishCaught.RankUp.Default"))) { // 일반 사람이 플러스 물고기를 잡을 확률.

            }
        }

    }


    /**
     * 사이즈를 확률적으로 불러 올 수 있는 메소드
     *
     * @param rand 퍼센트 (백분률)
     * @return 랜덤한 값이 퍼센트 값보다 크면, 10cm~5cm를, 작으면 10~20cm를 반환한다.
     */
    public int getSizeByRand(int rand) {
        Random random = new Random();
        float result = random.nextFloat(0, rand);
        if (result > rand) {
            return random.nextInt(5, 10);
        }
        return random.nextInt(10, 20);
    }


//    public ItemStack getItem(Player player, FishType value, Config config) {
//        if (value.getFishRank() != null) {
//            if (chance(config.getDouble("percent.FishCaught.FishCaught.Size.Under"))) {
//                size = random.nextInt(10) + 5;
//            } else {
//                size = random.nextInt(20 - 10 + 1) + 10;
//            }
//
//            if (value.getFishRank() == FishRank.S || value.getFishRank() == FishRank.SPlus) {
//                Bukkit.getOnlinePlayers().forEach(players -> {
//                    players.sendMessage(player.getDisplayName() + "님이 " + "[" + value.getFishRank().getName() + "] "
//                            + value.getTranslate() + " 물고기를 잡았습니다! §7(" + size + "cm)");
//                });
//
//            } else {
//                ActionBar.sendMessage(player, "[" + value.getFishRank().getName() + "] " + value.getTranslate() +
//                        " 물고기를 잡았습니다! §7(" + size + "cm)");
//            }
//            value.setSize(size);
//             //Your new itemstack here!
//        }
//    }

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

    public FishType selector(List<FishType> types) {
        Random random = new Random();
        return types.get(random.nextInt(types.size() - 1));
    }

    private List<FishType> getByRank(FishRank rank) {
        return Arrays.stream(FishType.values()).filter(values -> {
            if (values.hasRank()) {
                return values.getFishRank().equalRank(rank);
            }
            return false;
        }).toList();
    }
}
