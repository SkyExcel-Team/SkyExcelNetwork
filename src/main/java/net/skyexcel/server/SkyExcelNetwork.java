package net.skyexcel.server;

import net.skyexcel.server.test.Test;
import net.skyexcel.server.trash.TrashCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {


    public static Plugin plugin;

    @Override
    public void onEnable() {
        super.onEnable();

        plugin = this;

        this.getCommand("쓰레기통").setExecutor(TrashCommand.TRASH);
        this.getCommand("test").setExecutor(new Test(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
