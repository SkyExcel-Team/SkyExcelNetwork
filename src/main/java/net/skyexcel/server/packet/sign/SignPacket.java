package net.skyexcel.server.packet.sign;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class SignPacket {

    public void editGUI(Player player, Sign sign) {
        player.openSign(sign);
    }
}
