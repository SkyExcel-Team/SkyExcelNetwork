package net.skyexcel.server.skyblock.event;


import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.data.event.SkyBlockCreateEvent;
import net.skyexcel.server.skyblock.data.event.SkyBlockJoinEvent;
import net.skyexcel.server.skyblock.data.event.SkyBlockQuickEvent;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.island.SkyBlockRecord;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import skyexcel.data.file.Config;

import java.util.List;
import java.util.UUID;

public class SkyBlockListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreate(SkyBlockCreateEvent event) {
        Player player = event.getPlayer();

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

        if (!equalFileName(event.getName())) {
            if (!playerData.hasIsland()) {

                SkyBlockRecord record = new SkyBlockRecord(event.getName());

                record.skyblockRecord(player, SkyBlockRecord.Type.CREATE);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "架 " + event.getName()) + " 이름의 섬 생성에 성공 하였습니다!");
                player.sendMessage("家" + ChatColor.GRAY + " 섬 채팅을 사용 하실 수 있습니다! ");

            } else {
                player.sendMessage("强 당신은 이미 섬이 있습니다!");
                event.setCancelled(true);
            }
        } else {
            player.sendMessage("强 이미 해당 섬의 이름이 있습니다");
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuick(SkyBlockQuickEvent event) {
        Player player = event.getPlayer();

        SkyBlockQuickEvent.CancelCause cause = event.getCancelCause();
        SkyBlock skyBlock = event.getIslandData();
        Player owner = Bukkit.getPlayer(UUID.fromString(skyBlock.getOwner()));

        if (event.getCause().equals(SkyBlockQuickEvent.QuickCuase.ISLAND)) {
            if (cause != null) {

                switch (cause) {
                    case OWNER -> {
                        player.sendMessage("强 섬 주인은 탈퇴 할 수 없습니다!");
                    }
                    case NOT_MEMBER -> {
                        player.sendMessage("强 당신은 해당 섬의 멤버가 아닙니다!");
                    }
                    case DEFAULT -> {
                        skyBlock.removeMember(player);
                        player.sendMessage("架 성공적으로 섬을 탈퇴하였습니다!");
                    }
                }
            }
        } else if (event.getCause().equals(SkyBlockQuickEvent.QuickCuase.SERVER)) {
            List<String> members = event.getIslandData().getMembers();


            if (event.getIslandData().getMembers().contains(player.getUniqueId().toString())) {
                for (String member : members) {
                    Player online = Bukkit.getPlayer(member);
                    if (online != null)
                        online.getPlayer().sendMessage("家 §6" + player.getPlayer().getDisplayName() + "§f님이 서버에서 §c퇴장§f하였습니다!");
                }

                System.out.println("test");
                if (owner.isOnline())
                    owner.sendMessage("家 §6" + player.getPlayer().getDisplayName() + "§f님이 서버에서 §c퇴장§f하였습니다!");
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(SkyBlockJoinEvent event) {
        Player player = event.getPlayer();
        SkyBlock data = event.getIslandData();

        System.out.println(event.getTarget());

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

        SkyBlockPlayerData target = new SkyBlockPlayerData(event.getTarget());

        SkyBlock targetData = new SkyBlock(target.getIsland());
        OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(targetData.getOwner()));


        if (data.getOwner() != null) {

            if (event.getName() != null) {
                if (target.hasIsland()) {
                    if (event.getCancelCause().equals(SkyBlockJoinEvent.CancelCause.DEFAULT)) {
                        if (event.getJoinCause().equals(SkyBlockJoinEvent.JoinCause.VISIT)) {
                            if (owner.isOnline())
                                owner.getPlayer().sendMessage(player.getDisplayName() + " 님이 섬에 방문 하였습니다.");

                            player.sendMessage("架 §6" + owner.getName() + "§f님의 섬을 방문 했습니다!");
                            targetData.spawn(player, targetData.getLocation());
                        }
                    }
                }
            }
        } else {
            player.sendMessage("强 해당 섬은 존재하지 않습니다!");
        }


        if (playerData.hasIsland()) {

            if ((event.getJoinCause().equals(SkyBlockJoinEvent.JoinCause.MEMBER))) {
                if (owner.isOnline())
                    owner.getPlayer().sendMessage("家 §6" + player.getDisplayName() + "§f님이 섬에 §e입장§f하였습니다!");
            }

            if (data.getMembers() != null) {
                for (String uuid : data.getMembers()) {
                    Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                    if (!data.getMembers().contains(player.getUniqueId().toString())) {
                        member.sendMessage("家 §6" + player.getDisplayName() + "§f님이 서버에 §e입장§f하였습니다!");
                    }
                }
            }
//            data.teleportSkyBlock(player, owner);

            if (owner != null) {
                if (!player.equals(owner)) {
                    if (owner.isOnline())
                        owner.getPlayer().sendMessage("家 §6" + player.getDisplayName() + "§f님이 서버 §e입장§f하였습니다!");
                }
            }


        } else if (event.getCancelCause().equals(SkyBlockJoinEvent.CancelCause.LOCK)) {
            player.sendMessage("强 해당 섬은 방문객 §a입장§f을 §c비활성화 §f하였습니다!");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Location location = event.getTo();

        double MIN_Y = -60;

        if (location.getY() <= MIN_Y) {
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            if (playerData.hasIsland()) {
                SkyBlock skyBlock = new SkyBlock(playerData.getIsland());
                skyBlock.teleportSkyBlock(player, Bukkit.getOfflinePlayer(skyBlock.getOwner()));
            }
        }
    }

    @EventHandler
    public void Farm(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Location location = player.getLocation();

    }


    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);
            if (skyBlockPlayerData.hasIsland()) {
                SkyBlock skyBlock = new SkyBlock(skyBlockPlayerData.getIsland());
                if (skyBlock.isInIsland(player)) {

                }
            }
        }
    }

    private boolean equalFileName(String name) {
        Config config = new Config("SkyBlock/");
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
        return config.fileListName().contains(name);
    }
}
