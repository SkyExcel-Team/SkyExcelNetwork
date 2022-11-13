package net.skyexcel.server.seconomy.data.economy;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEConomyMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;
import skyexcel.data.file.util.Flowable;

public class SEconomy extends Flowable {

    public SEconomy(OfflinePlayer player) {
        super(player, "data/", SkyExcelNetworkMain.getPlugin());
        setValuePath("money");
    }
}
