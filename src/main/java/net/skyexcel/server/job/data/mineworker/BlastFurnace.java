package net.skyexcel.server.job.data.mineworker;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.PercentData;
import net.skyexcel.server.job.data.StatMeta;
import net.skyexcel.server.job.data.stat.Percent;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;

public class BlastFurnace extends StatMeta implements Percent, JobPlayerData {
    private Player player;

    private String path = "job/";

    private Job job;

    /**
     * 용광로 1 렙을 업하기 위하여 필요한 레벨.
     */
    private final double step = 5.0;


    /**
     * 용광로의 최대 레벨 만약, -1이라면 무제한이 됨.
     */
    private final double max = 100;


    public BlastFurnace(Player player) {
        super("용광로");
        this.player = player;
        this.job = new Job(player);
    }

    public void run(Player player, Block block) {
        PercentData percentData = new PercentData();

        if (percentData.getBlastFuranceChance()) {
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

    public void setDefault() {
        path = path + player.getUniqueId();
        Config config = new Config(path + "/blessing");

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }
}
