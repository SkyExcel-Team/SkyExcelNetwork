package net.skyexcel.server.data.vault;

import net.skyexcel.server.SkyBlockCore;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SkyBlockVault {

    private Config config;
    private String name;

    private Player player;

    public SkyBlockVault(Player player, String name) {
        this.name = name;
        this.player = player;
        config = new Config("SkyBlock/" + name + "/vault");
        config.setPlugin(SkyBlockCore.plugin);
    }

    public void create() {
        config.setString("name", name);
        config.getConfig().set("amount", 0);
        config.getConfig().set("lock", false);
        config.saveConfig();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean withdraw(long amount) {
        if (player.hasPermission("island.admin")) {
            if (getAmount() - amount > 0) {
                if (setAmount(getAmount() - amount))

                    return true;
            }
        }

        return false;
    }

    public boolean deposit(long amount) {

        return setAmount(getAmount() + amount);
    }


    public boolean setAmount(long amount) {

        if (config.getConfig().get("amount") != null) {
            config.getConfig().set("amount", amount);
            config.saveConfig();
            return true;
        }
        return false;
    }

    public void setLock(boolean lock) {
        if (player.hasPermission("island.admin")) {
            config.getConfig().set("lock", lock);
            config.saveConfig();
        }

    }

    public long getAmount() {
        return config.getConfig().getLong("amount");
    }

    public boolean getLock() {
        return config.getConfig().getBoolean("lock");
    }


}
