package net.skyexcel.server.skyblock.data.island;

import net.skyexcel.server.skyblock.data.event.*;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;

import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVault;
import net.skyexcel.server.skyblock.util.world.WorldManager;
import net.skyexcel.server.warp.data.Warp;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;

import java.util.*;

public class SkyBlock extends SkyBlockMeta {

    private String name;

    private Config config;

    private SkyBlockVault vault;

    private String path;


    public SkyBlock(String name) {
        super("data/SkyBlock/SkyBlock/" + name + "/" + name);
        this.name = name;
        this.path = "data/SkyBlock/SkyBlock/" + name + "/" + name;

        config = new Config(path);
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
    }

    public SkyBlock(Player player, String name) {
        super("data/SkyBlock/SkyBlock/" + name + "/" + name);
        this.name = name;

        vault = new SkyBlockVault(player, name);
        config = new Config("data/SkyBlock/SkyBlock/" + name + "/" + name);
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
    }

    public void create(Player player) {
        SkyBlockPlayerData data = new SkyBlockPlayerData(player);
        SkyBlockCreateEvent event = new SkyBlockCreateEvent(name, this, player);

        if (!event.isCancelled()) {
            Bukkit.getPluginManager().callEvent(event);
            data.setName(name);
            vault.create();
            config.setString("SkyBlock.name", name);
            config.setString("SkyBlock.discord", "");
            config.getConfig().set("SkyBlock.worldtime", 0);

            config.getConfig().set("SkyBlock.level.level", 1);
            config.getConfig().set("SkyBlock.level.size", 50);

            config.getConfig().set("SkyBlock.level.member", 0);
            config.getConfig().set("SkyBlock.level.banblock", 0);
            config.getConfig().set("SkyBlock.level.stand", 0);
            config.getConfig().set("SkyBlock.level.hopper", 0);

            config.setString("SkyBlock.owner", player.getUniqueId().toString());

            config.getConfig().set("SkyBlock.blacklist", new ArrayList<>());
            config.getConfig().set("SkyBlock.member", new ArrayList<>());
            config.getConfig().set("SkyBlock.rule", new ArrayList<>());
            config.getConfig().set("SkyBlock.parttime.player", new ArrayList<>());
            config.getConfig().set("SkyBlock.parttime.money", new ArrayList<>());

            config.getConfig().set("SkyBlock.option.pvp", false);
            config.getConfig().set("SkyBlock.option.worldBorder", true);
            config.getConfig().set("SkyBlock.option.open", true);

            config.getConfig().set("SkyBlock.option.banblock.member", new ArrayList<>());
            config.getConfig().set("SkyBlock.option.banblock.parttime", new ArrayList<>());

            config.getConfig().set("SkyBlock.option.weather", WeatherType.CLEAR.name());

            // 1000 = day, noon = 6000 night = 13000 midnight = 18000
            config.getConfig().set("SkyBlock.option.time", 1000);

            config.saveConfig();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            if (playerData.getOriginLocation() == null) {
                if (SkyExcelNetworkSkyBlockMain.config.getConfig().get("location") == null) {
                    Location location = new Location(Bukkit.getWorld("SkyBlock"), 0.0D, 0.0D, 0.0D);
                    SkyExcelNetworkSkyBlockMain.config.setLocation("location", location);
                    WorldManager worldManager = new WorldManager();
                    worldManager.paste(location.getWorld(), location, "islands/Based/large.schem");

                    player.teleport(location);
                    this.config.setLocation("SkyBlock.location", location);
                    this.config.saveConfig();
                } else {
                    Location location = SkyExcelNetworkSkyBlockMain.config.getLocation("location");
                    if (location.getX() + 1000.0D <= 2.9999984E7D) {
                        location.add(1000.0D, 0.0D, 0.0D);
                        this.config.setLocation("SkyBlock.location", location);

                        WorldManager worldManager = new WorldManager();
                        worldManager.paste(location.getWorld(), location, "islands/Based/large.schem");
                        player.teleport(location);
                        SkyExcelNetworkSkyBlockMain.config.setLocation("location", location);
                        this.config.saveConfig();
                    }
                }
            } else {

                Location location = playerData.getOriginLocation();
                WorldManager worldManager = new WorldManager();
                worldManager.paste(location.getWorld(), location, "islands/Based/large.schem");

                player.teleport(location);
                this.config.saveConfig();
                playerData.getConfig().removeKey("island.loc");
            }

        }
    }

    public void remove(Player player) {

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        playerData.setOriginLocation(getLocation());

        Warp warp = new Warp("spawn");
        player.teleport(warp.getLocation());


        if (getLocation() != null) {
            if (!getMembers().isEmpty()) { //TODO 맴버가 있을 경우 모든 멤버를 스폰으로 텔레포트 시킨다.
                for (String uuid : getMembers()) {
                    OfflinePlayer members = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                    SkyBlockPlayerData memberData = new SkyBlockPlayerData(members);
                    memberData.getConfig().deleteFile();
                    if (members.isOnline()) {

                        members.getPlayer().teleport(warp.getLocation());
                        members.getPlayer().sendMessage("佳 §6섬장§f이 섬을 §c제거§f하여, 섬에서 자동 §c탈퇴§f되었습니다.");



                    }
                }
            } else if (!getPartTime().isEmpty()) {
                for (String uuid : getPartTime()) {
                    Player parttime = Bukkit.getPlayer(UUID.fromString(uuid));
                    if (parttime.isOnline()) {

                        parttime.teleport(warp.getLocation());
                        parttime.sendMessage("佳 §6섬장§f이 섬을 §c제거§f하여, 섬에서 자동 §c탈퇴§f되었습니다.");
                    }
                }
            }

            player.sendMessage("架 성공적으로 섬을 §c제거§f하였습니다!");

            org.bukkit.Location pos1 = getLocation(); //자신의 섬의 영역을 불러온다.
            org.bukkit.Location pos2 = getLocation(); //자신의 섬의 영역을 불러온다.
            int size = getSize() / 2;
            pos1.subtract(size, 63, size);
            pos2.add(size, 63, size);

            WorldManager.removeBlocks(pos1, pos2);

            playerData.getConfig().removeKey("island.name");

            Config folder = new Config("data/SkyBlock/SkyBlock/" + name);
            folder.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);

            folder.deleteDir("data/SkyBlock/SkyBlock/" + name + "/record");
            folder.deleteDir("data/SkyBlock/SkyBlock/" + name + "/");
        }
    }


    public void reset(Player player) {
        player.sendMessage("佳 섬 초기화를 진행합니다...");
    }


    public void time(Player player, long time) {


        for (Player online : player.getWorld().getPlayers()) {
            if (isInIsland(online))
                online.setPlayerTime(time, false);
        }
        player.setPlayerTime(time, false);
    }

    public void spawn(Player player, Location loc) {
        player.teleport(loc);
        if (isWorldBorder())
            SkyExcelNetworkSkyBlockMain.getWorldBorderApi().setBorder(player, 50, loc);
    }

    //Path = SkyBlock.option.open";
    public void setOpen(Player player, boolean is) {

        if (is) {
            player.sendMessage("架 이제 모든 사람이 섬을 방문할 수 있습니다!");
            config.setBoolean("SkyBlock.option.open", true);

            for (Player visitors : Bukkit.getWorld("SkyBlock").getPlayers()) {
                if (isInIsland(visitors)) {
                    teleportSkyBlock(visitors, Bukkit.getOfflinePlayer(getOwner()));
                }
            }

        } else {
            for (Player visitors : Bukkit.getWorld("SkyBlock").getPlayers()) {
                if (!getMembers().contains(visitors.getUniqueId().toString())) {
                    if (!getOwner().equalsIgnoreCase(visitors.getUniqueId().toString())) {
                        if (isInIsland(visitors)) {
                            visitors.sendMessage("强 해당 섬은 방문을 §6잠금§f하여 §a입장§f이 §c불가능§f합니다!");
                            Warp warp = new Warp("spawn");
                            visitors.teleport(warp.getLocation());
                        }
                    }
                }
            }
            player.sendMessage("架 이제 모든 사람이 섬에 방문하지 못합니다!");
            config.setBoolean("SkyBlock.option.open", false);
        }
    }


    public boolean isOpen() {
        return config.getConfig().getBoolean("SkyBlock.option.open");
    }


    public void quickSkyBlock(Player player) {
        SkyBlockQuickEvent event = new SkyBlockQuickEvent(name, this, player);

        if (!event.isCancelled()) {
            if (getOwner() != null) {

                if (getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {
                    event.setCancelCause(SkyBlockQuickEvent.CancelCause.OWNER);
                    event.setCause(SkyBlockQuickEvent.QuickCuase.ISLAND);
                } else {
                    if (!getMembers().isEmpty()) {
                        event.setCause(SkyBlockQuickEvent.QuickCuase.ISLAND);
                        if (getMembers().contains(player.getUniqueId().toString())) {
                            event.setCause(SkyBlockQuickEvent.QuickCuase.ISLAND);
                        } else {
                            event.setCancelCause(SkyBlockQuickEvent.CancelCause.NOT_MEMBER);
                            event.setCause(SkyBlockQuickEvent.QuickCuase.ISLAND);
                        }
                    }
                }

                Bukkit.getPluginManager().callEvent(event);
            }
        }
    }

    public void accept(Player player, Player target) {
        SkyBlockRecord record = new SkyBlockRecord(name);

        addMember(target);
        record.playerRecord(player, target, null, SkyBlockRecord.Type.JOIN);
    }


    public boolean kickMember(Player player, OfflinePlayer target, String reason) {
        SkyBlockKickEvent event = new SkyBlockKickEvent(name, this, player);

        if (!event.isCancelled()) {
            List<String> members = getMembers();
            if (members.contains(target.getUniqueId().toString()) && removeMember(target)) {
                SkyBlockRecord record = new SkyBlockRecord(name);

                record.playerRecord(player, target, reason, SkyBlockRecord.Type.KICK);

                SkyBlockPlayerData playerData = new SkyBlockPlayerData(target);
                playerData.setName("");
                Bukkit.getPluginManager().callEvent(event);
                return true;
            }
        }

        return false;
    }


    public int getSize() {
        return config.getConfig().getInt("SkyBlock.level.size");
    }


    public void visitSkyBlock(Player player, OfflinePlayer target) {

        SkyBlockJoinEvent event = new SkyBlockJoinEvent(name, this, player, target);

        if (name != null) {
            if (!event.isCancelled()) {
                if (!getMembers().contains(player.getUniqueId().toString())) {
                    event.setJoinCause(SkyBlockJoinEvent.JoinCause.ISLAND);

                    if (isOpen()) {
                        event.setJoinCause(SkyBlockJoinEvent.JoinCause.VISIT);
                    } else {
                        event.setCancelCause(SkyBlockJoinEvent.CancelCause.LOCK);
                    }
                } else {
                    event.setJoinCause(SkyBlockJoinEvent.JoinCause.MEMBER);
                }
                Bukkit.getPluginManager().callEvent(event);
            }
        } else {
            event.setCancelCause(SkyBlockJoinEvent.CancelCause.NONE);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public void teleportSkyBlock(Player player, OfflinePlayer target) {

        SkyBlockJoinEvent event = new SkyBlockJoinEvent(name, this, player, target);

        if (getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {
            event.setJoinCause(SkyBlockJoinEvent.JoinCause.OWNER);
        } else {
            event.setJoinCause(SkyBlockJoinEvent.JoinCause.MEMBER);
        }

        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            if (getSpawn() != null) {
                spawn(player, getLocation());
            }
        }

    }

    public void setVaultLock() {
        vault.setLock();
    }

    public void setDiscord(String link) {
        config.setString("SkyBlock.discord", link);
        config.saveConfig();
    }

    public boolean setOwner(Player owner) {

        if (config != null) {
            config.setString("SkyBlock.owner", owner.getUniqueId().toString());
            config.saveConfig();
            return true;
        }
        return false;
    }

    public void removeDiscord() {
        config.setString("SkyBlock.discord", "");
        config.saveConfig();
    }

    public boolean removePartTime(Player player) {

        if (config.getConfig().get("SkyBlock.parttime.player") != null && config.getConfig().get("SkyBlock.parttime.money") != null) {
            List<String> members = getPartTime();
            List<Integer> money = getPartMoney();

            members.add(player.getUniqueId().toString());
            config.getConfig().set("SkyBlock.parttime.player", members);
            config.getConfig().set("SkyBlock.parttime.money", money);

            config.saveConfig();
            return true;
        }
        return false;
    }

    public boolean addPartTime(Player player, int amount) {

        if (config.getConfig().get("SkyBlock.parttime.player") != null && config.getConfig().get("SkyBlock.parttime.money") != null) {
            List<String> members = getPartTime();
            List<Integer> money = getPartMoney();
            money.add(amount);
            members.add(player.getUniqueId().toString());
            config.getConfig().set("SkyBlock.parttime.player", members);
            config.getConfig().set("SkyBlock.parttime.money", money);

            config.saveConfig();
            return true;
        }
        return false;
    }

    public boolean addMember(Player player) {
        if (config != null) {
            List<String> members = getMembers();
            members.add(player.getUniqueId().toString());

            config.getConfig().set("SkyBlock.member", members);
            config.saveConfig();
            return true;
        }

        return false;
    }


    public boolean removeMember(OfflinePlayer player) {
        if (config != null) {
            List<String> members = getMembers();
            SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);
            skyBlockPlayerData.getConfig().removeKey("island.name");
            members.remove(player.getUniqueId().toString());

            config.getConfig().set("SkyBlock.member", members);
            config.saveConfig();
            return true;
        }

        return false;
    }

    public boolean addRule(String[] newRule) {
        if (config.getConfig().get("SkyBlock.rule") != null) {
            List<String> rule = config.getConfig().getStringList("SkyBlock.rule");
            if (rule.size() + 1 != 11) {
                rule.add(String.join(" ", Arrays.copyOfRange(newRule, 2, newRule.length)));
                config.getConfig().set("SkyBlock.rule", rule);
                config.saveConfig();
                return true;
            }
        }
        return false;
    }

    public boolean removeRule(int index) {
        try {
            if (config.getConfig().get("SkyBlock.rule") != null) {
                List<String> rule = config.getConfig().getStringList("SkyBlock.rule");
                if (rule.get(index) != null) {
                    rule.remove(index);
                    config.getConfig().set("SkyBlock.rule", rule);
                    config.saveConfig();
                }
                return true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        return false;
    }

    public boolean rename(String name) {
        if (!getMembers().isEmpty()) {
            for (String player : getMembers()) {
                SkyBlockPlayerData data = new SkyBlockPlayerData(Objects.requireNonNull(Bukkit.getPlayer(player)));
                data.setName(name);
            }
            String ownerUUID = getOwner();
            Player owner = Bukkit.getPlayer(UUID.fromString(ownerUUID));
            SkyBlockPlayerData data = new SkyBlockPlayerData(owner);
            data.setName(name);
            setName(name);
            path = "SkyBlock/" + name + "/" + name;
            config.renameFile(path);
        }
        config.saveConfig();
        return true;
    }

    public void setName(String name) {
        config.setString("SkyBlock.name", name);
    }

    public String getName() {
        return config.getString("SkyBlock.name");
    }

    public String getDiscord() {
        return config.getString("SkyBlock.discord");
    }

    public long getWorldTime() {
        return config.getLong("SkyBlock.worldtime");
    }

    public int getLevel() {
        return config.getInteger("SkyBlock.level.level");
    }

    public String getOwner() {

        return config.getConfig().getString("SkyBlock.owner");
    }

    public Location getSpawn() {

        String owner = getOwner();
        if (owner != null) {

            World world = Bukkit.getWorld(getOwner());
            return new Location(world, 0, 0, 0);
        }
        return null;
    }


    public void getRule(Player player) {

        List<String> rule = config.getConfig().getStringList("SkyBlock.rule");
        if (rule.size() == 0) {
            player.sendMessage("규칙이 없습니다!");
        }

        for (int i = 0; i < rule.size(); i++) {
            int index = i;
            player.sendMessage(++index + ". " + rule.get(i));
        }
    }

    public List<String> getMembers() {
        List<String> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("SkyBlock.member")) {
            members.add(name);
        }

        return members;
    }


    public boolean removeBlackList(OfflinePlayer player) {
        if (config != null) {
            List<String> members = getMembers();
            members.remove(player.getUniqueId().toString());
            config.getConfig().set("SkyBlock.blacklist", members);
            config.saveConfig();
            return true;
        }
        return false;
    }


    public boolean addBlackList(Player player, OfflinePlayer target, String reason) {
        if (config != null) {
            List<String> members = getBlackList();
            members.add(target.getUniqueId().toString());
            SkyBlockRecord record = new SkyBlockRecord(name);
            record.playerRecord(player, target, reason, SkyBlockRecord.Type.BLACKLIST);
            config.getConfig().set("SkyBlock.blacklist", members);
            config.saveConfig();
            return true;
        }
        return false;
    }

    public List<String> getBlackList() {
        return new ArrayList<>(config.getConfig().getStringList("SkyBlock.blacklist"));
    }

    public List<String> getPartTime() {
        List<String> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("SkyBlock.parttime.player")) {
            members.add(Bukkit.getPlayer(name).getDisplayName());
        }

        return members;
    }

    public List<Integer> getPartMoney() {
        List<Integer> members = new ArrayList<>();

        for (int name : config.getConfig().getIntegerList("SkyBlock.parttime.money")) {
            members.add(name);
        }

        return members;
    }


    public void removeBanBlockPartTime(Material material) {
        @NotNull List<String> members = config.getConfig().getStringList("SkyBlock.option.banblock.parttime");

        members.remove(material.name());

        config.getConfig().set("SkyBlock.option.banblock.member", members);
        config.saveConfig();
    }


    public void addBanBlockPartTime(Material material) {
        @NotNull List<String> members = config.getConfig().getStringList("SkyBlock.option.banblock.parttime");

        members.add(material.name());

        config.getConfig().set("SkyBlock.option.banblock.member", members);
        config.saveConfig();
    }


    public void removeBanBlockMember(Material material) {
        @NotNull List<String> members = config.getConfig().getStringList("SkyBlock.option.banblock.member");

        members.remove(material.name());

        config.getConfig().set("SkyBlock.option.banblock.member", members);
        config.saveConfig();
    }

    public void addBanBlockMember(Material material) {
        @NotNull List<String> members = config.getConfig().getStringList("SkyBlock.option.banblock.member");

        members.add(material.name());

        config.getConfig().set("SkyBlock.option.banblock.member", members);
        config.saveConfig();
    }

    public List<Material> getBanBlockMember() {
        List<Material> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("SkyBlock.option.banblock.member")) {
            members.add(Material.valueOf(name));
        }

        return members;
    }

    public List<Material> getBanBlockPartTime() {
        List<Material> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("SkyBlock.option.banblock.parttime")) {
            members.add(Material.valueOf(name));
        }

        return members;
    }

    public List<String> getVisitors() {
        List<String> visitors = new ArrayList<>();
        for (Player player : Objects.requireNonNull(Bukkit.getWorld("SkyBlock")).getPlayers()) {
            if (isInIsland(player) && !getMembers().contains(player.getUniqueId().toString())) {
                visitors.add(player.getUniqueId().toString());
            }
        }
        return visitors;
    }

    public void removeAll() {
        Config SkyBlockConfig = new Config("SkyBlock/");
        SkyBlockConfig.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);

        for (String name : SkyBlockConfig.fileListName()) {
            Config record = new Config("SkyBlock/" + name + "/record/");
            for (String records : record.fileListName()) {
                Config recordConfig = new Config("SkyBlock/" + name + "/record/" + records);
                recordConfig.deleteFile();
            }
        }
    }


    public SkyBlockVault getVault() {
        return vault;
    }
}
