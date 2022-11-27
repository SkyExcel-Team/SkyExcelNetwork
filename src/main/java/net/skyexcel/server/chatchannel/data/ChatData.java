package net.skyexcel.server.chatchannel.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class ChatData {
    private OfflinePlayer offlinePlayer;

    private Config config;

    private ChatChannel chatChannel = ChatChannel.GLOBAL;


    public ChatData(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;

        this.config = new Config("chat/" + offlinePlayer.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void setChannel(Player player, ChatChannel chatChannel) {
        this.chatChannel = chatChannel;
        player.sendMessage("§a채팅 채널이 §f" + chatChannel.getName() + "§a로 설정되었습니다.");
        this.config.setString("channel", chatChannel.name());
    }

    public ChatChannel getChatChannel() {
        return config.getConfig().get("channel") != null ?
                ChatChannel.valueOf(config.getString("channel")) : ChatChannel.LOCAL;
    }
}
