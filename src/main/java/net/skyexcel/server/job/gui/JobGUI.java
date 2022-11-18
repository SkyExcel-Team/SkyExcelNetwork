package net.skyexcel.server.job.gui;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobType;

import net.skyexcel.server.job.data.farmer.Blessing;
import net.skyexcel.server.job.data.farmer.Scarecrow;
import net.skyexcel.server.job.data.fisher.Anglers;
import net.skyexcel.server.job.data.mineworker.Bait;
import net.skyexcel.server.job.data.mineworker.BlastFurnace;
import net.skyexcel.server.job.data.mineworker.FeverTime;
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


    private Job job;
    private JobType jobType = JobType.NONE;

    public JobGUI(Player player) {
        this.player = player;
        job = new Job(player);

    }

    public void mineWork() {
        this.inv = Bukkit.createInventory(null, 27, "광부");

        BlastFurnace blastFurnace = new BlastFurnace(player);
        FeverTime feverTime = new FeverTime(player);
        Bait bait = new Bait(player);


        Items.newItem(blastFurnace.getDisplayName(), Material.CHEST, 1, List.of("", "§6§l│ §f광물을 캘시 일정확률로 §c구워진 §f광물이 §a드롭§f됩니다. ", "§6§l│ §f적용되는 광물:", "§6§l│ §6구리", "§6§l│ §f철", "§6§l│ §e금", ""), JobData.slot[0], inv);
        Items.newItem(feverTime.getDisplayName(), Material.CHEST, 1, List.of("", "§6§l│ §b다이아몬드§f를 캘시 §6일정확률§f로 §e성급함§f을 부여합니다. ", ""), JobData.slot[1], inv);
        Items.newItem(bait.getDisplayName(), Material.CHEST, 1, List.of("", "§6§l│ §6일정확률§f로 광물을 캘시, 곡괭이의 §e내구도§f가 달지 않습니다. ", ""), JobData.slot[2], inv);

        this.jobType = JobType.MINEWORKER;
        player.openInventory(inv);
    }

    public void farm() {

        this.inv = Bukkit.createInventory(null, 27, "농부");

        Blessing blessing = new Blessing(player);
        Scarecrow scarecrow = new Scarecrow(player);

        Items.newItem(blessing.getDisplayName(), Material.CHEST, 1, blessing.getDescription(), JobData.slot[0], this.inv);
        Items.newItem(scarecrow.getDisplayName(), Material.CHEST, 1, scarecrow.getDescription(), JobData.slot[1], this.inv);

        this.jobType = JobType.FARM;
        player.openInventory(this.inv);
    }

    public void fish() {

        this.inv = Bukkit.createInventory(null, 27, "낚시꾼 스텟포인트 [0]");

        Anglers anglers = new Anglers(player);

        Items.newItem(anglers.getDisplayName(), Material.FISHING_ROD, 1, List.of("", "§6§l│ §f한단계 높은 §e등급§f의 §9물고기§f(인챈트)(을)를 얻습니다. ", "",
                ChatColor.GRAY + "1/5"), JobData.FishSlot[0], inv);
        Items.newItem("§6특별한 §9물통", Material.CHEST, 1, List.of("", "§6§l│ §9물고기§f를 §6보관§f할 수 있는 §9물통 ", "§6§l│ §6효과:", "§6§l│ §9물고기§f를 잡을 시 §9물통§f으로 이동됩니다.", "§6§l│ §9물통§f은 §6레벨당 §fGUI 1줄씩 추가됩니다. §7(페이지 존재)", "§6§l│ §f특정 §6레벨 §f달성 시, §9물통§f 안에서 §a판매 §f가능", "",
                ChatColor.GRAY + "1/5"), JobData.FishSlot[1], inv);
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
