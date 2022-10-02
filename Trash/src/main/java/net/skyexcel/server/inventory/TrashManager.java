package net.skyexcel.server.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class TrashManager {

    public static Inventory trash;

    public static void Trash() {
        trash = Bukkit.createInventory(null, 27, "쓰레기통");

    }
}
