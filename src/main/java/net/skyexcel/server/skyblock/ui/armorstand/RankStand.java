package net.skyexcel.server.skyblock.ui.armorstand;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class RankStand {

    public void create(){
        World world = Bukkit.getWorld("world");

        for(int i = 0; i <= 10; i++){
            ArmorStand armorStand = (ArmorStand) world.spawnEntity(
                    SkyExcelNetworkSkyBlockMain.config.getLocation("").add(0,i,0),
                    EntityType.ARMOR_STAND);

            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);
            armorStand.setCustomName("%SRank_" + i + "%. " + "%SName_" + i+"% %SLevel_" + i + "%");
        }
    }
}
