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
            sender.sendMessage("强 §c해당 명령어는 플레이어만 사용가능합니다!");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("§8■ §7══════°• §8[ §b눈 §f사용방법 §8] §7•°══════ §8■");
            player.sendMessage("§6> §f家 §6스폰§f의 §b눈§f을 켜거나 끕니다.");
            player.sendMessage("");
            player.sendMessage("§6> §f/눈 켜기 §8- §f눈을 켭니다.");
            player.sendMessage("§6> §f/눈 끄기 §8- §f눈을 끕니다.");
            player.sendMessage("§8■ §7══════°• ═════════════════════ §7•°══════ §8■");

         } else if (args[0].equals("켜기")) {
            if (args.length > 1) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            //SnowSettingUtils.setSnowVisibility(player, true);
            (new SnowToggleData(player)).getConfig().setBoolean("snow", true);

            player.sendMessage("架 §b눈§f을 켰습니다!");

            return true;
        } else if (args[0].equals("끄기")) {
            if (args.length > 1) {
                player.sendMessage("强 §c잘못 된 명령어 입니다!");
                return false;
            }

            (new SnowToggleData(player)).getConfig().setBoolean("snow", false);

            player.sendMessage("架 §b눈§f을 껐습니다!");

            return true;
        }

        return false;
    }
}