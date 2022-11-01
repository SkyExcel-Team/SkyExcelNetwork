package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

import java.util.ArrayList;
import java.util.List;

public class Items {


    private ItemStack itemStack;

    private String name;
    private Material material;
    private String display;
    private int Amount;

    private List<String> lore = new ArrayList<>();

    private Config config;

    private GUI gui;


    public Items(String name) {
        this.name = name;
        this.config = new Config("data/items");
        config.setPlugin(SkyExcelNetwork.plugin);
        gui = new GUI(config);
    }

    public void create(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.getType().equals(Material.AIR)) {
            gui.setItemStack("item", item);
        }
    }

    public void setName(String name) {
        this.name = name;
        config.setString(name + ".name", name);
    }

    public void setDisplay(String display) {
        config.setString(name + ".display", display);
    }
}
