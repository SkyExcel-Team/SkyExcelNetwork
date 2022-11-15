package net.skyexcel.server.snowy.scheduler;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.snowy.data.SnowToggleData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowParticleScheduler extends BukkitRunnable {
    private int taskId;

    private static Random random = new Random();

    private static final String worldName = "lobby"; //TODO - CHANGE
    private static final int centerX = -2;
    private static final int centerZ = 70;
    private static final int minY = 63;
    private static final int maxY = 110;

    private static final int radius = 15; //TODO - CHANGE (95)
    private static final int chance = 12;

    private static boolean done = false;

    private List<Block> blocks = new ArrayList<>();

    public SnowParticleScheduler() {
        Location location = new Location(Bukkit.getWorld(worldName), centerX, 0, centerZ);

        this.taskId = Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkMain.getPlugin(), () -> {
            for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    for(int y = maxY; y >= minY; y--) {
                        Block block = location.getWorld().getBlockAt(x, y, z);

                        if (block.getType().isAir())
                            blocks.add(block);
                    }
                }
            }

            done = true;
            System.out.println("끝남");
        }, 20L * 5).getTaskId();
    }

    @Override
    public void run() {
        if (!done) return;

        blocks.forEach(block -> {
            if (getBoolean()) {
                Location loc = block.getLocation().subtract(
                        (float) random.nextGaussian(),
                        (float) random.nextGaussian(),
                        (float) random.nextGaussian()
                );

//                for (int i = 0; loc.getX() + i <= maxY; i++) {
//                    if (!loc.getWorld().getBlockAt((int) loc.getX(), ((int) loc.getY() + i), (int) loc.getZ()).getType().isAir()) {
//                        blocks.remove(block);
//                        return;
//                    }
//                }

                if (getBoolean()) {
                    Bukkit.getWorld(worldName).getPlayers().forEach(player -> {
                        if ((new SnowToggleData(player)).getConfig().getBoolean("snow")) {
                            ParticleEffect.END_ROD.display(loc, player);
                        }
                    });
                } else {
                    Bukkit.getWorld(worldName).getPlayers().forEach(player -> {
                        if ((new SnowToggleData(player)).getConfig().getBoolean("snow")) {
                            ParticleEffect.WHITE_ASH.display(loc, player);
                        }
                    });
                }
            }
        });
    }

    public Boolean getBoolean() {
        return random.nextFloat() <= (chance/100F);
    }

    public int getId() {
        return taskId;
    }
}
