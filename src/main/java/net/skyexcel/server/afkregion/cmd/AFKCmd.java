package net.skyexcel.server.afkregion.cmd;


import net.skyexcel.api.util.Translate;
import net.skyexcel.server.afkregion.data.AFK;
import net.skyexcel.server.afkregion.data.AFKArea;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AFKCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player player) {
            if (args.length > 0) {
                Translate translate = new Translate();
                switch (args[0]) {
                    case "포인트":
                        if (args.length > 1) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            AFK afk = new AFK(target);

                            if (afk.getLong() != -1) {
                                player.sendMessage(target.getName() + " 님의 잠수 포인트는 " + afk.getLong() + " 입니다.");
                            } else {
                                player.sendMessage("해당 플레이어의 잠수포인트는 존재하지 않습니다.");
                            }
                        } else {
                            player.sendMessage("플레이어를 입력해 주세요!");
                        }

                        break;
                    case "구역":
                        if (args.length > 2) {
                            switch (args[1]) {
                                case "생성" -> {
                                    AFKArea afkArea = new AFKArea(player, translate.collapse(args, 2));
                                    afkArea.create();
                                }
                                case "제거" -> {

                                }
                            }
                        }
                        break;
                }
            }

        }
        return false;
    }
}
