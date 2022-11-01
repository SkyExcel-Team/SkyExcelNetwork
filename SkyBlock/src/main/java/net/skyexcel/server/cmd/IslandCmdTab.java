package net.skyexcel.server.cmd;

import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.command.tab.Tab;

import java.util.*;

public class IslandCmdTab implements TabCompleter {

    public static Tab tab;

    private final String[] args0 = {"도움말", "제거", "생성", "초대", "규칙", "금고", "디스코드", "방문객", "이름", "스폰변경",
            "업그레이드", "양도", "권한", "호퍼", "초기화", "옵션", "설정", "알바", "순위"};

    private final String[] args1 = {
            "[이름]", "[플레이어]"};

    public IslandCmdTab() {
//        Objects.requireNonNull(SkyExcelNetwork.plugin.getServer().getPluginCommand("섬")).setTabCompleter(this);
        tab = new Tab(SkyBlockCore.plugin, "섬");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        List<String> result = new ArrayList<>();


        if (sender instanceof Player) {
            Player player = (Player) sender;
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock skyBlock = new SkyBlock(playerData.getIsland());

            if (args.length == 1) {
                result.addAll(Arrays.asList(args0));
                if (playerData.hasIsland()) {
                    result.remove("생성");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("생성")) {
                    result = List.of("[이름]");
                } else if (List.of("초대","수락","거절","양도").contains(args[1])) {
                    for(Player online : Bukkit.getOnlinePlayers()){
                        result.add(online.getDisplayName());
                    }
                }
            }
        }

        return result;
    }
}
