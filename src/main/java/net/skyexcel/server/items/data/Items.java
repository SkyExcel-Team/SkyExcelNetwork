package net.skyexcel.server.items.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

import java.util.ArrayList;
import java.util.List;

public abstract class Items {


    private ItemStack itemStack = new ItemStack(Material.AIR);


    private GUI gui;
    private String name;
    private Material material;
    private String display;
    private int Amount;

    private List<String> lore = new ArrayList<>();

    private Config config;

    private int CustomModelData;

    public Items() {
    }

    public Items(String name) {
        this.name = name;
        this.config = new Config("items/" + name);
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        gui = new GUI(config);
    }

    public void create(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!item.getType().equals(Material.AIR)) {
            player.sendMessage(name + " 아이템 생성을 완료했습니다.");
            gui.setItemStack(name, itemStack);
        } else {
            player.sendMessage("아이템은 공기가 될 수 없습니다!");
        }
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }


    public void setMaterial(Material material) {
        itemStack = new ItemStack(material);
        this.material = material;
    }

    public void setMaterial(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        this.material = material;
        this.Amount = amount;
    }


    public void setCustomModelData(int customModelData) {
        if (itemStack != null) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.setCustomModelData(customModelData);
            itemStack.setItemMeta(meta);
            CustomModelData = customModelData;
        }
    }

    public void setLore(List<String> lore) {
        if (itemStack != null) {
            this.lore = lore;
            ItemMeta meta = itemStack.getItemMeta();
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
        }

    }

    public void setAmount(int amount) {
        if (itemStack != null) {
            Amount = amount;
            itemStack.setAmount(amount);
        }
    }

    public void setName(String name) {
        this.name = name;

    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void getHeadItemFromHDB(String id) {
        this.itemStack = SkyExcelNetworkMain.hdb.getItemHead(id);

        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "hdb");
        pdc.set(namespacedKey, PersistentDataType.STRING, id);
        itemStack.setItemMeta(meta);
    }

    public void toHDB(ItemStack itemStack) {
        
    }


    public void setDisplay(String display) {
        if (itemStack != null) {
            this.display = display;
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(display);
            itemStack.setItemMeta(meta);
        }
    }

    public void setInventory(int slot, Inventory inv) {
        inv.setItem(slot, getItemStack());
    }

    public ItemMeta getItemMeta() {
        return itemStack.getItemMeta();
    }

    public int getAmount() {
        return Amount;
    }
}
