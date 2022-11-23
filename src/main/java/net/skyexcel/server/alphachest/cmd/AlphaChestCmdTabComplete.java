package net.skyexcel.server.alphachest.cmd;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AlphaChestCmdTabComplete implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p))
            return Collections.emptyList();

        if (args.length == 1)
            return p.isOp() ? List.of("열기", "확인") : List.of("열기");
        else if (args.length == 2)
            return args[0].equals("열기") ? List.of("일반", "후원") : (args[0].equals("확인") && p.isOp() ? getPlayers() : Collections.emptyList());
        else if (args.length == 3)
            return args[0].equals("열기") ? List.of("1", "2", "3", "4") : (args[0].equals("확인") && p.isOp() ? List.of("일반", "후원") : Collections.emptyList());
        else if (args.length == 4)
            return args[0].equals("확인") && p.isOp() ? List.of("1", "2", "3", "4") : Collections.emptyList();
        else
            return Collections.emptyList();
    }

    private List<String> getPlayers() {
        Config config = new Config("data/storages/");
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        config.loadDefualtConfig();

        List<String> players = new ArrayList<>();
        for (String fileName : config.fileListName()) {
            if (fileName != null) {
                Config configFile = new Config("data/storages/" + fileName);
                configFile.setPlugin(SkyExcelNetworkMain.getPlugin());
                players.add(Bukkit.getPlayer(fileName).getName());
            }
        }

        return players;
    }
}
