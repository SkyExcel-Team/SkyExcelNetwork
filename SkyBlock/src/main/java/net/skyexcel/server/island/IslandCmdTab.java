package net.skyexcel.server.island;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.SkyBlockData;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
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
        tab = new Tab(SkyExcelNetwork.plugin, "섬");

        tab.args("도움말");
        tab.args("생성", "[이름]");


        tab.args("초대", "[플레이어]");


        tab.args("규칙", "추가", "[이름]");
        tab.args("규칙", "제거", "[번호]");

        tab.args("금고", "입금", "<Amount>");
        tab.args("금고", "잠금");
        tab.args("금고", "출금", "<Amount>");

        tab.args("디스코드", "설정", "[링크]");
        tab.args("디스코드", "삭제");

        tab.args("방문객");

        tab.args("이름", "[이름]");

        tab.args("스폰변경");

        tab.args("업그레이드");


        tab.args("양도", "[플레이어]");
        tab.args("권한");
        tab.args("호퍼");
        tab.args("초기화");
        tab.args("pvp");
        tab.args("시간", "[시간]");
        tab.args("홈");
        tab.args("밴블록");
        tab.args("알바", "추가", "[이름]", "[돈]");
        tab.args("알바", "제거", "[이름]");
        tab.args("순위", "[페이지]");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();


        if (sender instanceof Player) {
            Player player = (Player) sender;
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock skyBlock = new SkyBlock(playerData.getIsland());

            if (args.length == 1) {
                completions.addAll(Arrays.asList(args0));
                if (playerData.hasIsland()) {
                    completions.remove("생성");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase(args0[0])) {

                }

                completions.addAll(Arrays.asList(args0));
            }
        }

        Collections.sort(completions);
        return completions;
    }
}
