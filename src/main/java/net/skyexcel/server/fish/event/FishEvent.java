package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import skyexcel.util.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FishEvent implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if (event.getCaught() instanceof Item) {
            Job job = new Job(player);

            job.setJobType(JobType.FISHERMAN);

            if (job.hasJob()) {

                ItemStack itemStack = caught((Item) event.getCaught(), player, job.getType().equals(JobType.FISHERMAN));

            }
        }
    }

    @EventHandler
    public void test(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        FishType fishType = FishType.GOLIATHGROUPER;

        player.getInventory().addItem(fishType.item(1));
    }

    private ItemStack caught(Item stack, Player player, boolean upgrade) {
        Random random = new Random();
        List<FishType> fishTypes = new ArrayList<>(Arrays.stream(FishType.values()).filter(FishType::hasRank).toList());

        int rand = random.nextInt(fishTypes.size() - 1);
        FishType value = fishTypes.get(rand);

        if (upgrade) {
            value.fishRankUp();
        }

        int size;

        if (value.getFishRank() != null) {
            if (getPercent(85)) {
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
            return result;
        }
        return null;
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

    public boolean getPercent(int percent) {
        double result = (double) percent / (double) 100 * 100.0;
        int rand = new Random().nextInt(100) + 1;
        return rand <= result;
    }
}
