package net.skyexcel.server.job.data.mineworker;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.PercentData;
import net.skyexcel.server.job.data.stat.Percent;
import net.skyexcel.server.job.data.stat.Statable;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import skyexcel.data.file.Config;

public class FeverTime extends Statable implements Percent, JobPlayerData {

    private Player player;


    public FeverTime(OfflinePlayer player) {
        super("피버 타임", "job/" + player.getUniqueId() + "/FeverTime", player);
    }

    public void run(Player player, Block block) {

        PercentData percentData = new PercentData();
        if (block.getType().equals(percentData.getFeverTimeType())) {
            if (percentData.getFeverTimeChance()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, percentData.getFeverTimeAmplifier(), percentData.getFeverTimeAmplifier()));
            }
        }
    }


    public void setDefault() {

        Config config = new Config(getPath());

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }

}
