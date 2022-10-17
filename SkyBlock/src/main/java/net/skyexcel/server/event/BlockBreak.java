package net.skyexcel.server.event;

import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

        SkyBlock islandData = new SkyBlock(playerData.getIsland());

        //블록을 캔 플레이어섬이름이 해당 섬 Owner와 같을 경우.
        if (!player.isOp()) {
            if (Objects.requireNonNull(Bukkit.getWorld(islandData.getOwner())).getName().equalsIgnoreCase(player.getWorld().getName())) {
                if (islandData.getOwner() != null) {
                    if (!islandData.getOwner().equalsIgnoreCase(player.getUniqueId().toString())) { //플레이어가 섬 주인이 아닐 경우
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
