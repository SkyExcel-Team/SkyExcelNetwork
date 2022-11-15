package net.skyexcel.server.skyblock.ui.gui;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.data.SkyBlockData;
import net.skyexcel.server.skyblock.data.StringData;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import net.skyexcel.server.skyblock.util.Items;
import net.skyexcel.server.skyblock.util.packet.InventoryUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageVisitor {
    private Inventory inv;

    private int currentPage = 1;

    private int totalPage;

    private final int NEXT_PAGE_SLOT = 50;

    private final int PREVIOUS_PAGE_SLOT = 48;

    private final String title;




    public PageVisitor(String title) {
        this.title = title;


    }


    public void update(Player player) {
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        SkyBlock islandData = new SkyBlock(playerData.getIsland());

        int divide = 1;
        int MAX = 1;

        this.totalPage = islandData.getVisitors().size() / divide;
        if (inv == null)
            inv = Bukkit.createInventory(null, 54, title + " (" + currentPage + "/" + totalPage + ")");

        setInv(inv);

        next(); // 다음 버튼 추가

        int slot = -1;

        /**
         * i = 현제 페이지에서 1을 제거 후, 44를 곱한다.(처음 페이지를 인식 하기 위함. (0일경우))
         * 이후 i가 현제 페이지와 44를 곱한 값보다 작거나 같을때 i값을 계속 더해준다. (materialList의 값을 불러오기 위함.),
         */

        for (int i = (currentPage - 1) * MAX; i <= currentPage * MAX; i++) {
            if (slot <= MAX) {
                unSelected(islandData.getVisitors().get(i), ++slot);
            }
        }
    }

    public void unSelected(String name, int slot) {

        inv.setItem(slot,Items.playerSkull(name,"", List.of()));
    }

    public void nextPage(Player player, boolean isShift) {

        if (!isShift) {
            if (currentPage + 1 <= totalPage) {
                InventoryUpdate.updateInventory(SkyExcelNetworkSkyBlockMain.plugin, player, title + " (" + ++currentPage + "/" + totalPage + ")");
                update(player);
                previous(); //이전 버튼 추가

            } else {
                clearItem(NEXT_PAGE_SLOT);
            }
        } else {
            this.currentPage = totalPage;
            previous();
            InventoryUpdate.updateInventory(SkyExcelNetworkSkyBlockMain.plugin, player, title + " (" + currentPage + "/" + totalPage + ")");
            update(player);
            clearItem(NEXT_PAGE_SLOT);
        }

    }

    public void previousPage(Player player, boolean isShift) {

        if (!isShift) {
            InventoryUpdate.updateInventory(SkyExcelNetworkSkyBlockMain.plugin, player, title + " (" + --currentPage + "/" + totalPage + ")");
            update(player);

            if (currentPage != 1) {
                previous(); //이전 버튼 추가
            } else {
                clearItem(PREVIOUS_PAGE_SLOT);
            }
        } else {
            currentPage = 1;
            InventoryUpdate.updateInventory(SkyExcelNetworkSkyBlockMain.plugin, player, title + " (" + currentPage + "/" + totalPage + ")");
            update(player);

            if (currentPage != 1) {
                previous(); //이전 버튼 추가
            } else {
                clearItem(PREVIOUS_PAGE_SLOT);
            }
        }
    }

    private void next() {
        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, List.of(ChatColor.GRAY + "쉬프트를 눌러 페이지의 끝으로 갈 수 있습니다."), NEXT_PAGE_SLOT, inv);
    }

    private void previous() {
        Items.newItem(StringData.PreviousPageName, Material.OAK_SIGN, 1, List.of(ChatColor.GRAY + "쉬프트를 눌러 페이지의 처음으로 갈 수 있습니다."), PREVIOUS_PAGE_SLOT, inv);
    }

    private void clearItem(int slot) {
        ItemStack item = inv.getItem(slot);
        assert item != null;
        item.setAmount(0);
        inv.setItem(slot, item);
    }


    public int getPREVIOUS_PAGE_SLOT() {
        return PREVIOUS_PAGE_SLOT;
    }

    public int getNEXT_PAGE_SLOT() {
        return NEXT_PAGE_SLOT;
    }


    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInv() {
        return inv;
    }
}
