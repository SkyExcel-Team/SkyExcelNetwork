package net.skyexcel.server.discord;

import net.skyexcel.server.discord.bot.Bot;
import net.skyexcel.server.discord.commands.DiscordVerifyCommandTabComplete;
import net.skyexcel.server.discord.event.ServerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import static net.skyexcel.server.discord.commands.DiscordVerify.VerifyCommand;

public class SkyExcelNetworkDiscordMain {
    public static JavaPlugin plugin;
    public static Config config;
    public static Config botConfig;
    public static Config data;
    public static Bot bot;
    private int titleSchedulerId;

    public SkyExcelNetworkDiscordMain(JavaPlugin plugin) {
        SkyExcelNetworkDiscordMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ServerJoinEvent(), plugin);

        Bukkit.getPluginCommand("인증").setExecutor(VerifyCommand);
        Bukkit.getPluginCommand("인증").setTabCompleter(new DiscordVerifyCommandTabComplete());


        titleSchedulerId = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            if (config.getBoolean("messages.notVerifiedTitle.titleEnable"))
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if (data.getString(player.getUniqueId().toString()).equals("")) {
                        player.sendTitle(ChatColor.translateAlternateColorCodes('&', config.getString("messages.notVerifiedTitle.title")),
                                ChatColor.translateAlternateColorCodes('&', config.getString("messages.notVerifiedTitle.subtitle")),
                                config.getInteger("messages.notVerifiedTitle.fadein") * 20,
                                config.getInteger("messages.notVerifiedTitle.stay") * 20,
                                config.getInteger("messages.notVerifiedTitle.fadeout") * 20);
                    }
                });
        }, 0, (config.getInteger("others.notVerifiedTitlePeriodSeconds") * 20L)).getTaskId();
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTask(titleSchedulerId);

        if (bot.getJDA() != null)
            bot.getJDA().shutdown();
    }

    public void onLoad() {
        config = new Config("config");
        config.loadDefualtConfig();
        botConfig = new Config("bot");
        botConfig.loadDefualtConfig();
        data = new Config("discordVerifies");
        data.loadDefualtConfig();

        bot = new Bot(plugin, botConfig.getString("bot_settings.token"));
    }
}
