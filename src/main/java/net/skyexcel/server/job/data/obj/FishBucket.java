package net.skyexcel.server.job.data.obj;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import skyexcel.data.file.util.Stockable;

public class FishBucket extends Stockable {

    private int level;

    private Inventory inv;

    public FishBucket(OfflinePlayer player) {
        super("inv/fish", player.getUniqueId().toString(), SkyExcelNetworkJobMain.plugin);
    }
}
