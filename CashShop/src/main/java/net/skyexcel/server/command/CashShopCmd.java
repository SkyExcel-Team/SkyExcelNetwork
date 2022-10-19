package net.skyexcel.server.command;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.CashShop;
import net.skyexcel.server.data.StringData;
import net.skyexcel.server.util.Translate;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;
import skyexcel.command.tab.Tab;
import skyexcel.data.file.Config;

public class CashShopCmd {

    public CashShopCmd() {
        Tab<String, String> cashShopTab = new Tab<>(SkyExcelNetwork.plugin, "캐시상점");
        Config config = new Config("cash/");

        config.setPlugin(SkyExcelNetwork.plugin);

        cashShopTab.args("생성", "[이름]");

        config.fileListName().forEach(names -> {
            cashShopTab.args("제거", names);

            cashShopTab.args("열기", names);
            cashShopTab.setOp(false);

            cashShopTab.args("편집", names);
            cashShopTab.args("줄", "<줄>", names);
            cashShopTab.args("제목", names, "[변경할 이름]");
        });

        cashShopTab.args("리로드");

    }

    public void registerCmd() {
        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "캐시상점");
        cmd.label(action -> {
            Player player = (Player) action.getSender();

            for (String line : StringData.main()) {
                player.sendMessage(line);
            }

        });
        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.create(player);
        });

        cmd.action("열기", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);

            shop.open(player);
        });
        cmd.action("편집", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.delete(player);
        });

        cmd.action("줄", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.delete(player);
        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.delete(player);
        });
    }
}
