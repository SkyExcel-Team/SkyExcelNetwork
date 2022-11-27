package net.skyexcel.server.chatchannel.event;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.chatchannel.data.ChatChannel;
import net.skyexcel.server.chatchannel.data.ChatData;
import net.skyexcel.server.chatchannel.data.ChatRecord;
import net.skyexcel.server.chatchannel.util.Translate;
import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;
import java.util.UUID;

public class ChatListener implements Listener {

    private final String prefix = "> §7";

    private final String split = " : ";

    private boolean isCancelled = false;


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ChatData chatData = new ChatData(player);
        player.sendMessage(chatData.getChatChannel().getName() + " 채팅 채널에 입장하였습니다!");

    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (SkyExcelNetworkEssentialsMain.chatCoolDown.isCoolDownNow(player)) {
            if (!player.isOp()) {
                player.sendMessage("채팅 치지마 ^.^");
                event.setCancelled(true);
                return;
            }
        } else {
            SkyExcelNetworkEssentialsMain.chatCoolDown.coolDown(player, SkyExcelNetworkEssentialsMain.config.getConfig().getLong("no_spam.chat.coolTick"));
        }

        if (!isCancelled) {
            ChatData chatData = new ChatData(player);
            if (chatData.getChatChannel().equals(ChatChannel.GLOBAL)) {
                sendMessage(player, event.getMessage(), Bukkit.getOnlinePlayers());
            } else if (chatData.getChatChannel().equals(ChatChannel.LOCAL)) {

                Bukkit.getScheduler().runTaskLater(SkyExcelNetworkMain.getPlugin(), () -> {
                    for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), 100, 100, 100)) {
                        if (entity instanceof Player players) {
                            sendMessage(player, event.getMessage(), players);
                        }
                    }
                }, 0L);

            } else if (chatData.getChatChannel().equals(ChatChannel.SKYBLOCK)) {
                SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                if (playerData.hasIsland()) {
                    SkyBlock skyBlock = new SkyBlock(playerData.getIsland());
                    for (String name : skyBlock.getMembers()) {
                        Player member = Bukkit.getPlayer(UUID.fromString(name));

                        sendMessage(player, event.getMessage(), player);

                        if (member.isOnline()) {
                            sendMessage(player, event.getMessage(), member);
                        }
                    }
                }
            }
        }
        event.setCancelled(true);
    }


    public void sendMessage(Player player, String msg, Player target) {
        ChatRecord record = new ChatRecord(player.getUniqueId().toString());
        TextComponent tPrefix = new TextComponent(prefix);

        TextComponent tPlayer = new TextComponent(player.getDisplayName());
        tPlayer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a클릭하여 프로필을 열어~").create()));

        tPlayer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/프로필 열기 " + player.getDisplayName()));

        TextComponent tSplit = new TextComponent(split);

        TextComponent message = new TextComponent(msg);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + Translate.getDate()).create()));


        tPrefix.addExtra(tPlayer);
        tPrefix.addExtra(tSplit);
        tPrefix.addExtra(message);

        target.spigot().sendMessage(tPrefix);
        record.record(player, msg);
    }


    public void sendMessage(Player player, String msg, Collection<? extends Player> players) {
        ChatRecord record = new ChatRecord(player.getUniqueId().toString());
        TextComponent tPrefix = new TextComponent(prefix);

        TextComponent tPlayer = new TextComponent(player.getDisplayName());
        tPlayer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a클릭하여 프로필을 열어~").create()));

        tPlayer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/프로필 열기 " + player.getDisplayName()));

        TextComponent tSplit = new TextComponent(split);

        TextComponent message = new TextComponent(msg);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + Translate.getDate()).create()));


        tPrefix.addExtra(tPlayer);
        tPrefix.addExtra(tSplit);
        tPrefix.addExtra(message);

        players.forEach(online -> {
            online.spigot().sendMessage(tPrefix);
        });
        record.record(player, msg);
    }


    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}