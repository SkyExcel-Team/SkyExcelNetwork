package net.skyexcel.server.skyblock.util.world;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import net.skyexcel.server.skyblock.util.world.chunk.EmptyChunkCreator;
import net.skyexcel.server.warp.data.Warp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


import java.io.File;
import java.io.FileInputStream;

public class WorldManager {

    private final String[] paths = {"islands/Based/large.schem", "islands/Based/middle.schem", "islands/Based/small.schem"};

    private World world;


    public void create() {

//        BlockVector3 pos1 = MobArena.WorldEdit.getSession(player).getSelection().getBoundingBox().getPos1();
//        BlockVector3 pos2 = MobArena.WorldEdit.getSession(player).getSelection().getBoundingBox().getPos2();



        WorldCreator wc = new WorldCreator("SkyBlock");
        wc.generator(new EmptyChunkCreator());
        wc.environment(World.Environment.NORMAL); // I'm going to create nether world with one wastes biome
        wc.type(WorldType.FLAT);
        wc.generateStructures(false);
        World created = wc.createWorld();


        assert created != null;
        created.setGameRule(GameRule.KEEP_INVENTORY, true);
        created.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        created.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        created.setSpawnLocation(new Location(created, 0, 0, 0));
        
    }

    public void create(Player player, int index) {
        org.bukkit.WorldCreator c = new org.bukkit.WorldCreator(player.getUniqueId().toString());
        c.createWorld();

        c.generator(new EmptyChunkCreator());


        c.type(WorldType.FLAT);
        c.generatorSettings("2;0;1;");

        World created = player.getServer().createWorld(c);

        player.teleport(new Location(created, 0, 0
                , 0));

        paste(created, paths[index]);

        assert created != null;
        created.setGameRule(GameRule.KEEP_INVENTORY, true);
        created.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        created.setGameRule(GameRule.DO_WEATHER_CYCLE, false);


        this.world = created;
        created.setSpawnLocation(new Location(created, 0, 0, 0));
        worldBorder(player);

    }


    public void paste(World world, Location location, String path) {
        BukkitWorld weWorld = new BukkitWorld(world);
        Clipboard clipboard;
        File file = new File(path);

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try {
            assert format != null;
            try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
                clipboard = reader.read();

                try (EditSession editSession = WorldEdit.getInstance().newEditSession(weWorld)) {
                    Operation operation = new ClipboardHolder(clipboard)
                            .createPaste(editSession)
                            .to(BlockVector3.at(location.getX(), location.getY(), location.getZ())) //Position
                            .build();

                    Operations.complete(operation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void paste(World world, String path) {
        BukkitWorld weWorld = new BukkitWorld(world);
        Clipboard clipboard;
        File file = new File(path);


        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try {
            assert format != null;
            try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
                clipboard = reader.read();


                try (EditSession editSession = WorldEdit.getInstance().newEditSession(weWorld)) {
                    Operation operation = new ClipboardHolder(clipboard)
                            .createPaste(editSession)
                            .to(BlockVector3.at(0, 0, 0)) //Position
                            .build();

                    Operations.complete(operation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBlocks(Location start, Location end) {
        int topBlockX = (Math.max(start.getBlockX(), end.getBlockX()));
        int bottomBlockX = (Math.min(start.getBlockX(), end.getBlockX()));

        int topBlockY = (Math.max(start.getBlockY(), end.getBlockY()));
        int bottomBlockY = (Math.min(start.getBlockY(), end.getBlockY()));

        int topBlockZ = (Math.max(start.getBlockZ(), end.getBlockZ()));
        int bottomBlockZ = (Math.min(start.getBlockZ(), end.getBlockZ()));

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    Block block = start.getWorld().getBlockAt(x, y, z);
                    if(!block.getType().equals(Material.AIR)){
                        block.setType(Material.AIR);
                    }
                }
            }
        }
    }
    public void worldBorder(Player player) {
        WorldBorder border = player.getWorld().getWorldBorder();
        border.setDamageBuffer(0);
        border.setSize(100);

    }

    public void removeBorder(Player player) {
        WorldBorder border = player.getWorld().getWorldBorder();
        border.reset();
    }


    public World getWorld() {
        return world;
    }
}
