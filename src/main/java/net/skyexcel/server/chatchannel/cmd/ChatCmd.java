package net.skyexcel.server.chatchannel.cmd;

import net.skyexcel.server.chatchannel.data.ChatChannel;
import net.skyexcel.server.chatchannel.data.ChatData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChatCmd implements CommandExecutor, TabExecutor {

    private static final String cmd = "채팅채널";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            ChatData chatData = new ChatData(player);
            if (args.length > 0) {
                switch (args[0]) {
                    case "전체" -> chatData.setChannel(player, ChatChannel.GLOBAL);
                    case "지역" -> chatData.setChannel(player, ChatChannel.LOCAL);
                    case "섬" -> chatData.setChannel(player, ChatChannel.SKYBLOCK);
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
