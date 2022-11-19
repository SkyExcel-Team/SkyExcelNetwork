package net.skyexcel.server.essentials.shiftf.event;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.essentials.autoclean.Util.ClearUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
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

    private void runCommand(Player p, String cmd) {
        if (cmd.contains("[player]")) {
            p.performCommand(cmd.replace("[player] ", ""));
        } else if (cmd.contains("[server]")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd
                    .replace("[server] ", "")
                    .replace("%player%", p.getName()));
        } else if (cmd.contains("[sound]")) {
            String sound = cmd.replace("[sound] ", "");
            p.playSound(p.getLocation(), Sound.valueOf(sound), 1, 1);
        }
    }
}
