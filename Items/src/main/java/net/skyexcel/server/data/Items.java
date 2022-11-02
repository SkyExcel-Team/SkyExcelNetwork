package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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


    public Items(String name) {
        this.name = name;
        this.config = new Config("data/items");
        config.setPlugin(SkyExcelNetwork.plugin);

    }

    public void create(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!item.getType().equals(Material.AIR)) {
            player.sendMessage(name + " 아이템 생성을 완료했습니다.");

            config.setString("items." + name + ".Material", item.getType().name());
            config.setInteger("items." + name + ".Amount", item.getAmount());

            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (item.hasDisplayName()) {
                    config.setString("items." + name + ".meta.display", meta.getDisplayName());
                } else if (item.hasLore()) {
                    config.getConfig().set("items." + name + ".meta.lore", item.getLore());
                    config.saveConfig();
                } else if (item.hasCustomModelData()) {
                    config.setInteger("items." + name + ".meta.CustomModelData", item.getCustomModelData());
                }
            }
        } else {
            player.sendMessage("아이템은 공기가 될 수 없습니다!");
        }
    }

    public void delete(Player player) {

    }

    public void setName(String name) {
        this.name = name;
        config.setString(name + ".name", name);
    }

    public void setDisplay(String display) {
        config.setString(name + ".display", display);
    }
}
