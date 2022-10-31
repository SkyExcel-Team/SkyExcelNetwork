package net.skyexcel.server.cmd;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.server.Request;
import net.skyexcel.server.data.Data;
import net.skyexcel.server.data.TradeGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TradeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Player target;
            TradeGUI tradeGUI;
            if (args.length > 0) {
                Request request = new Request();
                switch (args[0]) {
                    case "요청":
                        if (args.length > 1) {
                            target = Bukkit.getPlayer(args[1]);
                            tradeGUI = new TradeGUI();

                            if (Request.send(request, target, player)) {
                                TextComponent accept = new TextComponent("§a수락");

                                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a클릭하여 수락하세요!").create()));
                                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/거래 수락 " + player.getDisplayName()));

                                TextComponent deny = new TextComponent("§c거절");

                                deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c클릭하여 거절하세요").create()));
                                deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/거래 거절 " + player.getDisplayName()));

                                TextComponent result = new TextComponent(player.getDisplayName() + " 님에게 거래 요청이 왔습니다! ");
                                result.addExtra(accept);
                                result.addExtra("|");
                                result.addExtra(deny);

                                target.spigot().sendMessage(result);
                                player.sendMessage("초대를 보냈습니다!");
                                tradeGUI.setPlayer(player);
                                tradeGUI.setTarget(target);
                                Data.tradeGui.put(player.getUniqueId(), tradeGUI);
                                Data.tradeGui.put(target.getUniqueId(), tradeGUI);
                            }
                        }
                        break;
                    case "수락":
                        target = Bukkit.getPlayer(args[1]);

                        assert target != null;
                        tradeGUI = Data.tradeGui.get(target.getUniqueId());
                        tradeGUI.setTarget(target);
                        tradeGUI.openGUI();

                        tradeGUI = Data.tradeGui.get(player.getUniqueId());
                        tradeGUI.setTarget(player);
                        tradeGUI.openGUI();

                        if (Request.accept(request, player, target)) {
                            player.sendMessage("수락을 받았습니다!");
                        }
                        break;
                    case "거절":
                        if (args.length > 1) {
                            target = Bukkit.getPlayer(args[1]);
                            player.sendMessage(target.getDisplayName() + " 님의 거래를 거절했습니다!");
                        }
                        break;
                    case "완료":
                        if (args.length > 1) {
                            target = Bukkit.getPlayer(args[1]);
                            player.sendMessage("거래를 완료 하였습니다!");
                        }
                        break;
                    case "취소":
                        if (args.length > 1) {
                            target = Bukkit.getPlayer(args[1]);
                            player.sendMessage(target.getDisplayName() + " 님의 거래를 취소했습니다!");
                        }

                        break;
                }
            }
        }
        return false;
    }
}
