package net.skyexcel.server.mileage.cmd;


import net.skyexcel.server.mileage.data.MileageShop;
import net.skyexcel.server.mileage.data.MileageShopData;
import net.skyexcel.server.skyblock.util.Translate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MileageShopCmd implements CommandExecutor {

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
                                MileageShop shop = new MileageShop(name);
                                shop.create(player);
                                MileageShopData.shop.put(player.getUniqueId(), shop);
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
                            MileageShop shop = new MileageShop(name);
                            shop.setType(MileageShop.Type.OPEN);
                            shop.load(player);
                            MileageShopData.shop.put(player.getUniqueId(), shop);
                        } else {
                            player.sendMessage("상점 이름을 입력해 주세요!");
                        }
                    }
                    case "편집" -> {
                        if (player.isOp()) {
                            if (args.length > 1) {

                                String name = Translate.collapse(args, 1);
                                MileageShop shop = new MileageShop(name);
                                shop.setType(MileageShop.Type.EDIT);
                                shop.load(player);

                                MileageShopData.shop.put(player.getUniqueId(), shop);

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
                                MileageShop shop = new MileageShop(name);
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
                            MileageShop cashShop = new MileageShop();
                            cashShop.list(player, index);
                        } else {
                            MileageShop cashShop = new MileageShop();
                            cashShop.list(player, 1);
                        }
                    }
                }
            }
        }


        return false;
    }

}
