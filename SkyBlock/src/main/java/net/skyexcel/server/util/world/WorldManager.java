package net.skyexcel.server.util.world;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.util.FileUtils;
import net.skyexcel.server.util.world.chunk.EmptyChunkCreator;
import org.bukkit.*;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;


import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class WorldManager {

    private final String[] paths = {"islands/Based/large.schem", "islands/Based/middle.schem", "islands/Based/small.schem"};

    private World world;

    public void create(Player player, int index) {
        org.bukkit.WorldCreator c = new org.bukkit.WorldCreator(player.getUniqueId().toString());

        c.generator(new EmptyChunkCreator());
        World created = c.createWorld();

        c.type(WorldType.FLAT);
        c.generatorSettings("2;0;1;");
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

    public void delete(Player player) {
        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        org.bukkit.WorldCreator c = new org.bukkit.WorldCreator(player.getUniqueId().toString());
        World created = c.createWorld();

        created.getWorldFolder().delete();
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
