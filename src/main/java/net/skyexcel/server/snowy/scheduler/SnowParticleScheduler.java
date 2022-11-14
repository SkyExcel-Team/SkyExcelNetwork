package net.skyexcel.server.snowy.scheduler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.skyexcel.server.snowy.util.SnowSettingUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowParticleScheduler {
    private JavaPlugin plugin;
    private Random random = new Random();

    private final String world = "lobby";
    private final int centerX = -2;
    private final int centerY = 96;
    private final int centerZ = 70;
    private final int radius = 30;
    private final float chance = 0.12F;

    private List<Block> blocks = new ArrayList<>();

    public SnowParticleScheduler(JavaPlugin plugin) {
        this.plugin = plugin;

        //GET NEARBY BLOCKS
        Location centerLoc = new Location(Bukkit.getWorld(world), centerX, centerY, centerZ);
        for(int x = centerLoc.getBlockX() - radius; x <= centerLoc.getBlockX() + radius; x++) {
            for(int y = centerLoc.getBlockY() - radius; y <= centerLoc.getBlockY() + radius; y++) {
                for(int z = centerLoc.getBlockZ() - radius; z <= centerLoc.getBlockZ() + radius; z++) {
                    blocks.add(centerLoc.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        blocks.forEach(block -> {
            if (block.getType() != Material.AIR)
                blocks.remove(block);
        });

        //CREATE PACKET
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {
            blocks.forEach(block -> {
                if (getBoolean()) {
                    PacketContainer packet = pm.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
                    packet.getModifier().writeDefaults();
                    packet.getParticles().write(0, EnumWrappers.Particle.END_ROD);
                    packet.getFloat().write(0, (float) block.getX())
                            .write(1, (float) block.getY())
                            .write(2, (float) block.getZ());

                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if (SnowSettingUtils.getSnowVisibility(player)) {
                            try {
                                pm.sendServerPacket(player, packet);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }, 0L, 20L * 5);
    }

    private boolean getBoolean() {
        return random.nextFloat() <= chance;
    }
}
