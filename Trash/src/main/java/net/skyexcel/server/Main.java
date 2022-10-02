package net.skyexcel.server;

import net.skyexcel.server.commands.TrashCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        super.onEnable();

        this.getCommand("쓰레기통").setExecutor(TrashCommand.TRASH);
    }
}
