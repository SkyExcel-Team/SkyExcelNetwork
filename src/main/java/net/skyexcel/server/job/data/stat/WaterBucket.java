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


    private double level = 1; // 4/ 10 = 2

    private final double stat = 5;


    public WaterBucket() {
        super("WaterBucket");

    }

    public void onGUI(Player player) {
        //TODO 레벨업을 하면, 다음 페이지로 이동 함. 다음 페이지 값을 콘피그에 저장 후, 불러올 때 저장 시킴.

        level = getLevel(player);
        inv = Bukkit.createInventory(null, 54, "[1] 특별한 물병 레벨 : " + level);

        for (double i = 9 * level; i < 45; i++) {
            Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), (int)i, inv);
            System.out.println(i);
        }

        Items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);
        player.openInventory(inv);
    }


    public void levelUp(Player player) {


        if (getStatPoint(player,getName()) != -1) {
            if (getStatPoint(player , getName()) > stat) {
                int tempLevel = (int)level;
                int nextPage = (int) (level) / 5;
                tempLevel -= nextPage * 5;

                double pre = nextPage + 1;
                inv.clear();
                slots.clear();
                for (int i = 9 * (tempLevel + 1); i < 45; i++) {
                    slots.add(i);
                    Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), (int)i, inv);
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
