package net.skyexcel.server.data.vault;

import net.skyexcel.server.SkyExcelNetwork;
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
        config.setPlugin(SkyExcelNetwork.plugin);
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

    public boolean withdraw(int amount) {
        if (player.hasPermission("island.admin")) {
            if (getAmount() - amount > 0) {
                if (setAmount(getAmount() - amount))

                    return true;
            }
        }

        return false;
    }

    public boolean deposit(int amount) {

        if (setAmount(getAmount() + amount)) {
            return true;
        }

        return false;
    }


    public boolean setAmount(int amount) {

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

    public int getAmount() {
        return config.getConfig().getInt("amount");
    }

    public boolean getLock() {
        return config.getConfig().getBoolean("lock");
    }


}
