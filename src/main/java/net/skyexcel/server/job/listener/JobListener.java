package net.skyexcel.server.job.listener;


import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.*;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.job.gui.JobGUI;
import net.skyexcel.server.job.gui.JobSelectGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class JobListener implements Listener, JobPlayerData {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Job job = new Job(player);
        job.setJobType(JobType.FARM);

        if (job.hasJob()) {

            if (player.isSneaking() && player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_HOE)) {
                Farmer farmer = new Farmer();
                farmer.run(player);

            }

        } else {
            player.sendMessage("직업이 없습니다!");
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Job job = new Job(player);
        if (job.hasJob()) {
            job.setJobType(JobType.MINEWORKER);
            if (job.getType().equals(JobType.MINEWORKER)) { //광부일때 광부 패시브를 발동한다.

                FeverTime feverTime = new FeverTime();
                feverTime.run(player, block);

                //TODO 모든 광물 다 불러와야됨 ㅅㄱ
                BlastFurnace blastFurnace = new BlastFurnace();
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

    @EventHandler
    public void onGrow(BlockGrowEvent event) {

    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            Inventory inv = event.getInventory();
            Set<Integer> slot = event.getInventorySlots();

            if (JobData.waterBucket.containsKey(player.getUniqueId())) {
                WaterBucket waterBucket = JobData.waterBucket.get(player.getUniqueId());

                if (waterBucket.getInv().equals(inv)) {
                    if (waterBucket.getSlots().contains(slot)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void Change(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        ItemStack previous = player.getInventory().getItem(event.getPreviousSlot());
        Job job = new Job(player);
        if (job.hasJob()) {
            job.setJobType(JobType.FISHERMAN);
            if (job.getType().equals(JobType.FISHERMAN)) {
                Bait bait = new Bait();
                bait.run(player, item, previous);
            } else {
                player.sendMessage("응 너 어부아ㅏㅇ님");
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
                System.out.println(jobGUI.getJobType());

                switch (jobGUI.getJobType()) {
                    case MINEWORKER -> {
                        if (JobData.slot[0] == slot) {
                            BlastFurnace blastFurnace = new BlastFurnace();
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
                                WaterBucket waterBucket = new WaterBucket();
                                if (waterBucket.getStatPoint(player, "StatPoint") == -1) {
                                    waterBucket.onGUI(player);
                                }

                            }
                        }
                    }
                }
                event.setCancelled(true);
            } else if (JobData.waterBucket.containsKey(player.getUniqueId())) {
                WaterBucket waterBucket = JobData.waterBucket.get(player.getUniqueId());

                if (waterBucket.getInv().equals(inv)) {
                    if (waterBucket.getSlots().contains(slot)) {
                        event.setCancelled(true);
                    } else if (slot == 53) {
                        waterBucket.levelUp(player);
                        event.setCancelled(true);
                    }
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
                        jobSelectGUI.setDefaults(player);
                    } else if (slot == jobSelectGUI.getMINE()) {
                        jobSelectGUI.setJob(JobType.MINEWORKER);
                        jobSelectGUI.select(player);
                        jobSelectGUI.setSelect(true);
                    }
                } else {
                    if (slot == jobSelectGUI.getYES()) {
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
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();


    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            if (JobData.waterBucket.containsKey(player.getUniqueId())) {
                JobData.waterBucket.remove(player.getUniqueId());
            } else if (JobData.selectGUI.containsKey(player.getUniqueId())) {
                JobData.selectGUI.remove(player.getUniqueId());
            } else if (JobData.gui.containsKey(player.getUniqueId())) {
                JobData.gui.remove(player.getUniqueId());
            }
        }
    }
}
