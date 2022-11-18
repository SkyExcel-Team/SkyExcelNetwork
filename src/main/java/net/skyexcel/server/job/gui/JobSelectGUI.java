package net.skyexcel.server.job.gui;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.WaterBucket;
import net.skyexcel.server.job.data.type.Fisher;
import net.skyexcel.server.packet.Inventory.InventoryUpdate;
import net.skyexcel.server.trade.util.Items;
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


    public void open(Player player) {
        if (inv == null) {
            Inventory inv = Bukkit.createInventory(null, 27, title);

            Items.newItem("어부", Material.FISHING_ROD, 1, List.of(), FISH, inv);
            Items.newItem("농부", Material.NETHERITE_HOE, 1, List.of(), FARM, inv);
            Items.newItem("광부", Material.NETHERITE_PICKAXE, 1, List.of(), MINE, inv);

            this.inv = inv;
            player.openInventory(inv);
        } else {
            inv.clear();
            InventoryUpdate.updateInventory(SkyExcelNetworkJobMain.plugin, player, title);

            Items.newItem("어부", Material.FISHING_ROD, 1, List.of(), FISH, inv);
            Items.newItem("농부", Material.NETHERITE_HOE, 1, List.of(), FARM, inv);
            Items.newItem("광부", Material.NETHERITE_PICKAXE, 1, List.of(), MINE, inv);
        }
    }

    public void select(Player player) {
        inv.clear();
        InventoryUpdate.updateInventory(SkyExcelNetworkJobMain.plugin, player, "정말? ? ? ? ? ? ???? ? ??");
        Items.newItem(YES_NAME, Material.BLUE_WOOL, 1, List.of(), YES, inv);
        Items.newItem(NO_NAME, Material.RED_WOOL, 1, List.of(), NO, inv);
    }


    public void setDefaults(Player player) {
        switch (jobType) {
            case MINEWORKER -> {

            }
            case FARM -> {
            }
            case FISHERMAN -> {
                Fisher fisher = new Fisher();
                WaterBucket waterBucket = new WaterBucket();
                waterBucket.setDefault(player);
                fisher.setDefault(player);
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
