package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.fish.data.FishData;
import net.skyexcel.server.fish.data.FishStatus;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishEvent implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event){
        Player player = event.getPlayer();
        Entity fish = event.getCaught();
        if(SkyExcelNetworkFishMain.status.equals(FishStatus.Start)){
            FishData fishData = new FishData(player);
            fishData.addItem(fish);
        }
    }
}
