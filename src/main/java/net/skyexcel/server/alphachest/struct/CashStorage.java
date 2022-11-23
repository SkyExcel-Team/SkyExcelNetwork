package net.skyexcel.server.alphachest.struct;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

public class CashStorage {

    private OfflinePlayer offlinePlayer;

    private Config config;

    private GUI gui;

    private Inventory inv;


    private int index = 1;

    public CashStorage(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.config = new Config("data/storages/cash/" + offlinePlayer.getUniqueId() + "/");
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        gui = new GUI(config);
    }

    public CashStorage(OfflinePlayer offlinePlayer, int index) {
        this.offlinePlayer = offlinePlayer;
        this.config = new Config("data/storages/cash/" + offlinePlayer.getUniqueId() + "/" + index);
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        gui = new GUI(config);
        this.index = index;
    }


    public void setIndex(Player player, ItemStack item) {

        if (this.config.getFileList() != null) {
            if (this.config.getFileList().length >= 4) {
                player.sendMessage("저장소가 너무 많습니다!");
                return;
            }
            index = this.config.getFileList().length + 1;

        }

        Config newConfig = new Config("data/storages/cash/" + offlinePlayer.getUniqueId() + "/" + index);
        newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
        GUI newGUI = new GUI(newConfig);
        Inventory inv = Bukkit.createInventory(null, 54, index + " 번 창고");

        player.openInventory(inv);
        item.setAmount(item.getAmount() - 1);
        newGUI.saveInventory(String.valueOf(index), inv);

    }


    public void open(Player player) {
        if (gui.getInventory(String.valueOf(index)) != null) {
            Inventory inv = gui.getInventory(String.valueOf(index));
            this.inv = inv;
            player.openInventory(inv);
        } else {
            player.sendMessage("인벤토리가 존재하지 않습니다.");
        }
    }


    public void saveStorage() {
        gui.saveInventory(String.valueOf(index), inv);
    }
}
