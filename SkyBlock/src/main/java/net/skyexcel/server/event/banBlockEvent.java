package net.skyexcel.server.event;

import net.skyexcel.server.data.SkyBlockData;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.ui.gui.MaterialPageMember;
import net.skyexcel.server.ui.gui.MaterialPagePartTime;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class banBlockEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (event.getClickedInventory() != null) {
            ItemStack item = event.getCurrentItem();
            if (item != null) {
                if (SkyBlockData.testPage.containsKey(player.getUniqueId())) {
                    MaterialPagePartTime gui = SkyBlockData.testPage.get(player.getUniqueId());
                    if (slot == gui.getNEXT_PAGE_SLOT()) {
                        gui.nextPage(player, event.isShiftClick());
                    } else if (slot == gui.getPREVIOUS_PAGE_SLOT()) {
                        gui.previousPage(player, event.isShiftClick());
                    } else {
                        gui.select(player, slot, event.isShiftClick());
                    }
                    event.setCancelled(true);
                } else if (SkyBlockData.memberPage.containsKey(player.getUniqueId())) {
                    MaterialPageMember gui = SkyBlockData.memberPage.get(player.getUniqueId());
                    if (slot == gui.getNEXT_PAGE_SLOT()) {
                        gui.nextPage(player, event.isShiftClick());
                    } else if (slot == gui.getPREVIOUS_PAGE_SLOT()) {
                        gui.previousPage(player, event.isShiftClick());
                    } else {
                        gui.select(player, slot, event.isShiftClick());
                    }
                    event.setCancelled(true);
                } else if (SkyBlockData.partTimePage.containsKey(player.getUniqueId())) {
                    MaterialPagePartTime gui = SkyBlockData.partTimePage.get(player.getUniqueId());
                    if (slot == gui.getNEXT_PAGE_SLOT()) {
                        gui.nextPage(player, event.isShiftClick());
                    } else if (slot == gui.getPREVIOUS_PAGE_SLOT()) {
                        gui.previousPage(player, event.isShiftClick());
                    } else {
                        gui.select(player, slot, event.isShiftClick());
                    }
                    event.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (SkyBlockData.partTimePage.containsKey(player.getUniqueId())) {

            player.sendMessage("알바 밴블록 설정을 완료 하였습니다.");
            SkyBlockData.partTimePage.remove(player.getUniqueId());

        } else if (SkyBlockData.memberPage.containsKey(player.getUniqueId())) {
            player.sendMessage("맴버 밴블록 설정을 완료 하였습니다.");
            SkyBlockData.memberPage.remove(player.getUniqueId());
        }
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

        SkyBlock islandData = new SkyBlock(playerData.getIsland());
        if(!player.isOp()){
            if (!islandData.isInIsland(player)) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void jump() {

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

        SkyBlock islandData = new SkyBlock(playerData.getIsland());

        if (!player.isOp()) {
            if (!islandData.isInIsland(player)) {
                for (Entity entity : player.getChunk().getEntities()) {
                    if (entity instanceof Player) {
                        Player players = (Player) entity;
                        SkyBlockPlayerData playersData = new SkyBlockPlayerData(players);
                        if (playersData.isOwner()) {
                            player.sendMessage("Owner 는 " + players.getDisplayName() + " 님 입니다!");
                            break;
                        }
                    }
                }
                player.sendMessage("다른 섬의 블록은 캘 수 없습니다!");
                event.setCancelled(true);
            } else { //자신의 섬 일때
                if (islandData.getOwner() != null) {
                    if (islandData.getMembers().contains(player.getUniqueId().toString())) {
                        //플레이어가 섬 주인이 아닐 경우
                        if (!islandData.getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {
                            if (islandData.getMembers().contains(player.getUniqueId().toString())) {

                                if (islandData.getBanBlockMember().contains(block.getType())) {
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                } else { // 블록을 부슨 플레이어의 섬이 자신의 섬의 Owner의 월드 이름과 다를 경우, 즉 다른 월드에서 블록을 부슬 경우
                    if (islandData.getPartTime().contains(player.getUniqueId().toString())) { //알바 밴 블록을 캘 시
                        if (islandData.getBanBlockPartTime().contains(block.getType())) { //알바가 블록을 캘 시
                            event.setCancelled(true);
                        }
                    }
                    event.setCancelled(true); //모든 월드에서의 블록 부스는것을 캔슬 시킨다.
                }
            }

        }
    }
}
