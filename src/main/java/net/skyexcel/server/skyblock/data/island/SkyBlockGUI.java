package net.skyexcel.server.skyblock.data.island;

import net.skyexcel.server.giftbox.util.Items;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class SkyBlockGUI {

    private Inventory inv;

    public void memberGUI(Player player) {
        SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);
        SkyBlock skyBlock = new SkyBlock(skyBlockPlayerData.getIsland());
        if(skyBlockPlayerData.isOwner()){
            Inventory inv = Bukkit.createInventory(null, 45, "섬원 목록 GUI");

            List<String> names = skyBlock.getMembers();

            for (String name : skyBlock.getMembers()) {
                int index = names.indexOf(name);
                Items.playerSkull(Bukkit.getOfflinePlayer(name).getName(), "", List.of("(쉬프트 + 클릭)"), index, inv);
            }

            this.inv = inv;
            player.openInventory(inv);
        } else{
            player.sendMessage("당신은 섬원을 관리할 권한이 없습니다.");
        }
    }

    public Inventory getInv() {
        return inv;
    }
}
