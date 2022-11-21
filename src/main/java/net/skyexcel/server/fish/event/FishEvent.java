package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.fish.events.PlayerFishCaughtEvent;
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
            if (event.getCaught() instanceof Item) {
                Job job = new Job(player);
                job.setJobType(JobType.FISHERMAN);


                if (job.hasJob()) {
                    caught((Item) event.getCaught(), player, job.getType().equals(JobType.FISHERMAN));
                }
            }
        }
    }


    private void caught(Item stack, Player player, boolean upgrade) {
        FishType fishType = FishType.NULL;
        int size = 0;

        Config config = SkyExcelNetworkJobMain.config;

        ItemStack item = null;

        double JobRankUp = config.getDouble("percent.FishCaught.RankUp.Job"); //TODO 이건 나중에 스텟으로 뺄꺼임.


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

        double DefaultRankUp = config.getDouble("percent.FishCaught.RankUp.Default");

        if (percent <= chances.get(0)) {

            List<FishType> types = getByRank(ranks.get(chances.get(0)));
            if (!types.isEmpty()) {
                fishType = selector(types);
                size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
                item = fishType.item(1);
            }
            rankUpByUpgrade(item, upgrade, RankUpPercent, JobRankUp, DefaultRankUp, fishType);
        } else if (percent <= chances.get(0) + chances.get(1)) {

            List<FishType> types = getByRank(ranks.get(chances.get(1)));
            if (!types.isEmpty()) {
                fishType = selector(types);
                size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
                item = fishType.item(1);
            }
            rankUpByUpgrade(item, upgrade, RankUpPercent, JobRankUp, DefaultRankUp, fishType);

        } else if (percent <= chances.get(0) + chances.get(1) + chances.get(2)) {

            List<FishType> types = getByRank(ranks.get(chances.get(2)));
            if (!types.isEmpty()) {
                fishType = selector(types);

                size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
                item = fishType.item(1);
            }
            rankUpByUpgrade(item, upgrade, RankUpPercent, JobRankUp, DefaultRankUp, fishType);

        } else if (percent <= chances.get(0) + chances.get(1) + chances.get(2) + chances.get(3)) {

            List<FishType> types = getByRank(ranks.get(chances.get(3)));
            if (!types.isEmpty()) {
                fishType = selector(types);
                size = getSizeByRand(config.getInteger("percent.FishCaught.Size.Under"));
                item = fishType.item(1);
            }
            rankUpByUpgrade(item, upgrade, RankUpPercent, JobRankUp, DefaultRankUp, fishType);
        }

        if (item != null) {
            PlayerFishCaughtEvent playerFishCaughtEvent = new PlayerFishCaughtEvent(player, fishType);
            Bukkit.getPluginManager().callEvent(playerFishCaughtEvent);

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("(" + fishType.getFishRank().getName() + ") " + fishType.getTranslate() + " §7(" + size + "cm)");
            item.setItemMeta(meta);
            stack.setItemStack(item);

            ActionBar.sendMessage(player, "[" + fishType.getFishRank().getName() + "] " + fishType.getTranslate() +
                    " 물고기를 잡았습니다! §7(" + size + "cm)");


        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        ItemStack test = new ItemStack(Material.FISHING_ROD, 1);
        test.addUnsafeEnchantment(Enchantment.LURE, 255);
        player.getInventory().addItem(test);

    }

    /**
     * Upgrade 변수값이 참이면, JobRankUp 의 변수에 따라 RankUpPercent 변수 값이 비교가 되고,
     * Upgrade 변수값이 거짓이면 DefaultRankUp 변수에 따라 RankUpPercent 변수 값이 비교가 된다.
     *
     * @param item          인첸트 될 아이템 변수
     * @param upgrade       플레이어가 어부 직업인지 아닌지, 비교하는 변수
     * @param RankUpPercent 랭크 업을 할 확률
     * @param JobRankUp     RankUpPercent를 비교할 값 (직업이 어부일 경우)
     * @param DefaultRankUp RankupPercent를 비교할 값(직업이 어부가 아닐 경우)
     * @param fishType      랭크업할 FishType 변수
     */
    private void rankUpByUpgrade(ItemStack item, boolean upgrade, double RankUpPercent, double JobRankUp, double DefaultRankUp, FishType fishType) {
        if (upgrade) {
            if (RankUpPercent <= JobRankUp) {
                if (!fishType.equals(FishType.NULL)) { //이게 else면 꽝
                    rankUp(fishType, item);
                } else {
                    System.out.println("일반 아이템 나옴 ");
                }

            }
        } else {
            if (RankUpPercent <= DefaultRankUp) {
                if (!fishType.equals(FishType.NULL)) { //이게 else면 꽝
                    rankUp(fishType, item);
                } else {
                    System.out.println("일반 아이템 나옴 ");
                }
            }
        }
    }

    private void rankUp(FishType rank, ItemStack item) {
        rank.fishRankUp(item);
    }


    /***
     * 리스트 값중에서 하나의 값을 랜덤하게(리스트 사이즈만큼) 뽑는 함수이다.
     * 만약 리스트의 값이 없다면, NullType을 변환한다.
     * @param types 리불러올 리스트
     * @return 렌덤으로 뽑은 값, 혹은 NullType
     */
    private FishType selector(List<FishType> types) {
        Random random = new Random();
        if (types.size() > 1) {
            return types.get(random.nextInt(types.size() - 1));
        } else {
            return FishType.NULL;
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
