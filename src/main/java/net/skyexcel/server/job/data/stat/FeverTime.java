package net.skyexcel.server.job.data.stat;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FeverTime implements Percent {

    public void run(Player player, Block block) {
        double percent = 100;
        if (block.getType().equals(Material.DIAMOND_ORE)) {
            if (chance(percent)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 50, 1));
            }
        }
    }
}
