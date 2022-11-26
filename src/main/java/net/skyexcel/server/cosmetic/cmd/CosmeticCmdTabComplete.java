package net.skyexcel.server.cosmetic.cmd;

import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CosmeticCmdTabComplete implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return Collections.emptyList();

        if (args.length == 1) {
            if (player.isOp())
                return List.of("도움말", "목록", "장착", "제거", "주기", "뺏기");
            else
                return List.of("도움말", "목록", "장착", "제거");
        } else if (args.length == 2) {
            if (List.of("도움말", "help", "?").contains(args[0].toLowerCase()))
                return Collections.emptyList();
            else if (List.of("목록", "list", "장착", "wear", "제거", "remove", "주기", "give", "뺏기", "take").contains(args[0].toLowerCase()))
                return List.of("등", "모자", "왼손");
        } else if (args.length == 3) {
            if (List.of("목록", "list", "장착", "wear", "제거", "remove", "주기", "give", "뺏기", "take").contains(args[0].toLowerCase())) {
                if (List.of("장착", "wear").contains(args[0].toLowerCase())) {
                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        List<String> cosmeticNames = new ArrayList<>();

                        if (player.isOp())
                            Arrays.asList(Cosmetic.BACK.values()).forEach(cosmetic -> cosmeticNames.add(cosmetic.name()));
                        else
                            new PlayerCosmeticData(player).getBackCosmetics().forEach(cosmetic -> cosmeticNames.add(cosmetic.name()));

                        cosmeticNames.remove("NONE");

                        return cosmeticNames;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        List<String> cosmeticNames = new ArrayList<>();

                        if (player.isOp())
                            Arrays.asList(Cosmetic.HAT.values()).forEach(cosmetic -> cosmeticNames.add(cosmetic.name()));
                        else
                            new PlayerCosmeticData(player).getHatCosmetics().forEach(cosmetic -> cosmeticNames.add(cosmetic.name()));

                        cosmeticNames.remove("NONE");

                        return cosmeticNames;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        List<String> cosmeticNames = new ArrayList<>();

                        if (player.isOp())
                            Arrays.asList(Cosmetic.OFFHAND.values()).forEach(cosmetic -> cosmeticNames.add(cosmetic.name()));
                        else
                            new PlayerCosmeticData(player).getOffhandCosmetics().forEach(cosmetic -> cosmeticNames.add(cosmetic.name()));

                        cosmeticNames.remove("NONE");

                        return cosmeticNames;
                    }
                } else if (player.isOp() && List.of("목록", "list", "제거", "remove").contains(args[0].toLowerCase()))
                    return null;
            }
        } else if (args.length == 4) {
            if (player.isOp() && List.of("장착", "wear", "주기", "give", "뺏기", "take").contains(args[0].toLowerCase()))
                return null;
        }

        return Collections.emptyList();
    }
}
