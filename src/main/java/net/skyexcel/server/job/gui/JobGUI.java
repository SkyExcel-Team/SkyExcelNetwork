package net.skyexcel.server.job.gui;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobType;

import net.skyexcel.server.job.data.farmer.Blessing;
import net.skyexcel.server.job.data.farmer.Scarecrow;
import net.skyexcel.server.job.data.fisher.Anglers;
import net.skyexcel.server.job.data.fisher.WaterBucket;
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

        Items.newItem(blastFurnace.getDisplayName(), Material.CHEST, 1, blastFurnace.getDescription(), JobData.slot[0], inv);
        Items.newItem(feverTime.getDisplayName(), Material.CHEST, 1, feverTime.getDescription(), JobData.slot[1], inv);
        Items.newItem(bait.getDisplayName(), Material.CHEST, 1, bait.getDescription(), JobData.slot[2], inv);

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
        WaterBucket waterBucket = new WaterBucket(player);
        Items.newItem(anglers.getDisplayName(), Material.FISHING_ROD, 1, anglers.getDescription(), JobData.FishSlot[0], inv);
        Items.newItem(waterBucket.getDisplayName(), Material.CHEST, 1, waterBucket.getDescription(), JobData.FishSlot[1], inv);
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
