package net.skyexcel.server.cashshop.command;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.data.Cash;
import net.skyexcel.server.cashshop.data.CashShop;


import net.skyexcel.server.cashshop.data.CashShopData;
import net.skyexcel.server.skyblock.util.Translate;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class CashShopCmd {


    public void registerCmd() {
        CashShopData cashShopData = new CashShopData();

        Cmd cmd = new Cmd(SkyExcelNetworkCashShopMain.plugin, "캐시상점");
        cmd.label(action -> {
            Player player = (Player) action.getSender();

        });
        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.collapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.create(player);
            CashShopData.shop.put(player.getUniqueId(), shop);

        });

        cmd.action("열기", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.collapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.setType(CashShop.Type.OPEN);
            shop.load(player);
            CashShopData.shop.put(player.getUniqueId(), shop);
        });
        cmd.action("편집", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.collapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
            shop.setType(CashShop.Type.EDIT);
            shop.load(player);

            CashShopData.shop.put(player.getUniqueId(), shop);
        });

        cmd.action("줄", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.collapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
//            shop.delete(player);
        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.collapse(action.getArgs(), 1);
            CashShop shop = new CashShop(name);
//            shop.delete(player);
        });
    }
}
