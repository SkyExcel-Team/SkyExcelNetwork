package net.skyexcel.server.job.data.fisher;

import net.skyexcel.api.packet.Inventory.InventoryUpdate;
import net.skyexcel.api.util.Items;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.StatMeta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

import java.util.ArrayList;
import java.util.List;

public class WaterBucket extends StatMeta implements JobPlayerData {


    private Inventory inv;

    private final String name = "WaterBucket";
    private List<Integer> slots = new ArrayList<>();


    private int level = 1; // 4/ 10 = 2

    private final double stat = 5;

    private String path = "job/";

    private Items items = new Items();

    private final int previousSlot = 48;

    private OfflinePlayer offlinePlayer;

    private int pre = 1;

    private int page = 1;
    private final int nextSlot = 50;


    public WaterBucket(OfflinePlayer player) {
        super("특별한 통발", List.of("", "§6§l│ §9물고기§f를 §6보관§f할 수 있는 §9물통 ", "§6§l│ §6효과:", "§6§l│ §9물고기§f를 잡을 시 §9물통§f으로 이동됩니다.", "§6§l│ §9물통§f은 §6레벨당 §fGUI 1줄씩 추가됩니다. §7(페이지 존재)", "§6§l│ §f특정 §6레벨 §f달성 시, §9물통§f 안에서 §a판매 §f가능", "",
                ChatColor.GRAY + "1/5"));
        this.offlinePlayer = player;
    }

    public void onGUI(Player player, int page) {
        //TODO 레벨업을 하면, 다음 페이지로 이동 함. 다음 페이지 값을 콘피그에 저장 후, 불러올 때 저장 시킴.

        level = getStatLevel(player, name);
        if (level >= 1) {

            Config newConfig = new Config("job/" + offlinePlayer.getUniqueId() + "/" + getDisplayName() + "/" + page);
            newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
            GUI newGUI = new GUI(newConfig);
            Inventory inv = newGUI.getInventory(String.valueOf(page));
            this.inv = inv;

            this.pre = level / 5;

            if (inv == null) {
                inv = Bukkit.createInventory(null, 54, "[1] 특별한 물통 레벨 : " + level);
                this.inv = inv;
            }


            for (double i = 9 * level; i < 45; i++) {
                items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), (int) i, inv);
            }
            setBarrier();

            items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);

            if (level > 5) {
                previousButton(inv);
            }

            player.openInventory(inv);
        }
    }

    public void nextButton(Inventory inv) {
        items.newItem("다음 페이지로", Material.ACACIA_SIGN, 1, List.of(""), nextSlot, inv);
    }

    public void previousButton(Inventory inv) {
        items.newItem("이전 페이지로", Material.ACACIA_SIGN, 1, List.of(""), previousSlot, inv);
    }

    public void setDefault(Player player) {

        path = path + player.getUniqueId();
        Config config = new Config("job/" + player.getUniqueId() + "/WaterBucket");

        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        config.getConfig().set("level", 0);
        config.getConfig().set("items", new ArrayList<>());
        config.saveConfig();
    }


    public void levelUp(Player player) {
        if (getStatPoint(player) != -1) {
            if (getStatPoint(player) < stat) {
                int tempLevel = level;
                int nextPage = level / 5;
                tempLevel -= nextPage * 5;

                int pre = nextPage + 1;
                this.pre = pre;
                ++level;

                InventoryUpdate.updateInventory(SkyExcelNetworkMain.getPlugin(), player, "[" + pre + "]" + getDisplayName() + " 레벨 : " + level);


                setStatLevel(player, "WaterBucket", level);

                // 기존에 있는 아이템을 지우는거
                for (ItemStack items : inv.getContents()) {
                    if (items != null) {
                        if (items.getType().equals(Material.BARRIER)) {
                            items.setAmount(0);
                        }
                    }
                }

                setBarrier();
                if (tempLevel == 0) { // 다음 페이지로 넘어가면 해당 페이지 GUI 저장
                    addPage(inv);

                } else { //레벨업을 누를때 해당 GUI를 저장
                    Config newConfig = new Config("job/" + offlinePlayer.getUniqueId() + "/" + getDisplayName() + "/" + page);
                    newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
                    GUI newGUI = new GUI(newConfig);
                    newGUI.saveInventory(String.valueOf(page), inv);
                }

                items.newItem("물병 레벨을 올리세요!", Material.EXPERIENCE_BOTTLE, 1, List.of(""), 53, inv);
                player.sendMessage("레벨업에 성공 하였습니다!");
            } else {
                player.sendMessage("스텟 포인트가 부족합니다!");
            }
        } else {
            player.sendMessage("스텟 포인트가 존재하지 않습니다!");
        }
    }


    public void previousPage() {
        Player player = offlinePlayer.getPlayer();

    }

    public void nextPage() {

    }

    public void setBarrier() {
        int tempLevel = level;
        int nextPage = level / 5;
        tempLevel -= nextPage * 5;


        for (int i = 9 * (tempLevel + 1); i < 45; i++) {
            slots.add(i);
            items.newItem("물병 레벨을 올리세요!", Material.BARRIER, 1, List.of(""), i, inv);
        }
    }


    public void saveInventory(int pre) {
        Config newConfig = new Config("job/" + offlinePlayer.getUniqueId() + "/" + getDisplayName() + "/" + page);
        newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
        GUI newGUI = new GUI(newConfig);
        newGUI.saveInventory(String.valueOf(page), inv);
    }

    public void addPage(Inventory inv) {
        ++page;
        inv.clear();
        Config newConfig = new Config("job/" + offlinePlayer.getUniqueId() + "/" + getDisplayName() + "/" + page);
        newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
        GUI newGUI = new GUI(newConfig);
        newGUI.saveInventory(String.valueOf(level), inv);
        previousButton(inv);
        setBarrier();
    }


    public int getPre() {
        return pre;
    }

    public int getNextSlot() {
        return nextSlot;
    }

    public int getPreviousSlot() {
        return previousSlot;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public Inventory getInv() {
        return inv;
    }
}
