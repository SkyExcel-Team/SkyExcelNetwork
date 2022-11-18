package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.data.FishRank;
import net.skyexcel.server.fish.data.FishType;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.Percent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import skyexcel.util.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FishEvent implements Listener, Percent {

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


    private ItemStack caught(Item stack, Player player, boolean upgrade) {
        Random random = new Random();
        List<FishType> fishTypes = new ArrayList<>(Arrays.stream(FishType.values()).filter(FishType::hasRank).toList());

        int rand = random.nextInt(fishTypes.size() - 1);
        FishType value = fishTypes.get(rand);

        if (upgrade) {
            if (chance(70)) //낚시꾼이 플러스 물고기를 잡을 확률.
                value.fishRankUp();
        } else {
            if (chance(50)) { // 일반 사람이 플러스 물고기를 잡을 확률.
                value.fishRankUp();
            }

        }

        int size;

        if (value.getFishRank() != null) {
            if (chance(85)) {
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
            PersistentDataContainer pdc = result.getItemMeta().getPersistentDataContainer();

            NamespacedKey Name = new NamespacedKey(SkyExcelNetworkJobMain.plugin, "name");
            pdc.set(Name, PersistentDataType.STRING, value.getFishRank().getName());


            NamespacedKey Size = new NamespacedKey(SkyExcelNetworkJobMain.plugin, "size");
            pdc.set(Size, PersistentDataType.INTEGER, size);


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

}
