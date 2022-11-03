package net.skyexcel.server.data.player;

import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.util.packet.InventoryUpdate;
import net.skyexcel.server.util.Items;
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
                int size = skyBlock.getSize();
                List<String> members = skyBlock.getMembers();
                for (Entity entity : location.getNearbyEntities(size, size, size)) {
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

    public void openGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, name + " 방문객  " + getVisitors().size() + " 명");
        getVisitors().forEach(visitors -> {
            if (!visitors.getUniqueId().toString().equalsIgnoreCase(skyBlock.getOwner())) {
                ItemStack item = Items.playerSkull(visitors.getName(), "", List.of());
                inv.setItem(0, item);
            }
        });
        player.openInventory(inv);
        this.inv = inv;
        update(player,"방문객 " + getVisitors().size() + " 명");
    }

    public void update(Player player,String title){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlockCore.plugin, new Runnable() {
            @Override
            public void run() {
                if(inv != null){
                    InventoryUpdate.updateInventory(SkyBlockCore.plugin,player,title);
                }
            }
        },0,20);
    }

    public SkyBlock getSkyBlock() {
        return skyBlock;
    }
}
