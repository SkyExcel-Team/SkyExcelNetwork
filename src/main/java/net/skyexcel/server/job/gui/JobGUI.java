package net.skyexcel.server.job.gui;

import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class JobGUI {
    private Player player;

    private Inventory inv;

    private JobType jobType = JobType.NONE;

    public JobGUI(Player player) {
        this.player = player;
    }

    public void mineWork() {
        Inventory inv = Bukkit.createInventory(null, 27, "광부");
        this.inv = inv;
        Items.newItem(ChatColor.RED + "용광로", Material.CHEST, 0, List.of("광물을 캘시 일정확률로 구워진 광물이 드롭됩니다."), JobData.slot[0], inv);
        Items.newItem(ChatColor.YELLOW + "피버 타임", Material.CHEST, 0, List.of("다이아몬드를 캘시 일정확률로 성급함을 부여합니다."), JobData.slot[1], inv);
        Items.newItem(ChatColor.GRAY + "단단한 곡괭이", Material.CHEST, 0, List.of("일정확률로 광물을 캘시, 내구도가 달지 않습니다."), JobData.slot[2], inv);
        this.jobType = JobType.MINEWORKER;
        player.openInventory(inv);
    }
    public void farm() {

        Inventory inv = Bukkit.createInventory(null, 27, "농부");
        this.inv = inv;
        Items.newItem(ChatColor.RED + "농부의 축복", Material.CHEST, 0, List.of("광물을 캘시 일정확률로 구워진 광물이 드롭됩니다."), JobData.slot[0], inv);
        Items.newItem(ChatColor.YELLOW + "허수아비", Material.CHEST, 0, List.of("다이아몬드를 캘시 일정확률로 성급함을 부여합니다."), JobData.slot[1], inv);
        this.jobType = JobType.FARM;
        player.openInventory(inv);
    }
    public void fish() {

        Inventory inv = Bukkit.createInventory(null, 27, "낚시꾼");
        this.inv = inv;

        Items.newItem(ChatColor.WHITE + "최고급 미끼", Material.CHEST, 0, List.of("광물을 캘시 일정확률로 구워진 광물이 드롭됩니다."), JobData.slot[0], inv);
        Items.newItem(ChatColor.BLUE + "특별한 물통", Material.CHEST, 0, List.of("다이아몬드를 캘시 일정확률로 성급함을 부여합니다."), JobData.slot[1], inv);
        this.jobType = JobType.FISHERMAN;
        player.openInventory(inv);
    }

    public JobType getJobType() {
        return jobType;
    }

    public Inventory getInv() {
        return inv;
    }
}
