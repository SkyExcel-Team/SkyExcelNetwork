package net.skyexcel.api.packet.sign;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class SignPacket {

    public void editGUI(Player player, Sign sign) {
        player.openSign(sign);
    }
}
