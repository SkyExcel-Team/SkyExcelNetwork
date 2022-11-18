package net.skyexcel.server.essentials.nospam.event;

import net.skyexcel.server.essentials.nospam.util.CoolDownUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (CoolDownUtils.isCoolDownNow(p)) {
            p.sendMessage("채팅 치지마 ^.^");
            e.setCancelled(true);
        } else
            CoolDownUtils.coolDown(p);
    }
}