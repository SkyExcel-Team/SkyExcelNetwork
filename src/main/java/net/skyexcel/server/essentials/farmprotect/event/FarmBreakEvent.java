package net.skyexcel.server.essentials.farmprotect.event;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import skyexcel.data.file.Config;

public class FarmBreakEvent implements Listener {
    @EventHandler
    public void onEntityInteract(EntityInteractEvent event) {
        Block block = event.getBlock();
        if(block != null && block.getType() == Material.FARMLAND) {
            Entity entity = event.getEntity();

            if (entity instanceof Player p) {
                Config data = new Config("data/" + p.getUniqueId());
                data.setPlugin(SkyExcelNetworkEssentialsMain.plugin);
                data.loadDefualtConfig();

                if (data.getBoolean("farmprotect"))
                    event.setCancelled(true);
            } else
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.PHYSICAL) {
            if(event.getClickedBlock().getType() == Material.FARMLAND) {
                Player p = event.getPlayer();

                Config data = new Config("data/" + p.getUniqueId());
                data.setPlugin(SkyExcelNetworkEssentialsMain.plugin);
                data.loadDefualtConfig();

                if (data.getBoolean("farmprotect"))
                    event.setCancelled(true);
            }
        }
    }
}
