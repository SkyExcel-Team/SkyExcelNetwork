package net.skyexcel.server.event;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.server.data.ChatRecord;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class Chat implements Listener {

    private final String prefix = "> ";

    private final String split = " : ";


    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        ChatRecord record = new ChatRecord(player.getUniqueId().toString());
        TextComponent tPrefix = new TextComponent(prefix);

        TextComponent tPlayer = new TextComponent(player.getDisplayName());
        tPlayer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a클릭하여 섬을 구경하세요!").create()));

        tPlayer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 방문 " + player.getDisplayName()));

        TextComponent tSplit = new TextComponent(split);

        TextComponent message = new TextComponent(event.getMessage());
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + Translate.getDate()).create()));


        tPrefix.addExtra(tPlayer);
        tPrefix.addExtra(tSplit);
        tPrefix.addExtra(message);

        for (Player online : Bukkit.getOnlinePlayers()) {

            online.spigot().sendMessage(tPrefix);
        }
        record.record(player, event.getMessage());

        event.setCancelled(true);

    }
}
