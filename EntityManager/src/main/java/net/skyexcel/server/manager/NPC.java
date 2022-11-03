package net.skyexcel.server.manager;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class NPC {
    public void spawn(Player player){

         org.bukkit.entity.NPC npc = (org.bukkit.entity.NPC) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);


    }
}
