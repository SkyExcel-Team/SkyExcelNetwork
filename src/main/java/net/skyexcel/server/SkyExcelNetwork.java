package net.skyexcel.server;

import net.skyexcel.server.trash.TrashCommand;
import net.skyexcel.server.trash.TrashManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();



        this.getCommand("쓰레기통").setExecutor(TrashCommand.TRASH);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
