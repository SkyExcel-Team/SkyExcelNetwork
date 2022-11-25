package net.skyexcel.server.job.listener;


import net.skyexcel.server.items.data.Items;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.fisher.WaterBucket;
import net.skyexcel.server.job.data.mineworker.Bait;
import net.skyexcel.server.job.data.mineworker.BlastFurnace;
import net.skyexcel.server.job.data.mineworker.FeverTime;
import net.skyexcel.server.job.data.stat.*;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.job.gui.JobGUI;
import net.skyexcel.server.job.gui.JobSelectGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class JobListener implements Listener, JobPlayerData {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Job job = new Job(player);

        if (job.hasJob()) {
            if (job.getType().equals(JobType.FARM)) {
                if (player.isSneaking() && player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_HOE)) {
                    Farmer farmer = new Farmer();
                    farmer.run(player);
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Job job = new Job(player);
        if (job.hasJob()) {
            if (!job.getType().equals(JobType.NONE)) {
                if (job.getType().equals(JobType.MINEWORKER)) { //광부일때 광부 패시브를 발동한다.

                    FeverTime feverTime = new FeverTime(player);
                    feverTime.run(player, block);

                    //TODO 모든 광물 다 불러와야됨 ㅅㄱ
                    BlastFurnace blastFurnace = new BlastFurnace(player);
                    blastFurnace.run(player, block);

                    //TODO 이벤트 캔슬 시켜야됨
                    AntiFragile antiFragile = new AntiFragile();
                    antiFragile.run(player);

                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getDurability() == 0)
                        item.setDurability((short) 1);

                    item.setDurability((short) (item.getDurability() - 1));


                } else if (job.getType().equals(JobType.FARM)) { // 농부일때 농부의 축복 페시브를 발동한다.

                }
            }
        }
    }

    @EventHandler
    public void onGrow(BlockGrowEvent event) {

        BlockData blockData = event.getBlock().getBlockData();
        event.getBlock().setBlockData(event.getBlock().getBlockData());

    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            Inventory inv = event.getInventory();
            Set<Integer> slot = event.getInventorySlots();

            if (JobData.waterBucket.containsKey(player.getUniqueId())) {
                WaterBucket waterBucket = JobData.waterBucket.get(player.getUniqueId());

                if (waterBucket.getInv().equals(inv)) {
                    for (ItemStack items : event.getNewItems().values()) {
                        if (!items.getType().equals(Material.COD)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();

        Entity item = event.getCaught();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            Inventory inv = event.getClickedInventory();
            int slot = event.getSlot();
            if (JobData.gui.containsKey(player.getUniqueId())) { // 스탯 GUI를 열었을 경우
                JobGUI jobGUI = JobData.gui.get(player.getUniqueId());

                switch (jobGUI.getJobType()) {
                    case MINEWORKER -> {
                        if (JobData.slot[0] == slot) {
                            BlastFurnace blastFurnace = new BlastFurnace(player);
                            player.sendMessage("용광로 특성을 업그레이드 하였습니다.");
                        } else if (JobData.slot[1] == slot) {
                            player.sendMessage("피버타임 특성을 업그레이드 하였습니다.");
                        } else if (JobData.slot[2] == slot) {
                            player.sendMessage("단단한 곡괭이 특성을 업그레이드 하였습니다.");
                        }
                    }
                    case FARM -> {

                    }
                    case FISHERMAN -> {

                        if (JobData.FishSlot[0] == slot) {

                        } else if (JobData.FishSlot[1] == slot) {
                            if (event.isShiftClick()) {
                                WaterBucket waterBucket = new WaterBucket(player);
                                if (waterBucket.getStatPoint(player, "StatPoint") == -1) {
                                    waterBucket.onGUI(player, 1);
                                }

                            }
                        }
                    }
                }
                event.setCancelled(true);
            } else if (JobData.waterBucket.containsKey(player.getUniqueId())) {
                WaterBucket waterBucket = JobData.waterBucket.get(player.getUniqueId());
                if (event.getCurrentItem() != null) {
                    if (!event.getCurrentItem().getType().equals(Material.COD)) {
                        event.setCancelled(true);
                    }
                }

                if (waterBucket.getSlots().contains(slot)) {
                    event.setCancelled(true);
                } else if (slot == 53) {
                    waterBucket.levelUp(player);
                    event.setCancelled(true);
                } else if (slot == waterBucket.getNextSlot()) {
                    waterBucket.nextPage();
                    event.setCancelled(true);
                } else if (slot == waterBucket.getPreviousSlot()) {
                    waterBucket.previousPage();
                    event.setCancelled(true);
                }

            } else if (JobData.selectGUI.containsKey(player.getUniqueId())) { // 직업 선택
                JobSelectGUI jobSelectGUI = JobData.selectGUI.get(player.getUniqueId());

                if (!jobSelectGUI.isSelect()) {
                    if (slot == jobSelectGUI.getFARM()) {
                        jobSelectGUI.setJob(JobType.FARM);
                        jobSelectGUI.select(player);
                        jobSelectGUI.setSelect(true);

                    } else if (slot == jobSelectGUI.getFISH()) {
                        jobSelectGUI.setJob(JobType.FISHERMAN);
                        jobSelectGUI.select(player);
                        jobSelectGUI.setSelect(true);


                    } else if (slot == jobSelectGUI.getMINE()) {
                        jobSelectGUI.setJob(JobType.MINEWORKER);
                        jobSelectGUI.select(player);
                        jobSelectGUI.setSelect(true);
                    }

                } else {
                    if (slot == jobSelectGUI.getYES()) {
                        jobSelectGUI.setDefaults(player);
                        player.closeInventory();
                    } else if (slot == jobSelectGUI.getNO()) {
                        jobSelectGUI.open(player);
                        jobSelectGUI.setSelect(false);
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            if (JobData.waterBucket.containsKey(player.getUniqueId())) {

                WaterBucket waterBucket = JobData.waterBucket.get(player.getUniqueId());
                waterBucket.saveInventory(waterBucket.getPre());

                JobData.waterBucket.remove(player.getUniqueId());
            } else if (JobData.selectGUI.containsKey(player.getUniqueId())) {
                JobData.selectGUI.remove(player.getUniqueId());
            } else if (JobData.gui.containsKey(player.getUniqueId())) {
                JobData.gui.remove(player.getUniqueId());
            }
        }
    }
}
