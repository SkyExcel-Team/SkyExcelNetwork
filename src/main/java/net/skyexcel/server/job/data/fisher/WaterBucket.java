package net.skyexcel.server.job.data.fisher;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.StatMeta;
import net.skyexcel.server.job.data.stat.Statable;
import net.skyexcel.server.packet.Inventory.InventoryUpdate;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class WaterBucket extends StatMeta implements JobPlayerData {


    private Inventory inv;

    private final int line = 9; //

    private final String name = "WaterBucket";
    private List<Integer> slots = new ArrayList<>();


    private double level = 1; // 4/ 10 = 2

    private final double stat = 5;

    private String path = "job/";


    public WaterBucket(OfflinePlayer player) {
        super("특별한 물통", List.of("", "§6§l│ §9물고기§f를 §6보관§f할 수 있는 §9물통 ", "§6§l│ §6효과:", "§6§l│ §9물고기§f를 잡을 시 §9물통§f으로 이동됩니다.", "§6§l│ §9물통§f은 §6레벨당 §fGUI 1줄씩 추가됩니다. §7(페이지 존재)", "§6§l│ §f특정 §6레벨 §f달성 시, §9물통§f 안에서 §a판매 §f가능", "",
                ChatColor.GRAY + "1/5"));
    }

    public void onGUI(Player player) {
        //TODO 레벨업을 하면, 다음 페이지로 이동 함. 다음 페이지 값을 콘피그에 저장 후, 불러올 때 저장 시킴.

        level = getLevel(player);
        inv = Bukkit.createInventory(null, 54, "[1] 특별한 물병 레벨 : " + level);

        for (double i = 9 * level; i < 45; i++) {
            Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), (int) i, inv);
            System.out.println(i);
        }

        Items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);
        player.openInventory(inv);
    }

    public void setDefault(Player player) {

        path = path + player.getUniqueId();
        Config config = new Config("job/" + player.getUniqueId() + "/WaterBucket");

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.getConfig().set("items", new ArrayList<>());
        config.saveConfig();
    }


    public void levelUp(Player player) {
        if (getStatPoint(player, name) != -1) {
            if (getStatPoint(player, name) > stat) {
                int tempLevel = (int) level;
                int nextPage = (int) (level) / 5;
                tempLevel -= nextPage * 5;

                double pre = nextPage + 1;
                inv.clear();
                slots.clear();
                for (int i = 9 * (tempLevel + 1); i < 45; i++) {
                    slots.add(i);
                    Items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), (int) i, inv);
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
