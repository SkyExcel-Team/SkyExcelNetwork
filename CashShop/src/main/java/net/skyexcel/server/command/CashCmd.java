package net.skyexcel.server.command;

import net.skyexcel.server.SkyExcelNetwork;
import skyexcel.command.tab.Tab;

public class CashCmd {
    public CashCmd() {
        Tab<String, String> cashTab = new Tab<>(SkyExcelNetwork.plugin, "캐시");

        cashTab.args("지급", "[플레이어]", "<Amount>");
        cashTab.args("모두지급", "<Amount> ");
        cashTab.args("빼기", "[플레이어]", "<Amount>");
        cashTab.args("설정", "[플레이어]", "<Amount>");
        cashTab.args("확인", "[플레이어]");
        cashTab.args("초기화", "[플레이어]");

    }
}
