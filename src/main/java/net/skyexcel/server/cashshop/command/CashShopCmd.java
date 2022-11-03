package net.skyexcel.server.cashshop.command;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.data.CashShop;
import net.skyexcel.server.data.StringData;
import net.skyexcel.server.util.Translate;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class CashShopCmd {

    public void registerCmd() {
        Cmd cmd = new Cmd(SkyExcelNetworkCashShopMain.plugin, "캐시상점");
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
