package net.skyexcel.server.job.data.stat;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class WaterBucket extends Statable{



    private Inventory inv;

    private final int line = 9; //

    private int level = 10; // 4/ 10 = 2


    public WaterBucket() {
        super("물병");
    }

    public void onGUI(){
        inv = Bukkit.createInventory(null, 45, "특별한 물병");


    }


}
