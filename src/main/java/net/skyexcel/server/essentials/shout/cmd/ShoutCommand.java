package net.skyexcel.server.essentials.shout.cmd;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.seconomy.data.SEConomy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("해당 명령어는 플레이어만 사용할 수 있습니다!");
            return false;
        }

        if (!p.isOp()) {
            if (SkyExcelNetworkEssentialsMain.chatCoolDown.isCoolDownNow(p)) {
                p.sendMessage("채팅 치지마 ^.^");
                return false;
            }
        }

        if (args.length < 1) {
            p.sendMessage("입력값이 적습니다!");
            return false;
        }

        if (!p.isOp()) {
            SEConomy seConomy = new SEConomy(p);
            if (seConomy.getLong() < SkyExcelNetworkEssentialsMain.config.getInteger("shout.money")) {
                p.sendMessage("잔액이 부족합니다!");
                return false;
            }

            seConomy.withdraw(SkyExcelNetworkEssentialsMain.config.getInteger("shout.money"));
        }

        String message = "§l>§r §c[§6확성기§c] §f" + p.getDisplayName() + " : " + String.join(" ", args);
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
            player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        });

        SkyExcelNetworkEssentialsMain.chatCoolDown.coolDown(p, SkyExcelNetworkEssentialsMain.config.getConfig().getLong("shout.coolTick"));

        return true;
    }
}
