package net.skyexcel.server.skyblock.ui.gui;

import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class UpgradeGUI {

    private String name;

    private SkyBlock skyBlock;


    public UpgradeGUI(String name) {
        this.name = name;
        this.skyBlock = new SkyBlock(name);
    }


    public void create(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, name + " 섬 레벨 " + skyBlock.getLevel());

        Items.newItem("호퍼 늘리기", Material.HOPPER, 1, List.of(""), 10, inv);

        playerSkull(player.getName(), "섬원 늘리기", List.of(), 12, inv);

        Items.newItem("섬 크기 늘리기", Material.BARRIER, 1, List.of(""), 14, inv);

        ItemStack itemStack = SkyExcelNetworkMenuMain.hdb.getItemHead("36295");

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("섬 워프 늘리기");
        meta.setCustomModelData(1);
        itemStack.setItemMeta(meta);
        inv.setItem(16, itemStack);

        player.openInventory(inv);
    }


    private void playerSkull(String name, String display, List<String> lore, int slot, Inventory inv) {
        OfflinePlayer owner = Bukkit.getOfflinePlayer(name);


        ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);

        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);

        meta.setOwner(owner.getName());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', display));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        inv.setItem(slot, stack);
    }
}
