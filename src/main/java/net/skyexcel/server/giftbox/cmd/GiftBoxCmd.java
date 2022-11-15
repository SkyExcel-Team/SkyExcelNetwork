package net.skyexcel.server.giftbox.cmd;

import net.skyexcel.server.giftbox.data.GiftBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiftBoxCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            GiftBox giftBox = new GiftBox(player);

            if (args.length > 1) {
                if ("test".equals(args[0])) {
                    giftBox.save();
                } else  if ("open".equals(args[0])) {

                    giftBox.next(player);
                }
            }

        }


        return false;
    }
}
