package net.skyexcel.server.snowy.cmd;

import net.skyexcel.server.snowy.data.SnowToggleData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SnowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("해당 명령어는 플레이어만 사용가능합니다!");
            return false;
        }

        if (args.length == 0) {
             player.sendMessage(ChatColor.AQUA + "눈" + ChatColor.WHITE + "의 표시여부를 " + ChatColor.GOLD + "설정" + ChatColor.WHITE +"할 수 있습니다.",
                     ChatColor.GRAY + "/눈 " + ChatColor.GREEN + "<켜기|끄기>");
         } else if (args[0].equals("켜기")) {
            if (args.length > 1) {
                player.sendMessage("입력값이 너무 많습니다!");
                return false;
            }

            //SnowSettingUtils.setSnowVisibility(player, true);
            (new SnowToggleData(player)).getConfig().setBoolean("snow", true);

            player.sendMessage(ChatColor.AQUA + "눈" + ChatColor.WHITE + "을 켰습니다.");

            return true;
        } else if (args[0].equals("끄기")) {
            if (args.length > 1) {
                player.sendMessage("입력값이 너무 많습니다!");
                return false;
            }

            (new SnowToggleData(player)).getConfig().setBoolean("snow", false);

            player.sendMessage(ChatColor.AQUA + "눈" + ChatColor.WHITE + "을 껐습니다.");

            return true;
        }

        return false;
    }
}