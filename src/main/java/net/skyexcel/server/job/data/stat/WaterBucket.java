package net.skyexcel.server.job.data.stat;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.packet.Inventory.InventoryUpdate;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class WaterBucket extends Statable {


    private Inventory inv;

    private final int line = 9; //

    private List<Integer> slots = new ArrayList<>();

    private int level = 1; // 4/ 10 = 2


    public WaterBucket() {
        super("물병");
    }

    public void onGUI(Player player) {
        inv = Bukkit.createInventory(null, 54, "특별한 물병 레벨 : " + level);

//        for (int i = 9 * level; i < 9 * (47 / 9); i++) {
//            Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), i, inv);
//            this.slots.add(i);

//        }


        Items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);
        player.openInventory(inv);
    }


    public void levelUp(Player player) {
        int tempLevel = level;
        int nextPage = level / 5;
        tempLevel -= nextPage * 5;


        for (int i = 9 *(tempLevel - 1); i < 45; i++) {
            Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), i, inv);
        }

        InventoryUpdate.updateInventory(SkyExcelNetworkJobMain.plugin, player, "특별한 물병 레벨 : " + ++level);
        System.out.println(nextPage + "/ " + tempLevel);
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public Inventory getInv() {
        return inv;
    }
}
