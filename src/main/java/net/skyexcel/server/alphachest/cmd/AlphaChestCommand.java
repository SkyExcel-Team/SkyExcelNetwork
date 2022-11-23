package net.skyexcel.server.alphachest.cmd;


import net.skyexcel.server.alphachest.struct.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AlphaChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
                if ("열기".equalsIgnoreCase(args[0])) {
                    if (args.length > 1) {
                        if ("일반".equalsIgnoreCase(args[1])) {
                            if (args.length > 2) {
                                int index;

                                try {
                                    index = Integer.parseInt(args[2]);
                                } catch (Exception ex) {
                                    player.sendMessage("창고 번호는 1~4 사이의 숫자여야합니다!");
                                    return false;
                                }

                                if (index >= 1 && index <= 4) {
                                    Storage storage = new Storage(player, index);
                                    storage.open(player);
                                    StorageData.storageHashMap.put(player.getUniqueId(), storage);
                                }
                            }
                        } else if ("아이템".equalsIgnoreCase(args[1])) {

                            StorageItem storageItem = new StorageItem();
                            player.getInventory().addItem(storageItem.addItem());

                        } else if ("후원아이템".equalsIgnoreCase(args[1])) {

                            CashStorageItem storageItem = new CashStorageItem();
                            player.getInventory().addItem(storageItem.addItem());

                        } else if ("후원".equalsIgnoreCase(args[1])) {
                            if (args.length > 2) {
                                int index = Integer.parseInt(args[2]);
                                if (index >= 1 && index <= 4) {
                                    CashStorage storage = new CashStorage(player, index);
                                    storage.open(player);
                                    StorageData.cashStorageHashMap.put(player.getUniqueId(), storage);
                                }
                            }
                        }
                    }
                } else if ("확인".equalsIgnoreCase(args[0])) {

                }
            }
        }
        return true;
    }
}
