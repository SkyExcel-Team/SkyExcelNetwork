package net.skyexcel.server.regionafk.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.util.Flowable;


public class AFK extends Flowable {

    public AFK(OfflinePlayer player) {
        super(player, "data/", SkyExcelNetworkMain.getPlugin());
        setValuePath("AFK");
    }
}
