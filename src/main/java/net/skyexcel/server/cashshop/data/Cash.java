package net.skyexcel.server.cashshop.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.util.Flowable;


public class Cash extends Flowable {
    
    public Cash(OfflinePlayer player) {
        super(player, "data/", SkyExcelNetworkMain.getPlugin());
        setValuePath("cash");
    }
}
