package net.skyexcel.server.cosmetic.cmd;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.CosmeticType;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import net.skyexcel.server.cosmetic.util.GuiUtil;
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
            SkyExcelNetworkCosmeticMain.guiUtil.openGui(player, CosmeticType.BACK);
        } else if (args[0].equalsIgnoreCase("HAT")) {
            SkyExcelNetworkCosmeticMain.guiUtil.openGui(player, CosmeticType.HAT);
        } else if (args[0].equalsIgnoreCase("OFFHAND")) {
            SkyExcelNetworkCosmeticMain.guiUtil.openGui(player, CosmeticType.OFFHAND);
        }

        return true;
    }
}
