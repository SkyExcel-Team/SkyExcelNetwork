package net.skyexcel.server.discord.commands;

import net.skyexcel.server.discord.SkyExcelNetworkDiscordMain;
import net.skyexcel.server.discord.utils.VerifyUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class DiscordVerify {


    public static CommandExecutor VerifyCommand = (sender, command, label, args) -> {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "others.noConsoleCommand"));
            return false;
        }

        if (command.getName().equals("인증")) {
            if (args.length == 0) {
                if (SkyExcelNetworkDiscordMain.data.getString(player.getUniqueId().toString()).equals("")) {
                    // 인증을 안했을 때
                    List<String> messages = SkyExcelNetworkDiscordMain.config.getConfig().getStringList("messages.noVerifyMessage");
                    messages.forEach(str -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', str)));
                } else {
                    // 인증을 했을 때
                    List<String> messages = SkyExcelNetworkDiscordMain.config.getConfig().getStringList("messages.completeVerifyMessage");
                    messages.forEach(str -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', str)));
                }

                return true;
            } else if (args.length == 1) {
                if (args[0].equals("리로드")) {
                    if (!player.isOp()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SkyExcelNetworkDiscordMain.config.getString("others.opCommand")));
                        return false;
                    }


                    SkyExcelNetworkDiscordMain.bot.reloadBot(player);
                    SkyExcelNetworkDiscordMain.config.reloadConfig();
                    SkyExcelNetworkDiscordMain.botConfig.reloadConfig();
                    SkyExcelNetworkDiscordMain.data.reloadConfig();

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SkyExcelNetworkDiscordMain.config.getString("others.reloadConfig")));

                    return true;
                }
            }

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SkyExcelNetworkDiscordMain.config.getString("others.wrongCommand")));
        } else if (command.getName().equals("인증코드")) {
            if (args.length > 1) {
                player.sendMessage("입력값이 많습니다.");
                return false;
            }

            if (!SkyExcelNetworkDiscordMain.data.getString(player.getUniqueId().toString()).equals("")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SkyExcelNetworkDiscordMain.config.getConfig().getString("messages.already_verify_account")));

                return false;
            }

            String verifyCode;
            do {
                verifyCode = String.valueOf(Math.round(Math.random() * 1000000));
            } while(verifyCode.length() != 6);

            String finalVerifyCode = verifyCode;

            if (VerifyUtils.containsPlayer(player)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SkyExcelNetworkDiscordMain.config.getString("alreadyVerifyCode")));

                return false;
            }

            VerifyUtils.addVerifyCode(finalVerifyCode, player.getUniqueId());



            // 인증코드 발급 메세지
            List<String> messages = SkyExcelNetworkDiscordMain.config.getConfig().getStringList("messages.completeVerifyCode");
            messages.forEach(str -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', str)
                    .replace("%expired_seconds%", "" + SkyExcelNetworkDiscordMain.config.getInteger("others.verifyCodeExpiredSeconds"))
                    .replace("%verify_code%", finalVerifyCode)));


            Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkDiscordMain.plugin, new Runnable() {
                @Override
                public void run() {
                    if (VerifyUtils.containsCode(finalVerifyCode)) {
                        VerifyUtils.removeVerifyCode(finalVerifyCode);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SkyExcelNetworkDiscordMain.config.getString("messages.expiredVerifyCode")));
                    }
                }
            }, 20L * (SkyExcelNetworkDiscordMain.config.getInteger("others.verifyCodeExpiredSeconds")));
        }

        return true;
    };
}
