package net.skyexcel.server.job.data.mineworker;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.stat.Statable;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;

public class Bait extends Statable implements JobPlayerData {

    private Player player;


    public Bait(Player player) {
        super("단단한 곡괭이", "job/" + player.getUniqueId() + "/bait", player);
        this.player = player;
    }

    public void run(Player player, ItemStack item, ItemStack previous) {
        if (item != null) {
            if (item.getType().equals(Material.FISHING_ROD)) {
                if (item.getEnchantmentLevel(Enchantment.LURE) == 0) {
                    item.addUnsafeEnchantment(Enchantment.LURE, 1);
                } else {
                    item.addUnsafeEnchantment(Enchantment.LURE, item.getEnchantmentLevel(Enchantment.LURE) + 1);
                }
            }
        } else {

            if (previous != null || previous.getType().equals(Material.FISHING_ROD)) {
                if (previous.getEnchantmentLevel(Enchantment.LURE) == 0) {
                    previous.addUnsafeEnchantment(Enchantment.LURE, 1);
                } else {
                    previous.addUnsafeEnchantment(Enchantment.LURE, item.getEnchantmentLevel(Enchantment.LURE) + 1);
                }
            }
        }
    }

    public void setDefault() {

        Config config = new Config(getPath());

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }
}
