package net.skyexcel.server.cosmetic.cmd;

import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CosmeticTestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (args[0].equalsIgnoreCase("BACK")) {
            new PlayerCosmeticData(player).setWearBack(Cosmetic.BACK.TEST);
        } else if (args[0].equalsIgnoreCase("HAT")) {
            new PlayerCosmeticData(player).setWearHat(Cosmetic.HAT.TEST);
        } else if (args[0].equalsIgnoreCase("OFFHAND")) {
            new PlayerCosmeticData(player).setWearOffhand(Cosmetic.OFFHAND.TEST);
        }

        return true;
    }
}
