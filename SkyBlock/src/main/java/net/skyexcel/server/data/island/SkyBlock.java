package net.skyexcel.server.data.island;

import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.event.*;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.data.vault.SkyBlockVault;
import net.skyexcel.server.util.world.WorldManager;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;
import skyexcel.data.location.Region;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SkyBlock {

    private String name;

    private Config config;

    private SkyBlockVault vault;

    private Location location;

    public SkyBlock(String name) {
        this.name = name;

        config = new Config("SkyBlock/" + name + "/" + name);
        config.setPlugin(SkyBlockCore.plugin);
    }

    public SkyBlock(Player player, String name) {
        this.name = name;

        vault = new SkyBlockVault(player, name);
        config = new Config("SkyBlock/" + name + "/" + name);
        config.setPlugin(SkyBlockCore.plugin);
    }

    public void create(Player player) throws InvocationTargetException {

        SkyBlockPlayerData data = new SkyBlockPlayerData(player);
        SkyBlockCreateEvent event = new SkyBlockCreateEvent(name, this, player);

        if (!event.isCancelled()) {

            if (!data.hasIsland()) {

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

                config.getConfig().set("SkyBlock.option.pvp", true);
                config.getConfig().set("SkyBlock.option.wb", true);
                config.getConfig().set("SkyBlock.option.open", true);

                config.getConfig().set("SkyBlock.option.banblock.member", new ArrayList<>());
                config.getConfig().set("SkyBlock.option.banblock.parttime", new ArrayList<>());

                config.getConfig().set("SkyBlock.option.weather", WeatherType.CLEAR.name());

                // 1000 = day, noon = 6000 night = 13000 midnight = 18000
                config.getConfig().set("SkyBlock.option.time", 1000);

                config.saveConfig();

                if (SkyBlockCore.config.getConfig().get("location") == null) {
                    Location location = new Location(Bukkit.getWorld("SkyBlock"), 0.0D, 0.0D, 0.0D);
                    SkyBlockCore.config.setLocation("location", location);
                    WorldManager worldManager = new WorldManager();
                    worldManager.paste(location.getWorld(), location, "islands/Based/large.schem");
                    this.config.setLocation("SkyBlock.location", location);
                    this.config.saveConfig();
                } else {
                    Location location = SkyBlockCore.config.getLocation("location");
                    if (location.getX() + 1000.0D <= 2.9999984E7D) {
                        location.add(1000.0D, 0.0D, 0.0D);
                        this.config.setLocation("SkyBlock.location", location);
                        WorldManager worldManager = new WorldManager();
                        worldManager.paste(location.getWorld(), location, "islands/Based/large.schem");
                        SkyBlockCore.config.setLocation("location", location);
                        this.config.saveConfig();
                    }
                }
            } else {
                player.sendMessage("섬을 탈퇴 하거나, 삭제 하세요!");
            }
        }
    }

    public void remove(Player player) {

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));

        if (getLocation() != null) {
            if (!getMembers().isEmpty()) { //TODO 맴버가 있을 경우 모든 멤버를 스폰으로 텔레포트 시킨다.
                for (String uuid : getMembers()) {
                    Player members = Bukkit.getPlayer(UUID.fromString(uuid));
                    SkyBlockPlayerData memberData = new SkyBlockPlayerData(members);
                    memberData.getConfig().deleteFile();
                    if (members.isOnline()) {
                        members.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
                        members.sendMessage("당신의 섬이 지워졌습니다!");
                    }
                }
            } else if (!getPartTime().isEmpty()) {
                for (String uuid : getPartTime()) {
                    Player parttime = Bukkit.getPlayer(UUID.fromString(uuid));
                    if (parttime.isOnline()) {
                        parttime.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
                        parttime.sendMessage("당신의 섬이 지워졌습니다!");

                    }
                }
            }

            org.bukkit.Location pos1 = getLocation(); //자신의 섬의 영역을 불러온다.
            org.bukkit.Location pos2 = getLocation(); //자신의 섬의 영역을 불러온다.
            int size = getSize() / 2;
            pos1.subtract(size, 63, size);
            pos2.add(size, 63, size);

            WorldManager.removeBlocks(pos1, pos2);

            config.deleteFile();
            playerData.getConfig().deleteFile();
        }
    }


    public void time(Player player, long time) {

        List<String> members = getMembers();

        for (String name : members) {
            Player online = Bukkit.getPlayer(UUID.fromString(name));
            if (online != null)
                online.setPlayerTime(time, false);
        }
        player.setPlayerTime(time, false);
    }

    public void spawn(Player player, Location loc) {
        player.teleport(loc);
        SkyBlockCore.getWorldBorderApi().setBorder(player, 50, loc);
    }


    public Location getLocation() {
        return config.getLocation("SkyBlock.location");
    }

    public boolean isPvp() {
        return config.getConfig().getBoolean("SkyBlock.option.pvp");
    }


    public boolean isOpen() {
        return config.getConfig().getBoolean("SkyBlock.option.open");
    }


    public boolean isWorldBorder() {
        return config.getConfig().getBoolean("SkyBlock.option.wb");
    }

    public void setWorldBorder(boolean is) {
        config.setBoolean("SkyBlock.option.wb", is);
    }

    public WeatherType getWeather() {
        return WeatherType.valueOf(config.getConfig().getString("SkyBlock.option.weather"));
    }

    public void worldCreate(Player player) {
        WorldManager world = new WorldManager();
        SkyBlockCreateEvent event = new SkyBlockCreateEvent(name, this, player);

        if (!event.isCancelled()) {
            SkyBlockRecord record = new SkyBlockRecord(name);
            event.setType(SkyBlockCreateEvent.Type.SMALL);
            record.skyblockRecord(player, event.getType().getName(), SkyBlockRecord.Type.CREATE);
            world.create(player, 0);

            config.setLocation("SkyBlock.spawn", new Location(world.getWorld(), 0, 0, 0));

            config.saveConfig();

            Bukkit.getPluginManager().callEvent(new SkyBlockCreateEvent(name, this, player));
        }

    }

    public void reset(Player player) {
        delete(player);

    }

    public void quickSkyBlock(Player player) {
        SkyBlockQuickEvent event = new SkyBlockQuickEvent(name, this, player);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            List<String> members = getMembers();

            if (!getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {

                if (members.contains(player.getUniqueId().toString())) {
                    event.setCancelCause(SkyBlockQuickEvent.CancelCause.NOT_MEMBER);
                } else {
                    Bukkit.getPluginManager().callEvent(event);
                }
            } else {
                event.setCancelCause(SkyBlockQuickEvent.CancelCause.OWNER);

            }
        }
    }

    public void accept(Player player, Player target) {

        SkyBlockRecord record = new SkyBlockRecord(name);

        addMember(target);
        record.playerRecord(player, target, null, SkyBlockRecord.Type.JOIN);

    }

    public void enableWorldBorder(Player player) {

        Location origin = getLocation();

        Location pos1 = origin.add(25, -60, 25);


        Location pos2 = origin.add(-25, 256, -25);


        Region region = new Region(pos1, pos2);


        for (Player players : origin.getWorld().getPlayers()) {
            if (region.locationIsInRegion(players.getLocation())) {
                players.sendMessage("지금 당신의 섬에 있음 " + getName());
            }
        }
    }

    public void setWorldBorderVisibilty(Player player) {
        if (isWorldBorder()) {
            if (!getMembers().isEmpty()) {
                SkyBlockCore.getWorldBorderApi().resetWorldBorderToGlobal(player);
            } else {
                for (String uuid : getMembers()) {
                    player.sendMessage(uuid);
                    Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                    SkyBlockCore.getWorldBorderApi().resetWorldBorderToGlobal(member);
                }
            }
            setWorldBorder(false);
        } else {
            int size = getSize();
            if (!getMembers().isEmpty()) {
                SkyBlockCore.getWorldBorderApi().setBorder(player, size, getLocation());
            } else {
                for (String uuid : getMembers()) {
                    Player member = Bukkit.getPlayer(UUID.fromString(uuid));
                    SkyBlockCore.getWorldBorderApi().setBorder(member, size, getLocation());
                }
            }
            setWorldBorder(true);
        }
    }

    public void setWeather(Player player, String weather) {
        player.setPlayerWeather(WeatherType.DOWNFALL);

        player.getLocation().getWorld().setBiome(player.getLocation(), Biome.SNOWY_BEACH);
    }


    public boolean kickMember(Player player, Player target, String reason) {
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

    public void deleteSkyblock(Player player) {

        SkyBlockKickEvent event = new SkyBlockKickEvent(name, this, player);
        Bukkit.getPluginManager().callEvent(event);

        Location location = getLocation();

        int size = getSize();

    }

    public int getSize() {
        return config.getConfig().getInt("SkyBlock.level.size");
    }

    public boolean teleportSkyBlock(Player player) {

        SkyBlockJoinEvent event = new SkyBlockJoinEvent(name, this, player);
        event.setJoinCause(SkyBlockJoinEvent.JoinCause.MEMBER);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            if (getSpawn() != null) {

                spawn(player, getLocation());

                return true;
            }
        }

        return false;
    }

    public boolean setVaultLock() {
        vault.setLock(true);
        return true;
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

    public boolean onQuit(Player player) {
        List<String> members = getMembers();

        if (members.contains(player.getUniqueId().toString())) {
            for (String member : members) {

                Player online = Bukkit.getPlayer(member);
                online.getPlayer().sendMessage(player.getPlayer().getDisplayName() + " 님이 입장 퇴장하였습니다!");

            }
            return true;
        }
        return false;
    }

    public boolean onJoin(Player player) {
        List<String> members = getMembers();

        if (members.contains(player.getUniqueId().toString()) || getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {
            for (String member : members) {


                Player online = Bukkit.getPlayer(member);
                online.getPlayer().sendMessage(player.getPlayer().getDisplayName() + " 님이 입장 하였습니다!");

            }
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


    public boolean removeMember(Player player) {
        if (config != null) {
            List<String> members = getMembers();
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
        if (getMembers() != null) {

            for (String player : getMembers()) {
                SkyBlockPlayerData data = new SkyBlockPlayerData(Objects.requireNonNull(Bukkit.getPlayer(player)));
                data.setName(name);
            }

            config.saveConfig();
            return true;
        } else {


            config.saveConfig();
            return true;
        }
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


    public boolean removeBlackList(Player player) {
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

    public boolean isInIsland(Player player) {


        if (!player.getWorld().getName().equalsIgnoreCase("world")) {
            if (getLocation() != null) {

                int size = getSize();
                org.bukkit.Location pos1 = getLocation(); //자신의 섬의 영역을 불러온다.
                pos1.add(size, 256, size);

                org.bukkit.Location pos2 = getLocation();


                size = -1 * size;
                pos2.add(size, -256, size);

                Region region = new Region(pos1, pos2);
                return region.locationIsInRegion(player.getLocation());
            }
        }
        return false;
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

    public void delete(Player player) {
        SkyBlockDeleteEvent skyBlockDeleteEvent = new SkyBlockDeleteEvent(name, this, player);


        Bukkit.getPluginManager().callEvent(skyBlockDeleteEvent);

        for (Player online : Objects.requireNonNull(Bukkit.getWorld("skyblock")).getPlayers()) {
            if (isInIsland(online)) {
                online.sendMessage("test");
                online.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
            }
        }
    }

    public boolean equalFileName(String name) {
        Config config = new Config("SkyBlock/");
        config.setPlugin(SkyBlockCore.plugin);
        return config.fileListName().contains(name);
    }

    public void removeAll() {
        Config SkyBlockConfig = new Config("SkyBlock/");
        SkyBlockConfig.setPlugin(SkyBlockCore.plugin);

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
