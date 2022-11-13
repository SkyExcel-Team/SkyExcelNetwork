package net.skyexcel.server.chatchannel.cmd;

import net.skyexcel.server.chatchannel.SkyExcelNetworkChatChannelMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ChatCmd implements CommandExecutor, TabExecutor {

    private static final String cmd = "채팅채널";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                switch (args[0]) {
                    case "전체":

                        player.sendMessage("전체 채팅 채널에 입장 하셨습니다!");
                        break;

                    case "지역":
                        player.sendMessage("지역 채팅 채널에 입장 하셨습니다!");
                        break;
                    case "섬":
                        player.sendMessage("섬 채팅 채널에 입장 하셨습니다!");
                        break;
                }
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of("전체", "지역", "섬");
    }


}
