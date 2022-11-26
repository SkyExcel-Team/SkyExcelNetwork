package net.skyexcel.server.giftbox.cmd;

import net.skyexcel.server.giftbox.data.GiftBox;
import net.skyexcel.server.giftbox.data.GiftBoxData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiftBoxCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
                if ("보내기".equalsIgnoreCase(args[0])) {

                    ItemStack itemStack = player.getInventory().getItemInMainHand();
                    if (!itemStack.getType().equals(Material.AIR)) {
                        if (args.length > 1) {

                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            GiftBox giftBox = new GiftBox(target);

                            giftBox.sendItemStack(itemStack);
                        }
                    }
                } else if ("보기".equalsIgnoreCase(args[0])) {
                    if (args.length > 1) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                        GiftBox giftBox = new GiftBox(target);
                        giftBox.open(player);
                        GiftBoxData.giftBoxHashMap.put(player.getUniqueId(), giftBox);
                    }
                }
            } else {
                GiftBox giftBox = new GiftBox(player);
                giftBox.open(player);
                GiftBoxData.giftBoxHashMap.put(player.getUniqueId(), giftBox);
            }
        }
        return false;
    }
}
