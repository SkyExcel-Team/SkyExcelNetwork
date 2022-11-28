package net.skyexcel.api.hook;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.query.QueryOptions;
import net.luckperms.api.track.Track;
import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class LuckPermHook {

    private final LuckPerms luckPerms = Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(LuckPerms.class)).getProvider();

    public Group getGroup(String groupName) {
        return luckPerms.getGroupManager().getGroup(groupName);
    }

    public Track getTrack(String trackName) {
        return luckPerms.getTrackManager().getTrack(trackName);
    }

    public void promote(Player player) {
        User user = getUser(player.getUniqueId());
        ImmutableContextSet set = ImmutableContextSet.builder().build();
        getTrack("main").promote(user, set);
    }

    public void isAdmin(Player who) {

        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(who);
        user.setPrimaryGroup("test");

    }

    public void addPermission(UUID userUuid, String permission) {
        // Load, modify, then save
        luckPerms.getUserManager().modifyUser(userUuid, user -> {
            // Add the permission
            user.data().add(Node.builder(permission).build());
        });
    }

    public void rankUp(Player player, String curr, String next) {
        User user = getUser(player.getUniqueId());

        InheritanceNode fixGroup = (InheritanceNode) ((InheritanceNode.Builder) InheritanceNode.builder("guide").value(true)).build();
        if (user.getPrimaryGroup().equalsIgnoreCase("guide")) {
            InheritanceNode currGroup = (InheritanceNode) ((InheritanceNode.Builder) InheritanceNode.builder(user.getPrimaryGroup()).value(true)).build();
            player.sendMessage(currGroup.getGroupName());
        }

//        InheritanceNode nextGroup = (InheritanceNode) ((InheritanceNode.Builder) InheritanceNode.builder(next).value(true)).build();
//
//        user.data().add((Node) nextGroup);
//        user.data().remove((Node) currGroup);
//        user.data().add(fixGroup);



//        user.setPrimaryGroup(String.valueOf(nextGroup));
        SkyExcelNetworkMain.luckPerms.getUserManager().saveUser(user);
    }


    public List<String> getGroupList(Player player) {
        return getUser(player.getUniqueId()).getInheritedGroups(getQueryOptions(player)).stream().map(Group::getName).collect(Collectors.toList());
    }

    public QueryOptions getQueryOptions(Player player) {
        return getUser(player.getUniqueId()).getQueryOptions();
    }

    public String getPrimaryGroup(Player player) {
        return getUser(player.getUniqueId()).getPrimaryGroup();
    }

    public static InheritanceNode getInheritanceNode(String groupName) {
        return (InheritanceNode.builder(groupName).value(true)).build();
    }

    public User getUser(UUID uuid) {
        return luckPerms.getUserManager().getUser(uuid);
    }
}
