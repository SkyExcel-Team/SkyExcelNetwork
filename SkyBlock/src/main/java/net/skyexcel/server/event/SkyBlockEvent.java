package net.skyexcel.server.event;

import net.skyexcel.server.data.event.SkyBlockCreateEvent;
import net.skyexcel.server.data.event.SkyBlockDeleteEvent;
import net.skyexcel.server.data.event.SkyBlockJoinEvent;
import net.skyexcel.server.data.event.SkyBlockQuickEvent;
import net.skyexcel.server.data.island.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.UUID;

public class SkyBlockEvent implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDelete(SkyBlockDeleteEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(ChatColor.GRAY + " 섬을 제거 하였습니다!");
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreate(SkyBlockCreateEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', event.getName()) + ChatColor.GREEN + " 섬 생성에 성공 하였습니다!");
        player.sendMessage(ChatColor.GRAY + "섬 채팅을 사용 하실 수 있습니다! ");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuick(SkyBlockQuickEvent event) {
        Player player = event.getPlayer();
        SkyBlockQuickEvent.CancelCause cause = event.getCancelCause();

        if (cause != null) {
            switch (cause) {
                case OWNER -> {
                    player.sendMessage("섬 주인은 탈퇴 할 수 없습니다!");
                }
                case NOT_MEMBER -> {
                    player.sendMessage("당신은 해당 섬의 멤버가 아닙니다!");
                }
            }
        } else {
            player.sendMessage("성공적으로 섬을 탈퇴 했습니당");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(SkyBlockJoinEvent event) {
        Player player = event.getPlayer();
        SkyBlock data = event.getIslandData();

        Player owner = Bukkit.getPlayer(UUID.fromString(data.getOwner()));

        if (data.getMembers() != null) {
            for (String uuid : data.getMembers()) {

                Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                if (!data.getMembers().contains(player.getUniqueId().toString()))
                    member.sendMessage(player.getDisplayName() + " 님이 섬 월드에 입장하였습니다!");
            }
        }

        if (owner != null) {
            if (!player.equals(owner)) {
                owner.sendMessage(player.getDisplayName() + " 님이 섬 월드에 입장하였습니다!");
            }
        }
    }
}
