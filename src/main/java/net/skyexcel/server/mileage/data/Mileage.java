package net.skyexcel.server.mileage.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;
import skyexcel.data.file.util.Flowable;

public class Mileage extends Flowable {
    public Mileage(OfflinePlayer player) {
        super(player, "data/", SkyExcelNetworkMain.getPlugin());
        setValuePath("mileage");
    }
}
