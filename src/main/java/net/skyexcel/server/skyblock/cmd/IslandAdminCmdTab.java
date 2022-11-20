package net.skyexcel.server.skyblock.cmd;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class IslandAdminCmdTab implements TabCompleter {
    public IslandAdminCmdTab() {
        Bukkit.getServer().getPluginCommand("섬어드민").setTabCompleter(this);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("island.admin*")){

                List<String> result = new ArrayList<>();
                Config config = new Config("SkyBlock/");
                config.setPlugin(SkyExcelNetworkMain.getPlugin());

                if (args.length == 1) {
                    result.add("랭킹갱신");
                    result.addAll(config.fileListName());
                } else if (args.length == 2) {
                    result = List.of("금고", "디스코드", "옵션", "알바", "레벨");
                } else if (args.length == 3) {
                    if (args[1].equalsIgnoreCase("금고")) {
                        result = List.of("입금", "출금", "설정", "잠금");
                    } else if (args[1].equalsIgnoreCase("레벨")) {
                        result = List.of("인원", "크기", "호퍼", "아머스탠드", "액자", "포탈", "농작물");
                    } else if (args[1].equalsIgnoreCase("규칙")) {
                        result = List.of("추가", "제거", "보기");
                    }else if (args[1].equalsIgnoreCase("디스코드")) {
                        result = List.of("보기");
                    }
                } else if (args.length == 4) {
                    if (List.of("입금", "출금", "설정").contains(args[2])) {
                        result = List.of("amount");
                    }
                }
                return result;
            }
        }

        return null;
    }
}
