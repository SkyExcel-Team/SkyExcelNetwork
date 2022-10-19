package net.skyexcel.server.data.island;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.event.*;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.data.vault.SkyBlockVault;
import net.skyexcel.server.util.world.WorldManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;

import java.util.*;

public class SkyBlock {

    private String name;

    private Config config;

    private SkyBlockVault vault;

    public SkyBlock(String name) {
        this.name = name;

        config = new Config("SkyBlock/" + name + "/" + name);
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public SkyBlock(Player player, String name) {
        this.name = name;

        String[] split = name.split("&", 1);
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }


        vault = new SkyBlockVault(player, name);
        config = new Config("SkyBlock/" + name + "/" + name);
        config.setPlugin(SkyExcelNetwork.plugin);

    }

    public void create(Player player) {

        SkyBlockPlayerData data = new SkyBlockPlayerData(player);
        SkyBlockCreateEvent event = new SkyBlockCreateEvent(name, this, player);

        SkyBlockRecord record = new SkyBlockRecord(name);

        if (!event.isCancelled()) {
            if (data.getIsland() == null) {

                data.setName(name);

                vault.create();

                config.setString("SkyBlock.name", name);
                config.setString("SkyBlock.discord", "");
                config.getConfig().set("SkyBlock.worldtime", 0);

                config.getConfig().set("SkyBlock.level.level", 0);
                config.getConfig().set("SkyBlock.level.size", 0);
                config.getConfig().set("SkyBlock.level.member", 0);
                config.getConfig().set("SkyBlock.level.banblock", 0);
                config.getConfig().set("SkyBlock.level.stand", 0);
                config.getConfig().set("SkyBlock.level.hopper", 0);

                config.setString("SkyBlock.owner", player.getUniqueId().toString());

                config.getConfig().set("SkyBlock.member", new ArrayList<>());
                config.getConfig().set("SkyBlock.rule", new ArrayList<>());

                config.getConfig().set("SkyBlock.parttime.player", new ArrayList<>());
                config.getConfig().set("SkyBlock.parttime.money", new ArrayList<>());

                config.getConfig().set("SkyBlock.option.pvp", false);
                config.getConfig().set("SkyBlock.option.open", true);
                config.getConfig().set("SkyBlock.option.banblock.member", new ArrayList<>());
                config.getConfig().set("SkyBlock.option.banblock.parttime", new ArrayList<>());
                config.getConfig().set("SkyBlock.option.weather", WeatherType.CLEAR.name());

                // 1000 = day, noon = 6000 night = 13000 midnight = 18000
                config.getConfig().set("SkyBlock.option.time", 1000);

                WorldManager world = new WorldManager();


                event.setType(SkyBlockCreateEvent.Type.SMALL);
                record.skyblockRecord(player, event.getType().getName(), SkyBlockRecord.Type.CREATE);
                world.create(player, 0);

                config.setLocation("SkyBlock.spawn", new Location(world.getWorld(), 0, 0, 0));

                config.saveConfig();

                Bukkit.getPluginManager().callEvent(new SkyBlockCreateEvent(name, this, player));
            } else {
                player.sendMessage("섬을 탈퇴 하거나, 삭제 하세요!");
            }
        }
    }

    public void reset(Player player) {
        delete(player);
        create(player);
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

    public boolean delete() {
        return config.delete();
    }


    public boolean teleportSkyBlock(Player player) {
        SkyBlockJoinEvent event = new SkyBlockJoinEvent(name, this, player);

        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            if (getSpawn() != null) {

                Location loc = getSpawn();
                player.teleport(loc);


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
        return config.getInteger("SkyBlock.level");
    }

    public String getOwner() {
        return config.getConfig().getString("SkyBlock.owner");
    }


    public Location getSpawn() {
        WorldCreator worldCreator = new org.bukkit.WorldCreator(getOwner());
        World world = worldCreator.createWorld();

        return new Location(world, 0, 0, 0);
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


    public List<String> getPartTime() {
        List<String> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("SkyBlock.parttime.player")) {
            members.add(Bukkit.getOfflinePlayer(name).getPlayer().getDisplayName());
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

    public void delete(Player player) {
        SkyBlockDeleteEvent skyBlockDeleteEvent = new SkyBlockDeleteEvent(name, this, player);
        Bukkit.getPluginManager().callEvent(new SkyBlockDeleteEvent(name, this, player));

        if (!skyBlockDeleteEvent.isCancelled()) {
            WorldManager world = new WorldManager();
            world.delete(player);
            config.deleteFile();

        }
    }


    public void removeAll() {
        Config SkyBlockConfig = new Config("SkyBlock/");
        SkyBlockConfig.setPlugin(SkyExcelNetwork.plugin);

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
