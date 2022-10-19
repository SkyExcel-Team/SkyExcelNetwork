package net.skyexcel.server.ui.gui;

import net.skyexcel.server.data.StringData;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
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

    private int NEXT_PAGE_SLOT = 50;

    private int PREVIOUS_PAGE_SLOT = 48;

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
        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, Arrays.asList(""), NEXT_PAGE_SLOT, inv);
    }

    public void previoussign() {
        Items.newItem(StringData.PreviousPageName, Material.OAK_SIGN, 1, Arrays.asList(""), PREVIOUS_PAGE_SLOT, inv);
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
        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, Arrays.asList(""), NEXT_PAGE_SLOT, inv);
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

        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, Arrays.asList(""), NEXT_PAGE_SLOT, inv);
        Items.newItem(StringData.PreviousPageName, Material.OAK_SIGN, 1, Arrays.asList(""), PREVIOUS_PAGE_SLOT, inv);
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
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        SkyBlock islandData = new SkyBlock(playerData.getIsland());

        if (islandData.getBanBlockMember() != null) {
            for (Material materials : islandData.getBanBlockPartTime()) {
                if (material.equals(materials)) {
                    Items.Enchant(new ItemStack(material), inv2, i2);
                }
            }
        }
    }


    public void addBanBlock(Material material, Player player, Inventory inv2, int i2) {
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        SkyBlock islandData = new SkyBlock(playerData.getIsland());

        if (islandData.getBanBlockMember() != null) {
            if (!islandData.getBanBlockMember().contains(material)) {
                islandData.addBanBlockPartTime(material);
                Items.Enchant(new ItemStack(material), inv2, i2);
            } else {
                islandData.removeBanBlockPartTime(material);
                Items.newItem(material.name(),material, 1, Arrays.asList(""), i2, inv);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public int getNEXT_PAGE_SLOT() {
        return NEXT_PAGE_SLOT;
    }

    public int getPREVIOUS_PAGE_SLOT() {
        return PREVIOUS_PAGE_SLOT;
    }
}
