package net.skyexcel.server.ui;

import net.skyexcel.server.data.StringData;
import net.skyexcel.server.data.island.IslandData;
import net.skyexcel.server.data.player.PlayerData;
import net.skyexcel.server.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialPagePartTime {
    private int CurrentPage = 1;
    private int PageSize = 44;
    private int totalPage = 15;
    private Inventory inv;
    private String title;
    private String world;

    public void increaseCurrentPage() {
        if (CurrentPage < totalPage) {
            CurrentPage += 1;
        }
    }

    public void decreaseCurrentPage() {
        if (CurrentPage > 0) {
            CurrentPage -= 1;
        }
    }

    public int getPageSize() {
        return PageSize;
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void increase(Player player) {

        increaseCurrentPage();

        title = "";
        title = getWorld() + "";
        Inventory inv = Show(player, title);

        previoussign();
        nextsign();

        if (CurrentPage == 15) {
            inv.setItem(50, new ItemStack(Material.AIR));
        }

        player.openInventory(inv);
    }

    public void nextsign() {
        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, Arrays.asList(""), 50, inv);
    }

    public void previoussign() {
        Items.newItem(StringData.PreviousPageName, Material.OAK_SIGN, 1, Arrays.asList(""), 48, inv);
    }

    public Inventory Show(Player player, String name) {
        setWorld(name);
        Inventory inv2 = Bukkit.createInventory(null, 54, setTitle(name));
        List<Material> materials = Arrays.stream(Material.values()).filter(Material::isSolid).collect(Collectors.toList());

        int i2 = 0;

        for (int i = (getCurrentPage() - 1) * getPageSize(); i <= getCurrentPage() * getPageSize(); i++) {
            if (i2 <= 44) {
                Material material = materials.get(materials.indexOf(materials.get(i)));

                ItemStack banblock = new ItemStack(material);
                ItemMeta banblockmeta = banblock.getItemMeta();

                banblock.setItemMeta(banblockmeta);
                inv2.setItem(i2, banblock);
                getBanBlockPartTime(material, player, inv2, i2);
                i2++;

            }
        }

        this.inv = inv2;

        player.openInventory(inv2);
        return inv2;
    }

    public String setTitle(String name) {
        title = "";

        title = name + " (" + getCurrentPage() + "/15)";
        return title;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getWorld() {
        return world;
    }

    public void decrease(Player player) {
        decreaseCurrentPage();
        title = "";
        title = getWorld() + "";
        Inventory inv = Show(player, title);

        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, Arrays.asList(""), 50, inv);
        Items.newItem(StringData.PreviousPageName, Material.OAK_SIGN, 1, Arrays.asList(""), 48, inv);
        player.openInventory(inv);
        if (CurrentPage == 1) {
            inv.setItem(48, new ItemStack(Material.AIR));
        }
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    private void getBanBlockPartTime(Material material, Player player, Inventory inv2, int i2) {
        PlayerData playerData = new PlayerData(player);
        IslandData islandData = new IslandData(playerData.getIsland());

        if (islandData.getBanBlockMember() != null) {
            for (Material materials : islandData.getBanBlockPartTime()) {
                if (material.equals(materials)) {
                    Items.Enchant(new ItemStack(material), inv2, i2);
                }
            }
        }
    }
}
