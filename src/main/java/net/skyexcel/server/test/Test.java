package net.skyexcel.server.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import skyexcel.command.Tab;

public class Test implements CommandExecutor {


    public Test(Plugin plugin) {
        Tab<Object, String> tab = new Tab<Object, String>(plugin, "test");

        tab.args("열기", "[이름]");
        tab.args("생성", "[이름]");
        tab.args("리로드");


    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        return false;
    }
}
