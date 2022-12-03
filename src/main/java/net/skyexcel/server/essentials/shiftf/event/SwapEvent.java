package net.skyexcel.server.essentials.shiftf.event;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SwapEvent implements Listener {
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();

        if (p.isSneaking()) {
            SkyExcelNetworkEssentialsMain.config.getConfig().getList("shift_f.execute").forEach(key -> {
                runCommand(p, (String) key);
            });

            e.setCancelled(true);
        }
    }

    private void runCommand(Player player, String commandLine) {
        if (commandLine.contains("[player]")) {
            player.performCommand(commandLine.replace("[player] ", ""));
        } else if (commandLine.contains("[server]")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandLine
                    .replace("[server] ", "")
                    .replace("%player%", player.getName()));
        } else if (commandLine.contains("[sound]")) {
            String sound = commandLine.replace("[sound] ", "");
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1, 1);
        }
    }
}
