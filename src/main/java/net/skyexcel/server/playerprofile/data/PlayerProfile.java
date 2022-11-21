package net.skyexcel.server.playerprofile.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;
import skyexcel.data.file.util.Flowable;

public class PlayerProfile extends Flowable {
    private OfflinePlayer player;

    public PlayerProfile(OfflinePlayer player) {
        super(player, "data/", SkyExcelNetworkMain.getPlugin());
        this.player = player;
        setValuePath("popularlity");
    }



    @Override
    public boolean sub(double amount) {
        setAmount(getDouble() - amount);
        return true;
    }


}
