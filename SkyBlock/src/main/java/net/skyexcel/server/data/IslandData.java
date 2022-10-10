package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IslandData {

    private String name;

    private Config config;


    private Vault vault;


    public IslandData(String name) {
        this.name = name;

        vault = new Vault(name);
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


    public void setDiscord(String link) {
        config.setString("island.discord", link);
        config.saveConfig();
    }

    public void removeDiscord() {
        config.setString("island.discord", "");
        config.saveConfig();
    }

    public boolean onQuit(Player player) {
        List<OfflinePlayer> members = getMembers();
        if (members.contains(player)) {
            for (OfflinePlayer member : members) {
                if (member.isOnline()) {
                    member.getPlayer().sendMessage(player.getPlayer().getDisplayName() + " 님이 입장 퇴장하였습니다!");
                }
            }
            return true;
        }
        return false;
    }

    public boolean onJoin(Player player) {
        List<OfflinePlayer> members = getMembers();
        if (members.contains(player)) {
            for (OfflinePlayer member : members) {
                if (member.isOnline()) {
                    member.getPlayer().sendMessage(player.getPlayer().getDisplayName() + " 님이 입장 하였습니다!");
                }
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

    public void addMember(Player player) {

        List<OfflinePlayer> members = getMembers();
        members.add(player);

        config.getConfig().set("island.member", members);
        config.saveConfig();
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
            for (OfflinePlayer player : getMembers()) {
                PlayerData data = new PlayerData(Objects.requireNonNull(player.getPlayer()));
                data.setName(name);
            }
            config.renameFile("island/" + name + "/" + name);
            config.saveConfig();
            return true;
        } else {
            config.moveTo("island/" + name + "/" + name);

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

    public OfflinePlayer getOwner() {
        return Bukkit.getOfflinePlayer(config.getString("island.owner"));
    }


    public Location getSpawn() {
        return config.getLocation("island.spawn");
    }

    public List<String> getRule() {

        return config.getConfig().getStringList("island.rule");

    }

    public List<OfflinePlayer> getMembers() {
        List<OfflinePlayer> members = new ArrayList<>();

        for (String name : config.getConfig().getStringList("island.member")) {
            members.add(Bukkit.getOfflinePlayer(name));
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


    private static enum Type {
        DEPOSIT, WITHDRAW
    }
}
