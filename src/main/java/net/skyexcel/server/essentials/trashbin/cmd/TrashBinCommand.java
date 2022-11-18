package net.skyexcel.server.essentials.trashbin.cmd;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.essentials.trashbin.util.TrashBinGUIUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrashBinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("해당 명령어는 플레이어만 사용할 수 있습니다!");
            return false;
        }

        if (args.length > 0) {
            p.sendMessage("입력값이 너무 많습니다!");
            return false;
        }

        TrashBinGUIUtils.openTrashBin(p, SkyExcelNetworkEssentialsMain.config.getInteger("trash_bin.lines"),
                SkyExcelNetworkEssentialsMain.config.getString("trash_bin.title").replace("%player%", p.getName()));

        return true;
    }
}
