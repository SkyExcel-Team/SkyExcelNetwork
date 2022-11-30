package net.skyexcel.server.afkregion.data;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.math.BlockVector3;
import net.skyexcel.server.SkyExcelNetworkMain;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class AFKArea {

    private Player player;

    private String name;

    private Config config;


    public AFKArea(Player player, String name) {
        this.player = player;
        this.name = name;
        this.config = new Config(name);
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }


    public void create() {
        try {
            BlockVector3 pos1 = SkyExcelNetworkMain.WorldEdit.getSession(player).getSelection().getBoundingBox().getPos1();
            BlockVector3 pos2 = SkyExcelNetworkMain.WorldEdit.getSession(player).getSelection().getBoundingBox().getPos2();

            Location loc = new Location(player.getWorld(), pos1.getX(), pos1.getY(), pos1.getZ());
            Location loc2 = new Location(player.getWorld(), pos2.getX(), pos2.getY(), pos2.getZ());

            config.setLocation("pos1", loc);
            config.setLocation("pos2", loc2);

            player.sendMessage("성공적으로 " + name + " 지역이 생성 되었습니다.");
        } catch (IncompleteRegionException e) {
            throw new RuntimeException(e);
        }
    }
}
