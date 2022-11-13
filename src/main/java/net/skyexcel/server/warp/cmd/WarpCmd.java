package net.skyexcel.server.warp.cmd;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.warp.data.Warp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

import java.util.Arrays;

public class WarpCmd {

    public void register() {
        Cmd cmd = new Cmd(SkyExcelNetworkCashShopMain.plugin, "워프");
        cmd.label(action -> {
            Player player = (Player) action.getSender();

        });
        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                String name = collapse(action.getArgs(), 1);

                Warp warp = new Warp(name);
                warp.setLocation(player);

                player.sendMessage("架 " + "성공적으로 " + name + ChatColor.WHITE + " 의 워프를 생성하였습니다!");

            } else {
                player.sendMessage("强 " + "당신은 워프를 생성할 권한이 없습니다.");
            }
        });
        cmd.action("이동", 0, action -> {
            Player player = (Player) action.getSender();
            String name = collapse(action.getArgs(), 1);
            Warp warp = new Warp(name);
            Location location = warp.getLocation();
            player.teleport(location);
            player.sendMessage("家 " + name + "으로 이동하였습니다!");
        });
        cmd.action("삭제", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                String name = collapse(action.getArgs(), 1);


            } else {
                player.sendMessage("强 " + "당신은 워프를 제거할 권한이 없습니다.");
            }
        });
    }

    private String collapse(String[] args, int from) {
        return ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, from, args.length)));
    }
}
