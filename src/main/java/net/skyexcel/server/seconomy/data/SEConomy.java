package net.skyexcel.server.seconomy.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.util.Flowable;


public class SEConomy extends Flowable {
    
    public SEConomy(OfflinePlayer player) {
        super(player, "data/", SkyExcelNetworkMain.getPlugin());
        setValuePath("money");
    }
}
