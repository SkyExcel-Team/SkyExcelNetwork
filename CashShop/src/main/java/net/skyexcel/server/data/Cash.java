package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class Cash implements CashData {

    private Player player;
    private Config config;

    private int amount = 0;

    public Cash(Player player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void increaseAmount(int amount) {

    }

    public void decreaseAmount(int amount) {

    }

    public void setAmount(int amount) {

    }

    public int getAmount() {
        return config.getConfig().getInt("amount");
    }

    @Override
    public void Increase(int Amount) {
        setAmount(getAmount() + amount);
    }

    @Override
    public void Decrease(int Amount) {
        setAmount(getAmount() - amount);
    }

    @Override
    public void Set(int Amount) {
        config.getConfig().set("amount", amount);
        config.saveConfig();
    }
}
