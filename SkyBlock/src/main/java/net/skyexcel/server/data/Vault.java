package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;

public class Vault {

    private Config config;
    private String name;

    private Vault.RecordObject record;

    public Vault(String name) {
        this.name = name;
        config = new Config("island/" + name + "/vault");
        config.setPlugin(SkyExcelNetwork.plugin);
        record = new Vault.RecordObject(name);
    }

    public void create() {
        config.setString("name", name);
        config.getConfig().set("amount", 0);
        config.getConfig().set("lock", false);
        config.saveConfig();
    }

    public boolean withdraw(int amount) {
        if (getAmount() - amount <= 0) {
            if (setAmount(getAmount() - amount))
                return true;
        }
        return false;
    }

    public boolean deposit(int amount) {

        if (setAmount(getAmount() + amount))
            return true;

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

    public int getAmount() {
        return config.getConfig().getInt("amount");
    }

    public boolean getLock() {
        return config.getConfig().getBoolean("lock");
    }

    private static class RecordObject {

        private Player player;
        private int amount;
        private Type type;
        private String name;

        private Config record;

        public RecordObject(String name) {
            this.name = name;
            this.record = new Config("island/" + name + "/record/record");
            this.record.setPlugin(SkyExcelNetwork.plugin);
            record.getConfig().set("record", new ArrayList<>());
        }

        public void add() {

        }
    }

    private static enum Type {
        DEPOSIT, WITHDRAW
    }
}
