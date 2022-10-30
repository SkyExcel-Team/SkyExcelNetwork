package net.skyexcel.server.island;

import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.player.SkyBlockRequest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class OtherCmd {
    public OtherCmd() {
        Cmd cmd = new Cmd(SkyBlockCore.plugin, "other");
        cmd.label(action -> {
        });

        SkyBlockRequest request = new SkyBlockRequest();

        cmd.action("test", 0, action -> {
            Player player = (Player) action.getSender();
            Player target = Bukkit.getPlayer(action.getArgs()[1]);
            if (SkyBlockRequest.send(request, player, target)) {
                player.sendMessage("테스트 요청 보냄!");
                target.sendMessage("테스트 요청 받음");
            }
        });

        cmd.action("accept", 0, action -> {
            Player player = (Player) action.getSender();
            Player target = Bukkit.getPlayer(action.getArgs()[1]);
            if (SkyBlockRequest.accept(request, player, target)) {

                player.sendMessage("초대 수락을 하였습니다!");
            } else {
                player.sendMessage("초대 수락에 실패 하였습니다! ");
            }
        });


    }
}
