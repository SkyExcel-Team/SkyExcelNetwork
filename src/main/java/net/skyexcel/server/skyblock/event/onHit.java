package net.skyexcel.server.skyblock.event;

import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onHit implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();


        if (entity instanceof Player && damager instanceof Player) {

            Player player = (Player) entity;

            Player target = (Player) damager;


            SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);
            SkyBlock targetIsland = new SkyBlock(targetData.getIsland());


            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock playerIsland = new SkyBlock(playerData.getIsland());

            if (playerIsland.isPvp()) {
                target.sendMessage("强 해당 섬에서는 PVP가 비활성화 되어있습니다");
                event.setCancelled(true);
            }
        }
    }
}
