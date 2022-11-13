package net.skyexcel.server.mileage.data;


import net.skyexcel.server.mileage.data.shop.MileageShop;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Data {
    private HashMap<UUID, MileageShop> mileageShop = new HashMap();

    public MileageShop get(Player player) {
        if (mileageShop.containsKey(player.getUniqueId())) {
            return mileageShop.get(player.getUniqueId());
        }
        return null;
    }

    public void add(Player player, MileageShop mileageShop) {
        if (!this.mileageShop.containsKey(player.getUniqueId())) {
            this.mileageShop.put(player.getUniqueId(), mileageShop);
        }
    }

    public void remove(Player player) {
        if (mileageShop.containsKey(player.getUniqueId())) {
            mileageShop.remove(player.getUniqueId());
        }
    }
}
