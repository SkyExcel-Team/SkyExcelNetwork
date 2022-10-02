package net.skyexcel.server.commands;

import net.skyexcel.server.inventory.TrashManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TrashCommand {

    public static CommandExecutor TRASH = (sender, command, label, args) -> {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                TrashManager.Trash();
                player.openInventory(TrashManager.trash);
                return true;
            }
        }
        return true;
    };

}
