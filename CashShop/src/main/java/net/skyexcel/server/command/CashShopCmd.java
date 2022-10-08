package net.skyexcel.server.command;

import net.skyexcel.server.SkyExcelNetwork;
import skyexcel.command.function.Cmd;
import skyexcel.command.tab.Tab;

public class CashShopCmd {

    public CashShopCmd() {
        Tab<String, String> cashTab = new Tab<>(SkyExcelNetwork.plugin, "캐시");

        cashTab.args("지급", "[플레이어]", "<Amount>");
        cashTab.args("모두지급", "< Amount > ");
        cashTab.args("빼기", "[플레이어]", "<Amount>");
        cashTab.args("설정", "[플레이어]", "<Amount>");
        cashTab.args("확인", "[플레이어]");
        cashTab.args("초기화", "[플레이어]");

        Tab<String, String> cashShopTab = new Tab<>(SkyExcelNetwork.plugin, "캐시상점");

        cashShopTab.args("생성", "[이름]");
        cashShopTab.args("제거", "[이름]");
        cashShopTab.args("편집", "[이름]");
        cashShopTab.args("<줄>", "[이름]");
        cashShopTab.args("제목", "[이름]", "[변경할 이름]");
        cashShopTab.args("리로드");

        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "캐시상점");

        cmd.action("생성", 0, action -> {
            String name = msgCollapse(action.getArgs(), 1);
            action.getSender().sendMessage(name);
        });
    }

    public String msgCollapse(String[] args, int index) {
        StringBuilder string = new StringBuilder();
        if (args.length > index) {
            for (int i = index; i < args.length; i++) {
                if (args.length != index) {
                    if (i == index) {
                        string.append(args[i]);
                    } else {
                        string.append(" " + args[i]);
                    }
                }
            }
        }
        return string.toString();
    }
}
