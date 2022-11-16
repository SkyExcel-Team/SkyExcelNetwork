package net.skyexcel.server.job.data.stat;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.packet.Inventory.InventoryUpdate;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class WaterBucket extends Statable implements JobPlayerData {


    private Inventory inv;

    private final int line = 9; //

    private List<Integer> slots = new ArrayList<>();

    private int level = 1; // 4/ 10 = 2

    private final double stat = 5;


    public WaterBucket() {
        super("물병");
    }

    public void onGUI(Player player) {
        inv = Bukkit.createInventory(null, 54, "[1] 특별한 물병 레벨 : " + level);

        for (int i = 9 * level; i < 45; i++) {
            Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), i, inv);
            System.out.println(i);
        }

        Items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);
        player.openInventory(inv);
    }


    public void levelUp(Player player) {

        if (getStatPoint(player) != -1) {
            if (getStatPoint(player) > stat) {
                int tempLevel = level;
                int nextPage = (level) / 5;
                tempLevel -= nextPage * 5;

                int pre = nextPage + 1;
                inv.clear();
                for (int i = 9 * (tempLevel + 1); i < 45; i++) {
                    Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), i, inv);
                    System.out.println(i);
                }

                InventoryUpdate.updateInventory(SkyExcelNetworkJobMain.plugin, player, "[" + pre + "] 특별한 물통 레벨 : " + ++level);

                System.out.println(nextPage + "/ " + tempLevel);
                Items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);
                player.sendMessage("레벨업에 성공 하였습니다!");
            } else {
                player.sendMessage("스텟 포인트가 부족합니다!");
            }
        } else {
            player.sendMessage("스텟 포인트가 존재하지 않습니다!");
        }


    }

    public List<Integer> getSlots() {
        return slots;
    }

    public Inventory getInv() {
        return inv;
    }
}
