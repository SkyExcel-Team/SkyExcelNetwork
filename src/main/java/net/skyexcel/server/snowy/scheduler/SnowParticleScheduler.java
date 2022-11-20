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
    public static int taskId;
    private static Random random = new Random();

    private static final String worldName = "lobby"; //TODO - CHANGE
    private static final int centerX = -2;
    private static final int centerZ = 70;

    private static int radius;
    private static final int chance = 2;

    private static boolean done = false;

    private List<Block> blocks = new ArrayList<>();

    public SnowParticleScheduler() {
        this.taskId = Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkMain.getPlugin(), () -> {
            while(Bukkit.getWorld(worldName) == null) {}

            Location location = new Location(Bukkit.getWorld(worldName), centerX, 0, centerZ);
            radius = 40;
            for (int y = 63; y <= 80; y++) {
                for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
                    for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                        Block block = location.getWorld().getBlockAt(x, y, z);

                        blocks.add(block);
                    }
                }
            }

            location = new Location(Bukkit.getWorld(worldName), 0, 0, 0);
            radius = 10;
            for (int y = 65; y <= 80; y++) {
                for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
                    for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                        Block block = location.getWorld().getBlockAt(x, y, z);

                        blocks.add(block);
                    }
                }
            }

            done = true;
            System.out.println("파티클 구역 로딩이 완료되었습니다!");
        }, 20L * 5).getTaskId();
    }

    @Override
    public void run() {
        if (!done) return;

        blocks.forEach(block -> {
            if (getBoolean()) {
                Location loc = block.getLocation().add(
                        (float) random.nextGaussian(),
                        (float) random.nextGaussian(),
                        (float) random.nextGaussian()
                );

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
        return random.nextFloat() <= (chance / 100F);
    }

    public int getTaskId2() {
        return taskId;
    }
}
