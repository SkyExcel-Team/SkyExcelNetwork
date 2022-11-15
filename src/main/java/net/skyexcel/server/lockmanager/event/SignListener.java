package net.skyexcel.server.lockmanager.event;

import com.google.common.collect.Lists;
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
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SignListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();


        if (block instanceof Sign) {
            Sign sign = (Sign) event.getClickedBlock().getState();
            player.openSign(sign);
        }

    }

    @EventHandler
    public void onChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        SignData signData = new SignData();

        for(String line : event.getLines()){

        }
    }

    Material[] materials = {Material.BIRCH_WALL_SIGN, Material.ACACIA_WALL_SIGN, Material.CRIMSON_WALL_SIGN, Material.OAK_WALL_SIGN, Material.DARK_OAK_WALL_SIGN, Material.JUNGLE_WALL_SIGN, Material.WARPED_WALL_SIGN, Material.MANGROVE_WALL_SIGN};


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        SignData data = new SignData();
        Block target = event.getBlockAgainst();

        if (player.isSneaking()) {
            if (Arrays.asList(materials).contains(block.getType())) {
                player.sendMessage("해당 상자를 잠궜습니다.");
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();


    }
}
