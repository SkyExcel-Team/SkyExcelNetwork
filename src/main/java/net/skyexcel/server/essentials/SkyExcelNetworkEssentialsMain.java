package net.skyexcel.server.essentials;

import net.skyexcel.server.essentials.autoclean.SkyExcelNetworkAutoCleanMain;
import net.skyexcel.server.essentials.events.PluginDisableEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.essentials.farmprotect.SkyExcelNetworkFarmProtectMain;
import net.skyexcel.server.essentials.shiftf.SkyExcelNetworkShiftFMain;
import net.skyexcel.server.essentials.shout.SkyExcelNetworkShoutMain;
import net.skyexcel.server.essentials.trashbin.SkyExcelNetworkTrashBinMain;
import net.skyexcel.server.essentials.util.ChatCoolDownUtils;
import net.skyexcel.server.essentials.whisper.SkyExcelNetworkWhisperMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;

public class SkyExcelNetworkEssentialsMain implements Listener, CommandExecutor {
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
        new SkyExcelNetworkShiftFMain(plugin);
        new SkyExcelNetworkShoutMain(plugin);
        new SkyExcelNetworkTrashBinMain(plugin);
        new SkyExcelNetworkWhisperMain(plugin);

        Bukkit.getPluginCommand("hat").setExecutor(this);
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        autoclean.disable();
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

            if (player.getInventory().getItemInMainHand().getType().isAir()) {
                player.sendMessage("손에 아이템을 들고 사용해주세요!");
                return false;
            }

            player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
            player.sendMessage("해당 아이템을 모자로 장착하였습니다! [아이템: " + player.getInventory().getHelmet().getType().name() + "]");
        }

        return false;
    }
}
