package net.skyexcel.server.mileage.data.shop;

import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;


public class Shop {

    private String name;

    private Config config;

    private GUI gui;

    private int slot;

    private SettingType type;


    public Shop(String name) {
        this.name = name;
        config = new Config("Menu-shop/" + name);
        config.setPlugin(SkyExcelNetworkMileageMain.plugin);

        gui = new GUI(config);
    }


    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void create(String name, Player player) {
        this.name = name;
        Inventory inv = Bukkit.createInventory(null, 9, name);

        config.saveConfig();

        player.openInventory(inv);
    }

    public void load(Player player) {
        Inventory inv = gui.getInventory("shop");
        player.openInventory(inv);
    }

    public void save(Inventory inv) {
        gui.saveInventory("shop", inv);
    }


    public void setType(SettingType type) {
        this.type = type;
    }

    public void setSellPrice(long price) {
        gui.path("shop.inv.items." + slot).setLong("sell", price);
    }

    public void setBuyPrice(long price) {
        gui.path("shop.inv.items." + slot).setLong("buy", price);
    }


    public long getBuyPrice() {
        return gui.path("shop.inv.items." + slot).getLong("buy");
    }

    public long getSellPrice() {
        return gui.path("shop.inv.items." + slot).getLong("sell");
    }

    public Config getConfig() {
        return config;
    }

    public String getName() {
        return name;
    }

    public int getSlot() {
        return slot;
    }

    public GUI getGui() {
        return gui;
    }

    public SettingType getType() {
        return type;
    }
}
