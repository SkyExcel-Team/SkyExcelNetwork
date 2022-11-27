package net.skyexcel.server.playerprofile.data;

import net.skyexcel.server.playerprofile.gui.PlusButton;
import net.skyexcel.server.playerprofile.gui.SkyBlockButton;
import net.skyexcel.server.playerprofile.gui.TargetSkullButton;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerProfileGUI {

    private OfflinePlayer target;

    final int TargetSkullSlot = 10;
    final int SkyBlockSlot = 12;

    final int PlusSlot = 13;
    public PlayerProfileGUI(OfflinePlayer target) {
        this.target = target;
    }

    public void onGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, target.getName() + " 님의 프로필");

        new TargetSkullButton(target).setInventory(TargetSkullSlot, inv);
        new SkyBlockButton(target).setInventory(SkyBlockSlot, inv);
        new PlusButton().setInventory(PlusSlot, inv);

        player.openInventory(inv);
    }

    public int getTargetSkullSlot() {
        return TargetSkullSlot;
    }

    public int getPlusSlot() {
        return PlusSlot;
    }

    public int getSkyBlockSlot() {
        return SkyBlockSlot;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
}
