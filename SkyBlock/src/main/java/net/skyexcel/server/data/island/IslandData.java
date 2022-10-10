package net.skyexcel.server.data.island;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.player.PlayerData;
import net.skyexcel.server.data.player.Request;
import net.skyexcel.server.data.vault.Vault;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class IslandData {

    private String name;

    private Config config;


    private Vault vault;


    public IslandData(String name) {
        this.name = name;


        config = new Config("island/" + name + "/" + name);
        config.setPlugin(SkyExcelNetwork.plugin);

    }

    public IslandData(Player player, String name) {
        this.name = name;

        vault = new Vault(player, name);
        config = new Config("island/" + name + "/" + name);
        config.setPlugin(SkyExcelNetwork.plugin);

    }

    public void create(Player player) {

        vault.create();

        config.setString("island.name", name);
        config.setString("island.discord", "");
        config.setLong("island.worldtime", 0);
        config.setLong("island.level", 0);

        config.setString("island.owner", player.getUniqueId().toString());

        config.getConfig().set("island.member", new ArrayList<>());
        config.getConfig().set("island.rule", new ArrayList<>());
        config.getConfig().set("island.parttime.player", new ArrayList<>());
        config.getConfig().set("island.parttime.money", new ArrayList<>());
        config.getConfig().set("island.option", new ArrayList<>());

        config.setLocation("island.spawn", new Location(Bukkit.getWorld("world"), 1, 1, 1));

        config.getConfig().set("island.banblock.parttime", new ArrayList<>());
        config.getConfig().set("island.banblock.member", new ArrayList<>());
        config.saveConfig();
    }

    public void quickIsland(Player player) {

        List<String> members = getMembers();

        if (!getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {

            if (members.contains(player.getUniqueId().toString())) {
                player.sendMessage("당신은 해당 섬의 멤버가 아닙니다!");
            } else {
                player.sendMessage("성공적으로 섬을 탈퇴 했습니당");
            }
        } else {
            player.sendMessage("섬 주인은 탈퇴 못함 ㅅㄱ");
        }
    }

    public boolean accept(Player player, Player target) {

        IslandRecord record = new IslandRecord(name);

        Request request = new Request();

        request.newRequest();

        record.record(player, target, null, IslandRecord.Type.JOIN);

        return false;
    }

    public boolean deny(Player target) {

        return false;
    }

    public boolean kickMember(Player player, Player target, String reason) {
        if (getMembers().contains(target.getUniqueId().toString())) {
            IslandRecord record = new IslandRecord(name);

            record.record(player, target, reason, IslandRecord.Type.KICK);
            return true;
        }
        return false;
    }

    public boolean invite(Player player, Player target) {


        return true;
    }

    public boolean delete() {
        return config.delete();
    }


    public boolean teleportIsland(Player player) {

        if (getSpawn() != null) {
            Location loc = getSpawn();
            player.teleport(loc);
            return true;
        }
        return false;
    }

    public boolean setVaultLock() {
        vault.setLock(true);
        return true;
    }

    public void setDiscord(String link) {
        config.setString("island.discord", link);
        config.saveConfig();
    }

    public boolean setOwner(Player owner) {

        if (config != null) {
            config.setString("island.owner", owner.getUniqueId().toString());
            config.saveConfig();
            return true;
        }
        return false;
    }

    public void removeDiscord() {
        config.setString("island.discord", "");
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

        if (members.contains(player.getUniqueId().toString())) {
            for (String member : members) {

                Player online = Bukkit.getPlayer(member);
                online.getPlayer().sendMessage(player.getPlayer().getDisplayName() + " 님이 입장 하였습니다!");
            }
            return true;
        }
        return false;
    }

    public boolean addPartTime(Player player, int amount) {

        if (config.getConfig().get("island.parttime.player") != null && config.getConfig().get("island.parttime.money") != null) {
            List<String> members = getPartTime();
            List<Integer> money = getPartMoney();
            money.add(amount);
            members.add(player.getUniqueId().toString());
            config.getConfig().set("island.parttime.player", members);
            config.getConfig().set("island.parttime.money", money);

            config.saveConfig();
            return true;
        }
        return false;
    }

    public boolean addMember(Player player) {
        if (config != null) {
            List<String> members = getMembers();
            members.add(player.getUniqueId().toString());

            config.getConfig().set("island.member", members);
            config.saveConfig();
            return true;
        }

        return false;
    }

    public boolean addRule(String newRule) {
        if (config.getConfig().get("island.rule") != null) {
            List<String> rule = config.getConfig().getStringList("island.rule");
            if (rule.size() != 11) {
                rule.add(newRule);
                config.getConfig().set("island.rule", rule);
                config.saveConfig();
                return true;
            }
        }
        return false;
    }

    public boolean removeRule(int index) {
        if (config.getConfig().get("island.rule") != null) {
            List<String> rule = config.getConfig().getStringList("island.rule");
            rule.remove(index);
            config.getConfig().set("island.rule", rule);
            config.saveConfig();
            return true;
        }
        return false;
    }

    public boolean rename(String name) {
        if (getMembers() != null) {
            for (String player : getMembers()) {
                PlayerData data = new PlayerData(Bukkit.getPlayer(player));
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
        return config.getString("island.name");
    }

    public String getDiscord() {
        return config.getString("island.discord");
    }

    public long getWorldTime() {
        return config.getLong("island.worldtime");
    }

    public int getLevel() {
        return config.getInteger("island.level");
    }

    public String getOwner() {
        return config.getString("island.owner");
    }


    public Location getSpawn() {
        return config.getLocation("island.spawn");
    }

    public List<String> getRule() {

        return config.getConfig().getStringList("island.rule");

    }

    public List<String> getMembers() {
        List<String> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("island.member")) {
            members.add(name);
        }

        return members;
    }


    public List<String> getPartTime() {
        List<String> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("island.parttime.player")) {
            members.add(Bukkit.getOfflinePlayer(name).getPlayer().getDisplayName());
        }

        return members;
    }

    public List<Integer> getPartMoney() {
        List<Integer> members = new ArrayList<>();

        for (int name : config.getConfig().getIntegerList("island.parttime.money")) {
            members.add(name);
        }

        return members;
    }


    public List<Material> getBanBlockMember() {
        List<Material> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("island.banblock.member")) {
            members.add(Material.valueOf(name));
        }

        return members;
    }

    public List<Material> getBanBlockPartTime() {
        List<Material> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("island.banblock.parttime")) {
            members.add(Material.valueOf(name));
        }

        return members;
    }

    public void delete(Player player) {

        config.deleteFile();
        player.sendMessage("제거 하였습니다!");
    }

    public Vault getVault() {
        return vault;
    }
}
