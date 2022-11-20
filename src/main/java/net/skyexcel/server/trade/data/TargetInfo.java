package net.skyexcel.server.trade.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.trade.gui.TradeGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TargetInfo extends TradeGUI {

    private Inventory inv = getInv();

    public TargetInfo(Player player) {
        super(player);
    }


    public void updateInventory() {
        Bukkit.getScheduler().runTaskTimer(SkyExcelNetworkMain.getPlugin(), new Runnable() {
            @Override
            public void run() {

            }
        }, 0, 20);
    }
}
