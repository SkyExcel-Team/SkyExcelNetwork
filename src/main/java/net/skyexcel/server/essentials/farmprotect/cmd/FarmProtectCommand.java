package net.skyexcel.server.essentials.farmprotect.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;

public class FarmProtectCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("해당 명령어는 플레이어만 사용할 수 있습니다!");
            return false;
        }

        if (args.length > 1) {
            p.sendMessage("입력값이 많습니다!");
            return false;
        } else if (args.length < 1) {
            p.sendMessage("입력값이 적습니다!");
            return false;
        }

        Config data = new Config("data/" + p.getUniqueId());

        if (args[0].equals("켜기")) {
            data.setBoolean("farmprotect", true);
            p.sendMessage("밭보호를 켰습니다. | 이제 당신은 밭을 파괴하지 않습니다.");
        } else if (args[0].equals("끄기")) {
            data.setBoolean("farmprotect", false);
            p.sendMessage("밭보호를 껐습니다. | 이제 당신은 밭을 파괴합니다.");
        } else {
            p.sendMessage("알 수 없는 명령어입니다.");
            return false;
        }

        return true;
    }
}
