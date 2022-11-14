package net.skyexcel.server.skyblock.event;


import net.skyexcel.server.skyblock.data.event.SkyBlockCreateEvent;
import net.skyexcel.server.skyblock.data.event.SkyBlockDeleteEvent;
import net.skyexcel.server.skyblock.data.event.SkyBlockJoinEvent;
import net.skyexcel.server.skyblock.data.event.SkyBlockQuickEvent;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.UUID;

public class SkyBlockEvent implements Listener {


    private double MIN_Y = -60;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDelete(SkyBlockDeleteEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("架 섬을 성공적으로 제거 하였습니다!");
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreate(SkyBlockCreateEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', event.getName()) + "家 섬 생성에 성공 하였습니다!");
        player.sendMessage("家 섬 채팅을 사용 하실 수 있습니다! ");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuick(SkyBlockQuickEvent event) {
        Player player = event.getPlayer();

        SkyBlockQuickEvent.CancelCause cause = event.getCancelCause();

        if (event.getCause().equals(SkyBlockQuickEvent.QuickCuase.ISLAND)) {
            if (cause != null) {
                switch (cause) {
                    case OWNER -> {
                        player.sendMessage("强 섬 주인은 탈퇴 할 수 없습니다!");
                    }
                    case NOT_MEMBER -> {
                        player.sendMessage("强 당신은 해당 섬의 멤버가 아닙니다!");
                    }
                }
            } else {
                SkyBlock skyBlock = event.getIslandData();
                skyBlock.removeMember(player);
                player.sendMessage("架 성공적으로 섬을 탈퇴하였습니다!");
            }
        } else if (event.getCause().equals(SkyBlockQuickEvent.QuickCuase.SERVER)) {
            List<String> members = event.getIslandData().getMembers();
            if (event.getIslandData().getMembers().contains(player.getUniqueId().toString())) {
                for (String member : members) {
                    Player online = Bukkit.getPlayer(member);
                    online.getPlayer().sendMessage("家 §6" + player.getPlayer().getDisplayName() + "§f님이 §a입장 §c퇴장§f하였습니다!");
                }

            }
        }
    }



    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(SkyBlockJoinEvent event) {
        Player player = event.getPlayer();
        SkyBlock data = event.getIslandData();
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

        if (event.getJoinCause().equals(SkyBlockJoinEvent.JoinCause.VISIT)) {

        }
        if (playerData.hasIsland()) {
            Player owner = Bukkit.getPlayer(UUID.fromString(data.getOwner()));

            if (data.getMembers() != null) {
                for (String uuid : data.getMembers()) {

                    Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                    if (!data.getMembers().contains(player.getUniqueId().toString()))
                        member.sendMessage("家 §6" + player.getDisplayName() + "§f님이 섬에 입장하였습니다!");
                }
            }

            if (owner != null) {
                if (!player.equals(owner)) {
                    owner.sendMessage("家 §6" + player.getDisplayName() + "§f님이 섬에 입장하였습니다!");
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Location location = event.getTo();

        if (location.getY() <= MIN_Y) {
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock skyBlock = new SkyBlock(playerData.getIsland());
            skyBlock.teleportSkyBlock(player);
        }
    }

    @EventHandler
    public void Farm(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Location location = player.getLocation();

    }

    @EventHandler
    public void damage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            event.setCancelled(true);
        }
    }
}
