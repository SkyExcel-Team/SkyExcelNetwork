package net.skyexcel.server.regionafk.cmd;

import net.skyexcel.api.util.Translate;

import net.skyexcel.server.regionafk.data.AFK;
import net.skyexcel.server.regionafk.data.AFKData;
import net.skyexcel.server.regionafk.data.AFKShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AFKShopCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            Translate translate = new Translate();
            switch (args[0]) {
                case "생성" -> {
                    if (player.isOp()) {
                        if (args.length > 1) {
                            String name = translate.collapse(args, 1);
                            AFKShop shop = new AFKShop(name);
                            shop.create(player);

                            AFKData.shop.put(player.getUniqueId(), shop);
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
                        AFKShop shop = new AFKShop(name);
                        shop.setType(AFKShop.Type.OPEN);
                        shop.load(player);
                        AFKData.shop.put(player.getUniqueId(), shop);
                    } else {
                        player.sendMessage("상점 이름을 입력해 주세요!");
                    }
                }
                case "편집" -> {
                    if (player.isOp()) {
                        if (args.length > 1) {

                            String name = translate.collapse(args, 1);
                            AFKShop shop = new AFKShop(name);
                            shop.setType(AFKShop.Type.EDIT);
                            shop.load(player);

                            AFKData.shop.put(player.getUniqueId(), shop);

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
                            AFKShop shop = new AFKShop(name);
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
                        AFKShop AFKShop = new AFKShop();
                        AFKShop.list(player, index);
                    } else {
                        AFKShop AFKShop = new AFKShop();
                        AFKShop.list(player, 1);
                    }
                }
            }
        }


        return false;
    }

}
