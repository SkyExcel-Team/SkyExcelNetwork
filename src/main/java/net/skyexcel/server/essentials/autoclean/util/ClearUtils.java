package net.skyexcel.server.essentials.autoclean.Util;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class ClearUtils {
    public static int clearWorld(World world) {
        int removed = 0;

        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof Item)) continue;
            entity.remove();
            removed++;
        }

        return removed;
    }
}
