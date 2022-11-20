package net.skyexcel.server.job.gui;

import net.skyexcel.api.packet.Inventory.InventoryUpdate;
import net.skyexcel.api.util.Items;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.farmer.Blessing;
import net.skyexcel.server.job.data.fisher.Anglers;
import net.skyexcel.server.job.data.farmer.Scarecrow;
import net.skyexcel.server.job.data.fisher.WaterBucket;
import net.skyexcel.server.job.data.mineworker.Bait;
import net.skyexcel.server.job.data.mineworker.BlastFurnace;
import net.skyexcel.server.job.data.mineworker.FeverTime;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.job.data.type.Fisher;
import net.skyexcel.server.job.data.type.MineWorker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class JobSelectGUI {


    private Inventory inv;
    private final int FISH = 11;
    private final int FARM = 13;

    private final int MINE = 15;

    private final int YES = 12;
    private final int NO = 14;

    private final String title = "직업을 선택하세요!";

    private final String YES_NAME = "네!";

    private final String NO_NAME = "응아니야!";
    private JobType jobType = JobType.NONE;

    private boolean isSelect = false;

    private Items items = new Items();
    

    public void open(Player player) {
        if (inv == null) {
            Inventory inv = Bukkit.createInventory(null, 27, title);

            items.newItem("어부", Material.FISHING_ROD, 1, List.of(), FISH, inv);
            items.newItem("농부", Material.NETHERITE_HOE, 1, List.of(), FARM, inv);
            items.newItem("광부", Material.NETHERITE_PICKAXE, 1, List.of(), MINE, inv);

            this.inv = inv;
            player.openInventory(inv);
        } else {
            inv.clear();
            InventoryUpdate.updateInventory(SkyExcelNetworkMain.getPlugin(), player, title);

            items.newItem("어부", Material.FISHING_ROD, 1, List.of(), FISH, inv);
            items.newItem("농부", Material.NETHERITE_HOE, 1, List.of(), FARM, inv);
            items.newItem("광부", Material.NETHERITE_PICKAXE, 1, List.of(), MINE, inv);
        }
    }

    public void select(Player player) {
        inv.clear();
        InventoryUpdate.updateInventory(SkyExcelNetworkMain.getPlugin(), player, "정말? ? ? ? ? ? ???? ? ??");
        items.newItem(YES_NAME, Material.BLUE_WOOL, 1, List.of(), YES, inv);
        items.newItem(NO_NAME, Material.RED_WOOL, 1, List.of(), NO, inv);
    }


    public void setDefaults(Player player) {
        Job job = new Job(player);
        switch (jobType) {
            case MINEWORKER -> {
                MineWorker mineWorker = new MineWorker();

                FeverTime feverTime = new FeverTime(player);
                BlastFurnace blastFurnace = new BlastFurnace(player);
                Bait bait = new Bait(player);
                blastFurnace.setDefault();
                feverTime.setDefault();
                bait.setDefault();
                mineWorker.setDefault(player);

                job.setJobType(JobType.MINEWORKER);

            }
            case FARM -> {
                Farmer farmer = new Farmer();

                Blessing blessing = new Blessing(player);
                Scarecrow scarecrow = new Scarecrow(player);
                scarecrow.setDefault();
                farmer.setDefault(player);
                blessing.setDefault();

                job.setJobType(JobType.FARM);
            }
            case FISHERMAN -> {
                Fisher fisher = new Fisher();
                WaterBucket waterBucket = new WaterBucket(player);
                Anglers anglers = new Anglers(player);
                waterBucket.setDefault(player);
                fisher.setDefault(player);
                anglers.setDefault();

                job.setJobType(JobType.FISHERMAN);
            }
        }
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setJob(JobType jobType) {
        this.jobType = jobType;
    }


    public int getNO() {
        return NO;
    }

    public int getYES() {
        return YES;
    }

    public int getFARM() {
        return FARM;
    }

    public int getFISH() {
        return FISH;
    }

    public int getMINE() {
        return MINE;
    }

    public Inventory getInv() {
        return inv;
    }
}
