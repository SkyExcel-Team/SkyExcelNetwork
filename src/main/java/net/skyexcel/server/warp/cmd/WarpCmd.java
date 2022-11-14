package net.skyexcel.server.warp.cmd;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.warp.data.Warp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.command.function.Cmd;

import java.util.Arrays;
import java.util.List;

public class WarpCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length == 1) {
                if (!List.of("생성", "삭제").contains(args[0])) {
                    String name = args[0];
                    Warp warp = new Warp(name);
                    Location location = warp.getLocation();
                    player.teleport(location);
                    player.sendMessage("家 " + name + "으로 이동하였습니다!");
                }
            } else if (args.length == 2) {
                if (!List.of("생성", "삭제").contains(args[0])) {
                    String name = args[0];
                    Player target = Bukkit.getPlayer(args[1]);

                    Warp warp = new Warp(name);
                    Location location = warp.getLocation();
                    target.teleport(location);
                    player.sendMessage("家 " + target.getDisplayName() + " 님을 " + name + "으로 이동하였습니다!");
                }
            }

            if (args.length > 1) {
                switch (args[0]) {
                    case "생성" -> {
                        if (player.isOp()) {
                            String name = collapse(args, 1);

                            Warp warp = new Warp(name);
                            warp.setLocation(player);

                            player.sendMessage("架 " + "성공적으로 " + name + ChatColor.WHITE + " 의 워프를 생성하였습니다!");

                        } else {
                            player.sendMessage("强 " + "당신은 워프를 생성할 권한이 없습니다.");
                        }
                    }

                    case "삭제" -> {
                        if (player.isOp()) {
                            String name = collapse(args, 1);

                            Warp warp = new Warp(name);
                            warp.deleteLocation();
                            player.sendMessage("架 " + "성공적으로 " + name + ChatColor.WHITE + " 의 워프를 삭제하였습니다!");
                        } else {
                            player.sendMessage("强 " + "당신은 워프를 제거할 권한이 없습니다.");
                        }
                    }
                }
            }
        }


        return false;
    }

    private String collapse(String[] args, int from) {
        return ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, from, args.length)));
    }
}
