package net.skyexcel.server.job.data.stat;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlastFurnace implements Percent {
    private Material[] dropItems = {Material.DIAMOND, Material.EMERALD,};

    public void run(Player player, Block block) {

        switch (block.getType()) {
            case DIAMOND_ORE -> {
                if (chance(100)) {
                    player.sendMessage("페시브 발동!");
                }
            }
        }
    }
}
