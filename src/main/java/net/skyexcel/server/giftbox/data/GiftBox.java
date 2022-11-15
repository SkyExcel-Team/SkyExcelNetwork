package net.skyexcel.server.giftbox.data;

import net.skyexcel.server.giftbox.SkyExcelNetworkGiftBoxMain;
import net.skyexcel.server.giftbox.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;
import skyexcel.data.file.util.Stockable;

import java.util.Arrays;
import java.util.List;

public class GiftBox extends Stockable {

    private OfflinePlayer player;
    private Config config;

    private GUI gui;

    private int MAX_PAGE = 5;

    private Inventory inv;

    public final int[] black_glass = {0, 1, 2, 3, 5, 6, 7, 8, 17, 9, 18, 26, 27, 35, 36, 44, 45, 46, 47, 51, 52, 53};


    public GiftBox(OfflinePlayer player) {
        super("giftbox",player.getUniqueId().toString(),SkyExcelNetworkGiftBoxMain.plugin);
        this.player = player;
        this.config = new Config("GiftBox/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkGiftBoxMain.plugin);
        gui = new GUI(config);
    }

    public void open() {

    }

    @Override
    public void save(Inventory inv) {
        for(int i : black_glass){
            inv.getItem(i).setAmount(0);
        }
        super.save(inv);
    }


    public void next(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "선물함");


        inv.setItem(4, Items.playerSkull(player.getName(), "", Arrays.asList("")));
        Arrays.stream(black_glass).forEach(slots -> {
            Items.newItem("", Material.BLACK_STAINED_GLASS_PANE, 1, List.of(""), slots, inv);
        });
        this.inv = inv;
        player.openInventory(inv);
    }


    public Inventory getInv() {
        return inv;
    }
}
