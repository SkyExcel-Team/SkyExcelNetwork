package net.skyexcel.server.util;

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
import net.skyexcel.server.data.player.PlayerData;
import net.skyexcel.server.util.chunk.EmptyChunkCreator;
import org.bukkit.*;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;


import java.io.File;
import java.io.FileInputStream;

public class WorldManager {

    private final String[] paths = {"islands/Based/large.schem", "islands/Based/middle.schem", "islands/Based/small.schem"};

    private World world;

    public void create(Player player, int index) {
        org.bukkit.WorldCreator c = new org.bukkit.WorldCreator("plugins/island/world/" + player.getUniqueId().toString());
        Config config = new Config("world/" + player.getUniqueId().toString() + "/" + player.displayName());

        config.setPlugin(SkyExcelNetwork.plugin);
        config.getConfig().set("owner", player.getDisplayName());
        config.saveConfig();

        c.generator(new EmptyChunkCreator());
        World created = c.createWorld();
        c.type(WorldType.FLAT);
        c.generatorSettings("2;0;1;");
        player.teleport(new Location(created, 0, 0
                , 0));

        paste(created, paths[index]);

        assert created != null;
        created.setGameRule(GameRule.KEEP_INVENTORY, false);
        created.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        created.setGameRule(GameRule.DO_WEATHER_CYCLE, false);

        player.teleport(new Location(created, 0, 0, 0));

        this.world = created;
        worldBorder(player);
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

                Bukkit.getConsoleSender().sendMessage("오리진 좌표 : " + clipboard.getOrigin());
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

    public String getPath(Player player) {
        return "plugins/island/world/" + player.getUniqueId();
    }

    public World getWorld() {
        return world;
    }
}
