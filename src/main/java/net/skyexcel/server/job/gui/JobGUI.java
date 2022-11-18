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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        Items.newItem("§c용광로", Material.CHEST, 1, List.of("", "§6§l│ §f광물을 캘시 일정확률로 §c구워진 §f광물이 §a드롭§f됩니다. ", "§6§l│ §f적용되는 광물:", "§6§l│ §6구리", "§6§l│ §f철", "§6§l│ §e금", ""), JobData.slot[0], inv);
        Items.newItem("§e피버 타임", Material.CHEST, 1, List.of("", "§6§l│ §b다이아몬드§f를 캘시 §6일정확률§f로 §e성급함§f을 부여합니다. ", ""), JobData.slot[1], inv);
        Items.newItem("§b단단한 곡괭이", Material.CHEST, 1, List.of("", "§6§l│ §6일정확률§f로 광물을 캘시, 곡괭이의 §e내구도§f가 달지 않습니다. ", ""), JobData.slot[2], inv);
        this.jobType = JobType.MINEWORKER;
        player.openInventory(inv);
    }

    public void farm() {

        this.inv = Bukkit.createInventory(null, 27, "농부");

        Items.newItem("§6농부의 축복", Material.CHEST, 1, List.of("", "§6§l│ §f농작물을 캘시, §6일정확률§f로 §6농작물§f을 §a추가 §f드롭됩니다. ", "§6§l│ §f적용되는 농작물:", "§6§l│ §f밀", "§6§l│ §f 감자", "§6§l│ §f 당근", "§6§l│ §f ", ""), JobData.slot[0], this.inv);
        Items.newItem("§6허수아비", Material.CHEST, 1, List.of("", "§6§l│ §6허수아비§f를 소환해, 주변의 §6농작물§f을 빠르게 자라게 해줍니다. ", "§6§l│ §f사용 방법:", "§6§l│ §f괭이를 들고 §6쉬프트 §f+ §6우클릭", ""), JobData.slot[1], this.inv);
        this.jobType = JobType.FARM;
        player.openInventory(this.inv);
    }

    public void fish() {

        Inventory inv = Bukkit.createInventory(null, 27, "낚시꾼 스텟포인트 [0] ");

        Items.newItem("§c최고급 §9미끼", Material.FISHING_ROD, 1, List.of("", "§6§l│ §f한단계 높은 §e등급§f의 §9물고기§f(인챈트)(을)를 얻습니다. ", "",
                ChatColor.GRAY + "1/5"), JobData.FishSlot[0], inv);
        Items.newItem("§6특별한 §9물통", Material.CHEST, 1, List.of("", "§6§l│ §9물고기§f를 §6보관§f할 수 있는 §9물통 ", "§6§l│ §6효과:", "§6§l│ §9물고기§f를 잡을 시 §9물통§f으로 이동됩니다.", "§6§l│ §9물통§f은 §6레벨당 §fGUI 1줄씩 추가됩니다. §7(페이지 존재)", "§6§l│ §f특정 §6레벨 §f달성 시, §9물통§f 안에서 §a판매 §f가능", "",
                ChatColor.GRAY + "1/5"), JobData.FishSlot[1], inv);
        this.jobType = JobType.FISHERMAN;

        this.inv = inv;
        player.openInventory(inv);
    }

    public JobType getJobType() {
        return jobType;
    }

    public Inventory getInv() {
        return inv;
    }


}
