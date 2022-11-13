package net.skyexcel.server.mileage.cmd;

import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.mileage.data.Data;

import net.skyexcel.server.mileage.data.shop.MileageShop;
import net.skyexcel.server.skyblock.util.Translate;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class MileageShopCmd {
    public void registerCmd() {
        Cmd cmd = new Cmd(SkyExcelNetworkMileageMain.plugin, "마일리지상점");
        Data data = new Data();
        cmd.label((action) -> {
            Player player = (Player) action.getSender();
        });
        cmd.action("생성", 0, (action) -> {
            Player player = (Player) action.getSender();
            String name = Translate.collapse(action.getArgs(), 1);
            MileageShop shop = new MileageShop(name);
            shop.create(player);
            data.add(player, shop);

        });
        cmd.action("열기", 0, (action) -> {
            Player player = (Player) action.getSender();
            String name = Translate.collapse(action.getArgs(), 1);
            MileageShop shop = new MileageShop(name);
            shop.open(player);
        });
        cmd.action("편집", 0, (action) -> {
            Player player = (Player) action.getSender();
            String name = Translate.collapse(action.getArgs(), 1);
            MileageShop shop = new MileageShop(name);
            shop.load(player);
            data.add(player, shop);
        });
        cmd.action("줄", 0, (action) -> {
            Player player = (Player) action.getSender();
            String name = Translate.collapse(action.getArgs(), 1);
            MileageShop shop = new MileageShop(name);
//            shop.delete(player);
        });
        cmd.action("제거", 0, (action) -> {
            Player player = (Player) action.getSender();
            String name = Translate.collapse(action.getArgs(), 1);
            MileageShop shop = new MileageShop(name);
//            shop.delete(player);
        });
    }
}
