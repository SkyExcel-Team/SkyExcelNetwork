package net.skyexcel.server.skyblock.data.player;

import net.skyexcel.api.packet.Inventory.InventoryUpdate;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.api.util.Items;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Visitor {

    private String name;

    private SkyBlock skyBlock;

    private Inventory inv;

    public Visitor(String name) {
        this.name = name;
        skyBlock = new SkyBlock(name);
    }


    public List<Player> getVisitors() {
        List<Player> visitors = new ArrayList<>();
        if (skyBlock.getLocation() != null) {
            Location location = skyBlock.getLocation();


            if (skyBlock.getSize() != 0) {
                double size = skyBlock.getSize();
                List<String> members = skyBlock.getMembers();
                for (Entity entity : Bukkit.getWorld("SkyBlock").getNearbyEntities(location, size, size, size)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;

                        if (skyBlock.getOwner() != null) {
                            if (skyBlock.getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {
                                visitors.add(player);
                            }

                            if (!player.getUniqueId().toString().equalsIgnoreCase(skyBlock.getOwner())) {
                                members.forEach(member -> {
                                    if (!player.getUniqueId().toString().equalsIgnoreCase(member)) {
                                        visitors.add(player);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
        return visitors;
    }

    public void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, name + " 방문객  " + getVisitors().size() + " 명");
        getVisitors().forEach(visitors -> {
            if (!visitors.getUniqueId().toString().equalsIgnoreCase(skyBlock.getOwner())) {
                Items items = new Items();
                ItemStack item = items.playerSkull(visitors.getName(), "", List.of());
                inv.setItem(0, item);
            }
        });
        player.openInventory(inv);
        this.inv = inv;
        update(player, "방문객 " + getVisitors().size() + " 명");
    }

    public void update(Player player, String title) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyExcelNetworkMain.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (inv != null) {
                    InventoryUpdate.updateInventory(SkyExcelNetworkMain.getPlugin(), player, title);
                }
            }
        }, 0, 20);
    }

    public SkyBlock getSkyBlock() {
        return skyBlock;
    }
}
