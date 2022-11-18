package net.skyexcel.server.essentials.whisper.cmd;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class WhisperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("해당 명령어는 플레이어만 사용할 수 있습니다!");
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage("메세지를 받을 플레이어를 입력해주세요.");
            return false;
        } else if (args.length < 2) {
            sender.sendMessage("보낼 메세지의 내용을 입력해주세요.");
            return false;
        }

        Player t = Bukkit.getPlayer(args[0]);
        if (t == null) {
            sender.sendMessage("존재하지 않는 플레이어입니다.");
            return false;
        }
        if (!t.hasPlayedBefore()) {
            sender.sendMessage("해당 플레이어는 이 서버에 접속한 적이 없습니다.");
            return false;
        }
        if (!t.isOnline()) {
            sender.sendMessage("해당 플레이어에게는 메세지를 보낼 수 없습니다.");
            return false;
        }

        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        String finalMessage = SkyExcelNetworkEssentialsMain.config.getString("whisper.message")
                .replace("%from%", p.getName())
                .replace("%to%", t.getName())
                .replace("%message%", message);

        t.sendMessage(finalMessage);

        return true;
    }
}
