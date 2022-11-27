package net.skyexcel.server.playerprofile.event;

import net.skyexcel.server.playerprofile.data.PlayerProfile;
import net.skyexcel.server.playerprofile.data.PlayerProfileData;
import net.skyexcel.server.playerprofile.data.PlayerProfileGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerProfileListener implements Listener {

    public PlayerProfileListener(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerProfile playerProfile = new PlayerProfile(player);
        if (playerProfile.getLong() == -1)
            playerProfile.setAmount(0);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (PlayerProfileData.playerProfileGUIHashMap.containsKey(player.getUniqueId())) {
                PlayerProfileGUI playerProfileGUI = PlayerProfileData.playerProfileGUIHashMap.get(player.getUniqueId());
                int slot = event.getSlot();
                if (slot == playerProfileGUI.getPlusSlot()) {
                }
            }
        }

    }

}
