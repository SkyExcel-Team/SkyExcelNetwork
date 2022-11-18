package net.skyexcel.server.job.data.mineworker;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.StatMeta;
import net.skyexcel.server.job.data.stat.Statable;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;

import java.util.List;

public class Bait extends StatMeta implements JobPlayerData {

    private Player player;


    public Bait(Player player) {
        super("단단한 곡괭이", List.of("", "§6§l│ §6일정확률§f로 광물을 캘시, 곡괭이의 §e내구도§f가 달지 않습니다. ", ""));
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

        Config config = new Config("job/" + player.getUniqueId() + "/bait");

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }
}
