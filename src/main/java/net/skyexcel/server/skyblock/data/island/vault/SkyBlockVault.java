package net.skyexcel.server.skyblock.data.island.vault;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SkyBlockVault {

    private Config config;
    private String name;

    private Player player;

    public SkyBlockVault(Player player, String name) {
        this.name = name;
        this.player = player;
        config = new Config("data/SkyBlock/SkyBlock/" + name + "/vault");
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
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

    public void setLock() {
        if(!isLock()){
            config.getConfig().set("lock", true);
            config.saveConfig();
        } else{
            config.getConfig().set("lock", false);
            config.saveConfig();
        }




    }

    public long getAmount() {
        return config.getConfig().getLong("amount");
    }

    public boolean isLock() {
        return config.getConfig().getBoolean("lock");
    }


}
