package net.skyexcel.server.island;

import net.skyexcel.server.SkyExcelNetwork;
import skyexcel.command.tab.Tab;

public class IslandAdminCmdTab {

    public static Tab<String> tab;

    public IslandAdminCmdTab() {

        tab = new Tab<>(SkyExcelNetwork.plugin, "섬어드민");

        tab.args("[자신의 섬 이름]", "금고", "입금", "Amount");
        tab.args("[자신의 섬 이름]", "금고", "출금", "Amount");
        tab.args("[자신의 섬 이름]", "금고", "잠금");

        tab.args("[자신의 섬 이름]", "밴블록");
        tab.args("[자신의 섬 이름]", "pvp");

        tab.args("[자신의 섬 이름]", "알바", "추가", "[플레이어]");
        tab.args("[자신의 섬 이름]", "알바", "제거", "[플레이어]");

        tab.args("[자신의 섬 이름]", "이름변경", "변경할 이름");

        tab.args("[자신의 섬 이름]", "레벨");

        tab.args("[자신의 섬 이름]", "규칙", "추가");
        tab.args("[자신의 섬 이름]", "규칙", "제거");
        tab.args("[자신의 섬 이름]", "규칙", "보기");

    }

}
