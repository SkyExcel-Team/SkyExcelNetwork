package net.skyexcel.server.chatchannel.gui;

import lombok.Getter;
import lombok.Setter;
import net.skyexcel.api.packet.Inventory.InventoryUpdate;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.chatchannel.data.ChatLog;
import net.skyexcel.server.chatchannel.data.ChatPlayerData;
import net.skyexcel.server.giftbox.gui.NextButton;
import net.skyexcel.server.giftbox.gui.PreviousButton;
import org.bukkit.Bukkit;


import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


import java.util.List;

public class ChatLogGUI {
    private OfflinePlayer offlinePlayer;

    private ChatLog chatLog;

    private int page = 1;

    private Inventory inv;

    @Getter
    @Setter
    private ClickType clickType = ClickType.DEFAULT;
    @Getter
    private final int NEXT_PAGE = 50;

    @Getter
    private final int PREVIOUS_PAGE = 48;
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

        //TODO : 처음에 여는거와 이전페이지로 여는것을 인식해야함.
        if (clickType == ClickType.PREVIOUS_PAGE || clickType == ClickType.DEFAULT) {
            page--;
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
            new PreviousButton().setInventory(PREVIOUS_PAGE, inv);
        } else {
            player.sendMessage("다음 페이지가 존재하지 않습니다.");
        }
    }

    public enum ClickType {
        NEXT_PAGE,
        PREVIOUS_PAGE, DEFAULT
    }
}
