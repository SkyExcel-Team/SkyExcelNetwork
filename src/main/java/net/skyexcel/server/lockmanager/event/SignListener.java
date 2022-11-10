package net.skyexcel.server.lockmanager.event;

import net.skyexcel.server.lockmanager.data.SignData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        SignData data = new SignData();
        Block target = event.getBlockAgainst();

        if (player.isSneaking()) {
            if (block instanceof Sign && target instanceof Chest) {
                player.sendMessage("해당 상자를 잠궜습니다.");
                Sign sign = (Sign) block.getBlockData();
                sign.setLine(0, player.getDisplayName());
                data.addSign(player);

            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();


    }
}
