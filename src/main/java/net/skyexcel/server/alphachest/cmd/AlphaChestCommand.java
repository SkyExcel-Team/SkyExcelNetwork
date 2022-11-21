package net.skyexcel.server.alphachest.cmd;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

public class AlphaChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("해당 명령어는 플레이어만 사용할 수 있습니다!");
            return false;
        }

        if (args.length < 1) {
            p.sendMessage("입력값이 부족합니다!");
            return false;
        }

        if (args[0].equals("열기")) {
            if (args.length > 3) {
                p.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 3) {
                p.sendMessage("입력값이 부족합니다!");
                return false;
            }

            if (Integer.parseInt(args[2]) < 1 || Integer.parseInt(args[2]) > 4) {
                p.sendMessage("번호는 1~4번까지입니다!");
                return false;
            }

            Config config = new Config("data/storages/" + p.getUniqueId());
            config.setPlugin(SkyExcelNetworkMain.getPlugin());
            config.loadDefualtConfig();

            GUI gui = new GUI(config);
            if (!config.isFileExist()) {
                gui.saveInventory(args[2], Bukkit.createInventory(null, 54, p.getName() + "님의 가상창고"));
            }

            p.openInventory(gui.getInventory(args[2]));

        } else if (args[0].equals("확인")) {
            if (args.length > 4) {
                p.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 4) {
                p.sendMessage("입력값이 부족합니다!");
                return false;
            }
        }
        return true;
    }
}
