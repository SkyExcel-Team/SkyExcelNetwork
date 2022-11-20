package net.skyexcel.server.discord;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.discord.bot.Bot;
import net.skyexcel.server.discord.commands.DiscordVerifyCommandTabComplete;
import net.skyexcel.server.discord.event.DiscordListener;
import net.skyexcel.server.essentials.events.PluginDisableEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import static net.skyexcel.server.discord.commands.DiscordVerify.VerifyCommand;

public class SkyExcelNetworkDiscordMain implements Listener {
    private static JavaPlugin plugin;
    public static Config config;
    public static Config botConfig;
    public static Config data;
    public static Bot bot = null;
    private int titleSchedulerId = 0;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        botConfig = new Config("Discord-bot");
        botConfig.setPlugin(plugin);
        botConfig.loadDefaultPluginConfig();

        if (!botConfig.getBoolean("bot_settings.enable")) return;

        config = new Config("Discord-config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();

        data = new Config("Discord-discordVerifies");
        data.setPlugin(plugin);
        data.loadDefaultPluginConfig();

        bot = new Bot(plugin, botConfig.getString("bot_settings.token"));

        Bukkit.getPluginManager().registerEvents(new DiscordListener(), plugin);

        Bukkit.getPluginCommand("인증").setExecutor(VerifyCommand);
        Bukkit.getPluginCommand("인증코드").setExecutor(VerifyCommand);
        Bukkit.getPluginCommand("인증").setTabCompleter(new DiscordVerifyCommandTabComplete());


        titleSchedulerId = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            if (config.getBoolean("messages.notVerifiedTitle.titleEnable"))
                Bukkit.getOnlinePlayers().forEach(player -> {
                    String discordId = data.getString(player.getUniqueId().toString());

                    if (discordId == null) return;
                    if (discordId.equals("")) {
                        player.sendTitle(ChatColor.translateAlternateColorCodes('&', config.getString("messages.notVerifiedTitle.title")),
                                ChatColor.translateAlternateColorCodes('&', config.getString("messages.notVerifiedTitle.subtitle")),
                                config.getInteger("messages.notVerifiedTitle.fadein") * 20,
                                config.getInteger("messages.notVerifiedTitle.stay") * 20,
                                config.getInteger("messages.notVerifiedTitle.fadeout") * 20);
                    }
                });
        }, 0, (config.getInteger("others.notVerifiedTitlePeriodSeconds") * 20L)).getTaskId();
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        if (titleSchedulerId != 0)
            Bukkit.getScheduler().cancelTask(titleSchedulerId);

        if (bot.getJDA() != null)
            bot.getJDA().shutdown();
    }
}
