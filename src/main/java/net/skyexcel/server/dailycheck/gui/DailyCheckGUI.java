package net.skyexcel.server.dailycheck.gui;

import net.skyexcel.server.dailycheck.data.DailyCheck;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DailyCheckGUI {

    private OfflinePlayer offlinePlayer;

    private DailyCheck dailyCheck;


    public DailyCheckGUI(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.dailyCheck = new DailyCheck(offlinePlayer);
    }

    public void open(Player player){

        Inventory inv = Bukkit.createInventory(null,54,"");

    }
}
