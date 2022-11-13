package net.skyexcel.server.cashshop.cmd;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
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
            String[] args = action.getArgs();
            if (player.isOp()) {
                if (args.length > 1) {
                    String name = Translate.collapse(action.getArgs(), 1);
                    CashShop shop = new CashShop(name);
                    shop.create(player);
                    CashShopData.shop.put(player.getUniqueId(), shop);
                } else {
                    player.sendMessage("상점 이름을 입력해 주세요!");
                }
            } else {
                player.sendMessage("당신은 권한이 없습니다.");
            }

        });

        cmd.action("열기", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            if (args.length > 1) {
                String name = Translate.collapse(action.getArgs(), 1);
                CashShop shop = new CashShop(name);
                shop.setType(CashShop.Type.OPEN);
                shop.load(player);
                CashShopData.shop.put(player.getUniqueId(), shop);
            } else {
                player.sendMessage("상점 이름을 입력해 주세요!");
            }

        });
        cmd.action("편집", 0, action -> {
            Player player = (Player) action.getSender();

            String[] args = action.getArgs();
            if (player.isOp()) {
                if (args.length > 1) {

                    String name = Translate.collapse(action.getArgs(), 1);
                    CashShop shop = new CashShop(name);
                    shop.setType(CashShop.Type.EDIT);
                    shop.load(player);

                    CashShopData.shop.put(player.getUniqueId(), shop);

                } else {
                    player.sendMessage("상점 이름을 입력해 주세요!");
                }
            } else {
                player.sendMessage("당신은 권한이 없습니다.");
            }
        });

        cmd.action("줄", 0, action -> {
            Player player = (Player) action.getSender();

            String[] args = action.getArgs();
            if (player.isOp()) {
                if (args.length > 1) {

                    String name = Translate.collapse(action.getArgs(), 1);
                    CashShop shop = new CashShop(name);

                } else {
                    player.sendMessage("상점 이름을 입력해 주세요!");
                }
            } else {
                player.sendMessage("당신은 권한이 없습니다.");
            }


//            shop.delete(player);
        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();
            if (args.length > 1) {
                if (player.isOp()) {
                    String name = Translate.collapse(action.getArgs(), 1);
                    CashShop shop = new CashShop(name);
                } else {
                    player.sendMessage("당신은 권한이 없습니다.");
                }


            } else {
                player.sendMessage("상점 이름을 입력해 주세요!");
            }

//            shop.delete(player);
        });
    }
}
