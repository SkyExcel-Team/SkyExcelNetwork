package net.skyexcel.server.job.event;

import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.fish.data.FishStatus;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.obj.Scarecrow;
import net.skyexcel.server.job.data.obj.Selector;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class JobListener implements Listener {

    @EventHandler
    public void test(PlayerArmorStandManipulateEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        EquipmentSlot e = event.getHand();


        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        Job job = new Job(player);


        Selector selector = new Selector(JobType.FARM);

        selector.selectJob(player);

        if (Objects.equals(job.getJobType(), JobType.FARM)) {
            if (event.getHand() != null) {
                if (player.isSneaking()) {
                    if (player.getInventory().getItemInHand().getType().equals(Material.STONE_HOE)) {

                        // 허수아비를 소환 할때 메세지.
                        Scarecrow scarecrow = new Scarecrow(player);
                        scarecrow.spawn(player);
                        player.sendMessage("허수아비를 소환합니다.");
                        event.setCancelled(true);

                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();

        if (Objects.equals(entity.getType(), EntityType.ARMOR_STAND)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (SkyExcelNetworkFishMain.status.equals(FishStatus.Stop)) {
            Player player = event.getPlayer();
            Entity entity = event.getCaught();


        }
    }
}
