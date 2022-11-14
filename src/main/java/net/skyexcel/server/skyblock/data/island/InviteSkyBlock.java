package net.skyexcel.server.skyblock.data.island;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class InviteSkyBlock {

    private Player player;


    public InviteSkyBlock(Player player) {
        this.player = player;
    }

    public void send(Player target) {
        TextComponent accept = new TextComponent("§a수락");

        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("家 §a클릭하여 수락하세요!").create()));
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 수락 " + player.getDisplayName()));

        TextComponent deny = new TextComponent("§c거절");

        deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("家 §c클릭하여 거절하세요").create()));
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 거절 " + player.getDisplayName()));

        TextComponent result = new TextComponent("家 §6" + player.getDisplayName() + "§f님에게 섬 초대 요청이 왔습니다! ");
        result.addExtra(accept);
        result.addExtra("|");
        result.addExtra(deny);

        target.spigot().sendMessage(result);
        player.sendMessage("家 §6" + target.getDisplayName() + "§f님에게 섬 §a초대§f를 보냈습니다!");
    }

}
