package net.skyexcel.server.chatchannel.gui;

import lombok.Getter;
import net.skyexcel.api.packet.Inventory.InventoryUpdate;
import net.skyexcel.api.util.Translate;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.chatchannel.data.ChatLog;
import net.skyexcel.server.chatchannel.data.ChatPlayerData;
import net.skyexcel.server.giftbox.gui.NextButton;
import net.skyexcel.server.giftbox.gui.PreviousButton;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ChatLogGUI {
    private OfflinePlayer offlinePlayer;

    private ChatLog chatLog;

    private int page = 1;

    private Inventory inv;

    @Getter
    private final int NEXT_PAGE = 50;

    private final int MAX = 44;


    public ChatLogGUI(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        if (ChatPlayerData.chatLogMap.containsKey(offlinePlayer.getUniqueId())) {
            chatLog = ChatPlayerData.chatLogMap.get(offlinePlayer.getUniqueId());
        } else {
            chatLog = new ChatLog(offlinePlayer);
            this.chatLog.load();
        }


    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, offlinePlayer.getName() + " 님의 채팅 로그 " + page + " 페이지");
        int i = -1;

        for (String log : chatLog.getLogs()) {
            i++;
            new LogButton(log).setInventory(i, inv);
            if (i == 44) break;
        }

        if (chatLog.getLogs().size() > 44 * page) {
            new NextButton().setInventory(NEXT_PAGE, inv);
        }

        player.openInventory(inv);
        this.inv = inv;
    }

    public void nextPage(Player player) {
        if (chatLog.getLogs().size() > 44 * page) {
            page++;

            inv.clear();
            InventoryUpdate.updateInventory(SkyExcelNetworkMain.getPlugin(), player, offlinePlayer.getName() + " 님의 채팅 로그 " + page + " 페이지");
            int i = -1;
            for (String log : chatLog.getLogs()) {
                i++;
                new LogButton(log).setInventory(i, inv);
                if (i == chatLog.getLogs().size() / MAX) break;
            }
            new PreviousButton().setInventory(48, inv);
        } else {
            player.sendMessage("다음 페이지가 존재하지 않습니다.");
        }
    }
}
