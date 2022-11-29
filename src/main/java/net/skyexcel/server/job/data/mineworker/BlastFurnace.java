package net.skyexcel.server.job.data.mineworker;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.PercentData;
import net.skyexcel.server.job.data.StatMeta;
import net.skyexcel.server.job.data.stat.Leveling;
import net.skyexcel.server.job.data.stat.Percent;
import org.bukkit.Material;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;

import java.util.List;

public class BlastFurnace extends StatMeta implements Percent, JobPlayerData, Leveling {
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
        super("용광로", List.of("",
                "§6§l│ §f광물을 캘시 일정확률로 §c구워진 §f광물이 §a드롭§f됩니다. ",
                "§6§l│ §f적용되는 광물:",
                "§6§l│ §6구리",
                "§6§l│ §f철",
                "§6§l│ §e금",
                ""));
        this.player = player;
        this.job = new Job(player);
    }

    public void levelUp() {
        double now = getStatPoint(player);

        double level = getLevel(player, "");
        if (now > step) { //현제

        }
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
        Config config = new Config(path + "/BlastFurnace");

        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        config.getConfig().set("level", 0);
        config.saveConfig();
    }

    @Override
    public void setStatPoint(OfflinePlayer player, String name, double value) {
        JobPlayerData.super.setStatPoint(player, name, value);
    }

    /**
     * 플레이어 스탯 포인트가 존재하지 않을 때 무조건 value 값으로 저장을 합니다.
     *
     * @param player - 스텟 포인트를 저장할 곳.
     * @param name   스텟 포인트
     * @param value
     */
    @Override
    public void increase(OfflinePlayer player, String name, double value) {
        JobPlayerData.super.increase(player, name, value);
    }

    /**
     * 플레이어 스탯 포인트가 존재하지 않을 때 save가 true일경우, value값으로 저장을 합니다.
     *
     * @param player - 스텟 포인트를 저장할 곳.
     * @param name   스텟 포인트
     * @param value
     * @param save
     */
    @Override
    public void increase(OfflinePlayer player, String name, double value, boolean save) {
        JobPlayerData.super.increase(player, name, value, save);
    }

    @Override
    public void decrease(OfflinePlayer player, String name, double value) {
        JobPlayerData.super.decrease(player, name, value);
    }

    @Override
    public double getStatPoint(OfflinePlayer player, String name) {
        return JobPlayerData.super.getStatPoint(player, name);
    }

    @Override
    public double getStatPoint(OfflinePlayer player) {
        return JobPlayerData.super.getStatPoint(player);
    }

    @Override
    public void setLevel(OfflinePlayer player, double value) {
        JobPlayerData.super.setLevel(player, value);
    }

    @Override
    public double getLevel(OfflinePlayer player, String name) {
        return JobPlayerData.super.getLevel(player, name);
    }

    @Override
    public int getLevel(OfflinePlayer player) {
        return JobPlayerData.super.getLevel(player);
    }

    @Override
    public boolean chance(double percent) {
        return Percent.super.chance(percent);
    }
}
