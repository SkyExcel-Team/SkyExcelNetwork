package net.skyexcel.server.lockmanager.gui;

import net.skyexcel.server.items.data.Items;
import net.skyexcel.server.lockmanager.data.BoxSortType;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class MemberGUI extends Items {

    private Inventory inv;

    private BoxSortType boxSortType = BoxSortType.MEMBER;


    public void open(Player player) {
        inv = Bukkit.createInventory(null, 54, "상자 잠금");

        SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);
        SkyBlock skyBlock = new SkyBlock(skyBlockPlayerData.getIsland());

        BoxSortTypeButton(inv);

        player.openInventory(inv);
    }

    public void BoxSortTypeButton(Inventory inv) {
        setMaterial(Material.LEGACY_REDSTONE_COMPARATOR, 1);
        setDisplay("§e정렬");
        setLore(List.of("§7" + BoxSortType.MEMBER.getName(), "§7" + BoxSortType.ONLINE.getName()));
        setInventory(49, inv);
    }

    public Inventory getInv() {
        return inv;
    }
}
