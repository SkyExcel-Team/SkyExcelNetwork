package net.skyexcel.server.job.data.stat;

import net.skyexcel.server.job.data.PercentData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FeverTime implements Percent {

    public void run(Player player, Block block) {

        PercentData percentData = new PercentData();
        if (block.getType().equals(percentData.getFeverTimeType())) {
            if (percentData.getFeverTimeChance()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, percentData.getFeverTimeAmplifier(), percentData.getFeverTimeAmplifier()));
            }
        }
    }
}
