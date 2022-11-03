package net.skyexcel.server.seconomy.cmd;

import net.skyexcel.server.seconomy.SkyExcelNetworkSEConomyMain;
import net.skyexcel.server.seconomy.data.Data;
import net.skyexcel.server.seconomy.data.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.command.function.Cmd;
import skyexcel.data.file.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopCommand implements TabCompleter {

    public ShopCommand() {

        Bukkit.getPluginCommand("상점").setTabCompleter(this);
        Cmd cmd = new Cmd(SkyExcelNetworkSEConomyMain.plugin, "상점");

        cmd.label(action -> {
            Player player = (Player) action.getSender();

        });


        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();

            String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));

            Shop shop = new Shop(name);
            shop.create(name, player);
            Data.shop.put(player.getUniqueId(), shop);
        });

        cmd.action("열기", 0, action -> {
            Player player = (Player) action.getSender();

            if (action.getArgs().length >= 1) {
                String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));

                Shop shop = new Shop(name);
                shop.load(player);
                Data.shop.put(player.getUniqueId(), shop);
            }

        });
        cmd.action("편집", 0, action -> {
            Player player = (Player) action.getSender();

            if (action.getArgs().length >= 1) {
                String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));
//
                Shop shop = new Shop(name);
                shop.load(player);
                Data.shop.put(player.getUniqueId(), shop);
            }
        });

        cmd.action("줄", 0, action -> {
            Player player = (Player) action.getSender();


        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();

        });
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> result = new ArrayList<>();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Config config = new Config("SEConomy-shop/");

            config.setPlugin(SkyExcelNetworkSEConomyMain.plugin);
            if (args.length == 1) {
                result.add("생성");
                result.add("열기");
                result.add("편집");
                result.add("줄");
                result.add("제거");
            } else if (args.length == 2) {
                switch (args[0]) {
                    case "열기":
                        for (File file : config.getFileList()) {
                            result.add(file.getName().replace(".yml", ""));
                        }

                        break;
                }
            }
        }
        return result;
    }
}
