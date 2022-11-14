package net.skyexcel.server.seconomy.cmd;


import net.skyexcel.server.seconomy.data.SEconomyShop;
import net.skyexcel.server.seconomy.data.SEConomyShopData;
import net.skyexcel.server.skyblock.util.Translate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EConomyShopCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 0) {

            } else {
                switch (args[0]) {
                    case "생성" -> {
                        if (player.isOp()) {
                            if (args.length > 1) {
                                String name = Translate.collapse(args, 1);
                                SEconomyShop shop = new SEconomyShop(name);
                                shop.create(player);
                                SEConomyShopData.shop.put(player.getUniqueId(), shop);
                            } else {
                                player.sendMessage("상점 이름을 입력해 주세요!");
                            }
                        } else {
                            player.sendMessage("당신은 권한이 없습니다.");
                        }
                    }
                    case "열기" -> {
                        if (args.length > 1) {
                            String name = Translate.collapse(args, 1);
                            SEconomyShop shop = new SEconomyShop(name);
                            shop.setType(SEconomyShop.Type.OPEN);
                            shop.load(player);
                            SEConomyShopData.shop.put(player.getUniqueId(), shop);
                        } else {
                            player.sendMessage("상점 이름을 입력해 주세요!");
                        }
                    }
                    case "편집" -> {
                        if (player.isOp()) {
                            if (args.length > 1) {

                                String name = Translate.collapse(args, 1);
                                SEconomyShop shop = new SEconomyShop(name);
                                shop.setType(SEconomyShop.Type.EDIT);
                                shop.load(player);

                                SEConomyShopData.shop.put(player.getUniqueId(), shop);

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
                                String name = Translate.collapse(args, 1);
                                SEconomyShop shop = new SEconomyShop(name);
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
                            SEconomyShop sEconomyShop = new SEconomyShop();
                            sEconomyShop.list(player, index);
                        } else {
                            SEconomyShop sEconomyShop = new SEconomyShop();
                            sEconomyShop.list(player, 1);
                        }
                    }
                }
            }
        }


        return false;
    }

}
