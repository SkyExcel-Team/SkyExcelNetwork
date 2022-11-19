package net.skyexcel.server.cashshop.cmd;

import net.skyexcel.api.util.Translate;
import net.skyexcel.server.cashshop.data.CashShop;


import net.skyexcel.server.cashshop.data.CashShopData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CashShopCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            Translate translate = new Translate();
            switch (args[0]) {
                case "생성" -> {
                    if (player.isOp()) {
                        if (args.length > 1) {
                            String name = translate.collapse(args, 1);
                            CashShop shop = new CashShop(name);
                            shop.create(player);

                            CashShopData.shop.put(player.getUniqueId(), shop);
                        } else {
                            player.sendMessage("상점 이름을 입력해 주세요!");
                        }
                    } else {
                        player.sendMessage("당신은 권한이 없습니다.");
                    }
                }
                case "열기" -> {
                    if (args.length > 1) {

                        String name = translate.collapse(args, 1);
                        CashShop shop = new CashShop(name);
                        shop.setType(CashShop.Type.OPEN);
                        shop.load(player);
                        CashShopData.shop.put(player.getUniqueId(), shop);
                    } else {
                        player.sendMessage("상점 이름을 입력해 주세요!");
                    }
                }
                case "편집" -> {
                    if (player.isOp()) {
                        if (args.length > 1) {

                            String name = translate.collapse(args, 1);
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
                }
                case "제거" -> {
                    if (args.length > 1) {
                        if (player.isOp()) {
                            String name = translate.collapse(args, 1);
                            CashShop shop = new CashShop(name);
                        } else {
                            player.sendMessage("당신은 권한이 없습니다.");
                        }


                    } else {
                        player.sendMessage("상점 이름을 입력해 주세요!");
                    }
                }
                case "목록" -> {
                    if (args.length > 1) {
                        int index = Integer.parseInt(args[1]);
                        CashShop cashShop = new CashShop();
                        cashShop.list(player, index);
                    } else {
                        CashShop cashShop = new CashShop();
                        cashShop.list(player, 1);
                    }
                }
            }
        }


        return false;
    }

}
