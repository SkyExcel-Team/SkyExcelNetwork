package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.Percent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skyexcel.data.file.Config;
import skyexcel.util.ActionBar;

import java.util.*;

public class FishEvent implements Listener, Percent {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();


        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            if (event.getCaught() instanceof Item item) {
                Job job = new Job(player);
                job.setJobType(JobType.FISHERMAN);
                item.setItemStack(FishType.RAWMUDCARP.item(1));

                if (job.hasJob()) {
                    caught((Item) event.getCaught(), player, job.getType().equals(JobType.FISHERMAN));
                } else {
                    caught((Item) event.getCaught(), player, false);
                }

            }
        }
    }

    private void caught(Item stack, Player player, boolean upgrade) {

        Config config = SkyExcelNetworkJobMain.config;
        System.out.println(upgrade);

        if (upgrade) {
            //낚시꾼이 플러스 물고기를 잡을 확률.
            test(stack, player);
        } else {
            if (chance(config.getDouble("percent.FishCaught.RankUp.Default"))) { // 일반 사람이 플러스 물고기를 잡을 확률.

            }
        }
    }


    public void test(Item stack, Player player) {
        Random random = new Random();
        FishType fishType = FishType.COOKEDANCHOVY;
        int size = 0;
        float index = random.nextFloat(0, 100);
        Config config = SkyExcelNetworkJobMain.config;

        ItemStack item = null;

        double JobRankUp = config.getDouble("percent.FishCaught.RankUp.Job"); //TODO 이건 나중에 스텟으로 뺄꺼임.

        double DefaultRankUp = config.getDouble("percent.FishCaught.RankUp.Default");

        System.out.println(getByRank(FishRank.S));

        double percent = Math.random() * 100; //물고기 잡을 확률

        double RankUpPercent = Math.random() * 100; //랭크를 업할 확률

        List<Double> chances = new ArrayList<>(List.of(config.getDouble("percent.FishCaught.Rank.S"),
                config.getDouble("percent.FishCaught.Rank.A"),
                config.getDouble("percent.FishCaught.Rank.B"),
                config.getDouble("percent.FishCaught.Rank.C")));
        Collections.sort(chances);
        Map<Double, FishRank> ranks = new HashMap<>();
        ranks.put(config.getDouble("percent.FishCaught.Rank.S"), FishRank.S);
        ranks.put(config.getDouble("percent.FishCaught.Rank.A"), FishRank.A);
        ranks.put(config.getDouble("percent.FishCaught.Rank.B"), FishRank.B);
        ranks.put(config.getDouble("percent.FishCaught.Rank.C"), FishRank.C);

        if (percent <= chances.get(0)) {
//            System.out.println(ChatColor.BLUE + "" + (index < SChance) + " / " + Math.random() * 100 + " < " + SChance);
            
            List<FishType> types = getByRank(ranks.get(chances.get(0)));
            fishType = selector(types);
            size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
            item = fishType.item(1);

            if (RankUpPercent <= JobRankUp) {
                rankUp(fishType, item);
            }

        } else if (percent <= chances.get(0) + chances.get(1)) {
//            System.out.println(ChatColor.YELLOW + "" + (index < AChance) + " / " + Math.random() * 100 + " < " + AChance);
            
            List<FishType> types = getByRank(ranks.get(chances.get(1)));
            fishType = selector(types);
            size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
            item = fishType.item(1);

            if (RankUpPercent <= JobRankUp) {
                rankUp(fishType, item);
            }


        } else if (percent <= chances.get(0) + chances.get(1) + chances.get(2)) {
//            System.out.println(ChatColor.GOLD + "" + (index < BChance) + " / " + Math.random() * 100 + " < " + BChance);

            List<FishType> types = getByRank(ranks.get(chances.get(2)));
            fishType = selector(types);
            size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
            item = fishType.item(1);

            if (RankUpPercent <= JobRankUp) {
                rankUp(fishType, item);
            }

        } else if (percent <= chances.get(0) + chances.get(1) + chances.get(2) + chances.get(3)) {
//            System.out.println(ChatColor.GREEN + "" + (index < CChance) + " / " + Math.random() * 100 + " < " + CChance);

            List<FishType> types = getByRank(ranks.get(chances.get(3)));
            System.out.println(types);
            fishType = selector(types);
            size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
            item = fishType.item(1);

            if (RankUpPercent <= JobRankUp) {
                rankUp(fishType, item);
            }

        }

        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("(" + fishType.getFishRank().getName() + ") " + fishType.getTranslate() + " §7(" + size + "cm)");
            item.setItemMeta(meta);
            stack.setItemStack(item);

        }

        ActionBar.sendMessage(player, "[" + fishType.getFishRank().getName() + "] " + fishType.getTranslate() +
                " 물고기를 잡았습니다! §7(" + size + "cm)");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        ItemStack test = new ItemStack(Material.FISHING_ROD, 1);
        test.addUnsafeEnchantment(Enchantment.LURE, 255);
        player.getInventory().addItem(test);

    }

    private FishType rankUp(FishType rank, ItemStack item) {
        return rank.fishRankUp(item);
    }

    private FishType selector(List<FishType> types) {

        Random random = new Random();

        System.out.println(types);
        if (types.size() > 1) {
            int test = random.nextInt(types.size() - 1);

            return types.get(test);
        } else {
            return types.get(0);
        }


    }

    /**
     * 사이즈를 확률적으로 불러 올 수 있는 메소드
     *
     * @param rand 퍼센트 (백분률)
     * @return 랜덤한 값이 퍼센트 값보다 크면, 10cm~5cm를, 작으면 10~20cm를 반환한다.
     */
    private int getSizeByRand(int rand) {
        Random random = new Random();
        float result = random.nextFloat(0, rand);

        if (result > rand) {
            return random.nextInt(5, 10);
        }
        return random.nextInt(10, 20);
    }

    private List<FishType> getByRank(FishRank rank) {
        return Arrays.stream(FishType.values()).filter(values -> (values.hasRank() && values.getFishRank().equalRank(rank))).toList();
    }
}
