package net.skyexcel.server.snowy.scheduler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.skyexcel.server.snowy.data.SnowToggleData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowParticleScheduler {
    private static JavaPlugin plugin;
    private static Random random = new Random();

    private static final String world = "lobby"; //TODO - CHANGE
    private static final int centerX = -2;
    private static final int centerY = 96;
    private static final int centerZ = 70;
    private static final double radius = 0.5; //TODO - CHANGE
    private static final float chance = 0.12F;

    public static final List<Player> players = new ArrayList<>();

    public static void enable(JavaPlugin pl) {
        plugin = pl;

        //CREATE PACKET
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();

        PacketContainer packet = pm.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getModifier().writeDefaults();
        packet.getParticles().write(0, EnumWrappers.Particle.END_ROD);

        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Bukkit.getWorld(world).getPlayers().forEach(player -> {
                if ((new SnowToggleData(player)).getConfig().getBoolean("snow")) {
//                    if (getBoolean()) {
                    packet.getFloat().write(0, (float) player.getLocation().getX())
                            .write(1, (float) (player.getLocation().getY() + 2))
                            .write(2, (float) player.getLocation().getZ());

                    try {
                        pm.sendServerPacket(player, packet);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
//                    }
                }
            });
        }, 0L, 1L);
    }

    private static boolean getBoolean() {
        return random.nextFloat() <= chance;
    }
}
