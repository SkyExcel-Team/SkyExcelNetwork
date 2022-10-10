package net.skyexcel.server.island;

import net.skyexcel.server.SkyExcelNetwork;
import skyexcel.command.tab.Tab;

public class IslandCmdTab {

    public static Tab<String, String> tab;

    public IslandCmdTab() {

        tab = new Tab<>(SkyExcelNetwork.plugin, "섬");

        tab.args("도움말");
        tab.args("생성", "[이름]");
        tab.args("초대", "[플레이어]");

        tab.args("규칙", "추가", "[이름]");
        tab.args("규칙", "제거", "[번호]");

        tab.args("금고", "입금", "<Amount>");
        tab.args("금고", "출금", "<Amount>");

        tab.args("디스코드", "설정", "[링크]");
        tab.args("디스코드", "삭제");

        tab.args("방문객");
        tab.args("이름", "[이름]");
        tab.args("스폰변경");
        tab.args("업그레이드");
        tab.args("디스코드", "삭제");
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

    public static void addArg(String previous, String... next) {
        tab.args(previous, next);
    }
}
