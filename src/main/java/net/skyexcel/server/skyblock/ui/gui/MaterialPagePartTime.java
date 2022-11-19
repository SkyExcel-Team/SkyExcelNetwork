package net.skyexcel.server.skyblock.ui.gui;

import net.skyexcel.api.packet.Inventory.InventoryUpdate;
import net.skyexcel.server.skyblock.data.SkyBlockData;
import net.skyexcel.server.skyblock.data.StringData;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import net.skyexcel.api.util.Items;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaterialPagePartTime {

    private Inventory inv;

    private int currentPage = 1;

    private int totalPage;

    private final int NEXT_PAGE_SLOT = 50;

    private final int PREVIOUS_PAGE_SLOT = 48;

    private final String title;

    private final List<Material> currentMaterial = new ArrayList<>();


    private final List<Material> materials;


    public MaterialPagePartTime(String title) {
        this.title = title;
        materials = new ArrayList<>(Arrays.stream(Material.values()).filter(Material::isSolid).toList());
    }


    public void update(Player player) {


        materials.add(Material.LIGHT);
        Arrays.stream(SkyBlockData.remove).forEach(materials::remove);

        int divide = 44;
        int MAX = 44;

        this.totalPage = materials.size() / divide;
        if (inv == null)
            inv = Bukkit.createInventory(null, 54, title + " (" + currentPage + "/" + totalPage + ")");

        setInv(inv);
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        SkyBlock islandData = new SkyBlock(playerData.getIsland());

        next(); // 다음 버튼 추가

        int slot = -1;

        /**
         * i = 현제 페이지에서 1을 제거 후, 44를 곱한다.(처음 페이지를 인식 하기 위함. (0일경우))
         * 이후 i가 현제 페이지와 44를 곱한 값보다 작거나 같을때 i값을 계속 더해준다. (materialList의 값을 불러오기 위함.),
         */

        for (int i = (currentPage - 1) * MAX; i <= currentPage * MAX; i++) {

            if (slot <= MAX) {
                Material material = materials.get(materials.indexOf(materials.get(i)));
                this.currentMaterial.add(material);

//                Items.newItem(ChatColor.GRAY + material.name(), material, 1, Arrays.asList("클릭하여 선택하세요!"), ++slot, inv);
                unSelected(material, ++slot);

                /**
                 * 이미 선택 되어 있는 블록은 Enchant 표시를 해준다.
                 */
                if (islandData.getBanBlockMember() != null) {
                    for (Material banblock : islandData.getBanBlockMember()) {
                        if (material.equals(banblock)) {
                            Items.Enchant(new ItemStack(material), inv, slot);
                        }
                    }
                }
            }
        }
    }

    public void unSelected(Material material, int slot) {
        Items.newItem(ChatColor.GRAY + material.name(), material, 1, Arrays.asList(ChatColor.WHITE + "클릭하여 선택하세요!"), slot, inv);
    }


    public void select(Player player, int slot, boolean shift) {

        Material material = this.currentMaterial.get(slot);

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        SkyBlock islandData = new SkyBlock(playerData.getIsland());
        List<Material> member = islandData.getBanBlockMember();


        if (!shift) {
            if (!member.contains(material)) {
                islandData.addBanBlockMember(material);
                Items.Enchant(new ItemStack(material), Arrays.asList(ChatColor.RED + "(쉬프트 + 클릭) 밴블록 해제"), inv, slot);
            } else {
                player.sendMessage(ChatColor.RED + "이미 밴 블록 입니다!");
            }
        } else {
            if (member.contains(material)) {
                islandData.removeBanBlockMember(material);
//                Items.newItem(ChatColor.GRAY + material.name(), material, 1, Arrays.asList(""), slot, inv);
                unSelected(material, slot);
            } else {
                player.sendMessage(ChatColor.RED + "밴 블록이 아닙니다!");
            }
        }
    }


    public void nextPage(Player player, boolean isShift) {
        this.currentMaterial.clear();
        if (!isShift) {
            InventoryUpdate.updateInventory(SkyExcelNetworkSkyBlockMain.plugin, player, title + " (" + ++currentPage + "/" + totalPage + ")");
            update(player);

            if (currentPage != totalPage) {

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
        this.currentMaterial.clear();
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
        Items.newItem(StringData.NextPageName, Material.OAK_SIGN, 1, Arrays.asList(ChatColor.GRAY + "쉬프트를 눌러 페이지의 끝으로 갈 수 있습니다."), NEXT_PAGE_SLOT, inv);
    }

    private void previous() {
        Items.newItem(StringData.PreviousPageName, Material.OAK_SIGN, 1, Arrays.asList(ChatColor.GRAY + "쉬프트를 눌러 페이지의 처음으로 갈 수 있습니다."), PREVIOUS_PAGE_SLOT, inv);
    }

    private void clearItem(int slot) {
        ItemStack item = inv.getItem(slot);
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
