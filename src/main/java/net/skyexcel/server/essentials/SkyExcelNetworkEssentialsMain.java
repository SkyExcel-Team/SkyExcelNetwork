package net.skyexcel.server.essentials;

import net.skyexcel.server.essentials.autoclean.SkyExcelNetworkAutoCleanMain;
import net.skyexcel.server.essentials.events.PluginDisableEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.essentials.farmprotect.SkyExcelNetworkFarmProtectMain;
import net.skyexcel.server.essentials.playtime.SkyExcelNetworkPlayTimeMain;
import net.skyexcel.server.essentials.shiftf.SkyExcelNetworkShiftFMain;
import net.skyexcel.server.essentials.shout.SkyExcelNetworkShoutMain;
import net.skyexcel.server.essentials.trashbin.SkyExcelNetworkTrashBinMain;
import net.skyexcel.server.essentials.util.ChatCoolDownUtils;
import net.skyexcel.server.essentials.whisper.SkyExcelNetworkWhisperMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.data.file.Config;

import java.util.Collections;
import java.util.List;

public class SkyExcelNetworkEssentialsMain implements Listener, CommandExecutor , TabCompleter {
    private static JavaPlugin plugin;
    public static Config config;

    private SkyExcelNetworkAutoCleanMain autoclean;
    public static final ChatCoolDownUtils chatCoolDown = new ChatCoolDownUtils();

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        config = new Config("Essentials-config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();

        autoclean = new SkyExcelNetworkAutoCleanMain(plugin);
        new SkyExcelNetworkFarmProtectMain(plugin);
        new SkyExcelNetworkPlayTimeMain(plugin);
        new SkyExcelNetworkShiftFMain(plugin);
        new SkyExcelNetworkShoutMain(plugin);
        new SkyExcelNetworkTrashBinMain(plugin);
        new SkyExcelNetworkWhisperMain(plugin);

        Bukkit.getPluginCommand("hat").setExecutor(this);
        Bukkit.getPluginCommand("essentials").setExecutor(this);
        Bukkit.getPluginCommand("essentials").setTabCompleter(this);
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        autoclean.onDisable();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("hat")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("이 명령어는 플레이어만 사용 가능합니다!");
                return false;
            }

            if (!player.isOp()) {
                sender.sendMessage("이 명령어는 OP만 사용 가능합니다!");
                return false;
            }

            if (args.length > 0) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            if (player.getInventory().getItemInMainHand().getType().isAir()) {
                player.sendMessage("손에 아이템을 들고 사용해주세요!");
                return false;
            }

            player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
            player.sendMessage("해당 아이템을 모자로 장착하였습니다! [아이템: " + player.getInventory().getHelmet().getType().name() + "]");
        } else if (command.getName().equals("essentials")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("이 명령어는 플레이어만 사용 가능합니다!");
                return false;
            }

            if (!player.isOp()) {
                player.sendMessage("이 명령어는 OP만 사용 가능합니다!");
                return false;
            }

            if (args.length == 0) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            if (args[0].equals("reload")) {
                config.reloadConfig();
                player.sendMessage("§a설정 파일을 다시 불러왔습니다!");

                autoclean.onDisable();
                autoclean = new SkyExcelNetworkAutoCleanMain(plugin);
                player.sendMessage("§a스케줄러를 다시 불러왔습니다!");

                return true;
            } else {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return sender.isOp() ? List.of("reload") : Collections.emptyList();

        return Collections.emptyList();
    }
}
