package net.skyexcel.server.job.data.mineworker;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.PercentData;
import net.skyexcel.server.job.data.StatMeta;
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

import java.util.List;

public class FeverTime extends StatMeta implements Percent, JobPlayerData {

    private OfflinePlayer player;


    public FeverTime(OfflinePlayer player) {
        super("피버 타임", List.of("", "§6§l│ §b다이아몬드§f를 캘시 §6일정확률§f로 §e성급함§f을 부여합니다. ", ""));
        this.player = player;
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

        Config config = new Config("job/" + player.getUniqueId() + "/FeverTime");

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }

}
