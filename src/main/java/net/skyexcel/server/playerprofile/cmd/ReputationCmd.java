package net.skyexcel.server.playerprofile.cmd;

import net.skyexcel.server.playerprofile.data.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReputationCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 1) {

                switch (args[0]) {
                    case "올리기" -> {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                        PlayerProfile playerProfile = new PlayerProfile(target);
                        playerProfile.add(1);

                        if (target.isOnline()) {
                            target.getPlayer().sendMessage(player.getDisplayName() + " 님이 인기도를 올렸습니다.");
                        }

                        player.sendMessage(target.getName() + "님의 인기도를 올렸습니다");
                    }
                    case "내리기" -> {

                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                        PlayerProfile playerProfile = new PlayerProfile(target);
                        playerProfile.sub(0.5);

                        if (target.isOnline()) {
                            target.getPlayer().sendMessage(player.getDisplayName() + " 님이 인기도를 내렸습니다.");
                        }

                        player.sendMessage(target.getName() + "님의 인기도를 내렸습니다");
                    }
                    case "보기" -> {
                        try {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                            PlayerProfile playerProfile = new PlayerProfile(target);

                            player.sendMessage(target.getName() + "님의 인기도는 " + playerProfile.getDouble() + " 입니다.");
                        } catch (NumberFormatException e) {
                            player.sendMessage("정수를 입력해 주세요!");
                        }

                    }
                    case "설정" -> {
                        if (player.isOp()) {

                            if (args.length > 2) {
                                try {
                                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                    double amount = Double.parseDouble(args[2]);
                                    PlayerProfile playerProfile = new PlayerProfile(target);
                                    playerProfile.setAmount(amount);
                                    if (target.isOnline()) {
                                        target.getPlayer().sendMessage(player.getDisplayName() + " 님이 인기도를 " + amount + " 값으로 설정하였습니다");
                                    }

                                    player.sendMessage(target.getName() + "님의 인기도를 " + amount + " 값으로 설정하였습니다");

                                } catch (NumberFormatException e) {
                                    player.sendMessage("정수를 입력해 주세요!");
                                }
                            }
                        }
                    }
                    case "초기화" -> {
                        if (player.isOp()) {
                            if (args.length > 2) {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                PlayerProfile playerProfile = new PlayerProfile(target);
                                playerProfile.setAmount(0);
                            }
                        }
                    }

                }
                if ("열기".equalsIgnoreCase(args[0])) {
                    if (args.length > 2) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                        PlayerProfile playerProfile = new PlayerProfile(target);
                    }
                }

            }
        }

        return false;
    }
}
