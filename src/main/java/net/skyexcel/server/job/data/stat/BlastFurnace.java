package net.skyexcel.server.job.data.stat;

import net.skyexcel.server.job.data.StatMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlastFurnace extends StatMeta implements Percent {




    public BlastFurnace() {
        super("용광로");
    }

    public void run(Player player, Block block) {

        if (chance(100)) {
            switch (block.getType()) {
                case IRON_ORE, DEEPSLATE_IRON_ORE -> {
                    player.getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_INGOT));
                }
                case GOLD_ORE, DEEPSLATE_GOLD_ORE -> {
                    player.getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
                }
                case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> {
                    player.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LAPIS_LAZULI));
                }

            }
        }

    }
}
