package net.skyexcel.server.skyblock.data;

import net.skyexcel.server.skyblock.data.island.DeleteRunnable;
import net.skyexcel.server.skyblock.data.island.InviteSkyBlock;
import net.skyexcel.server.skyblock.ui.gui.MaterialPageMember;
import net.skyexcel.server.skyblock.ui.gui.MaterialPagePartTime;
import net.skyexcel.server.skyblock.ui.gui.PageVisitor;
import net.skyexcel.server.skyblock.ui.title.Loading;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.UUID;

public class SkyBlockData {

    public static HashMap<UUID, MaterialPageMember> memberPage = new HashMap<>();

    public static HashMap<UUID, MaterialPagePartTime> partTimePage = new HashMap<>();

    public static HashMap<UUID, MaterialPagePartTime> testPage = new HashMap<>();

    public static HashMap<UUID, DeleteRunnable> delete = new HashMap<>();

    public static HashMap<UUID, PageVisitor> visitorHashMap = new HashMap<>();

    public static HashMap<UUID, InviteSkyBlock> inviteSkyBlock = new HashMap<>();

    public static Material[] remove = {Material.BEDROCK, Material.BARRIER, Material.COMMAND_BLOCK, Material.SCULK,
            Material.SCULK_CATALYST, Material.SCULK_SENSOR, Material.SCULK_SHRIEKER, Material.SCULK_VEIN,
            Material.DRAGON_EGG, Material.TURTLE_EGG, Material.JIGSAW, Material.POINTED_DRIPSTONE,
            Material.AMETHYST_CLUSTER, Material.AMETHYST_BLOCK, Material.AMETHYST_SHARD, Material.SMALL_AMETHYST_BUD, Material.MEDIUM_AMETHYST_BUD, Material.LARGE_AMETHYST_BUD,
            Material.REDSTONE_BLOCK, Material.PISTON, Material.STICKY_PISTON, Material.TNT, Material.LIGHTNING_ROD, Material.STRUCTURE_BLOCK, Material.DRIED_KELP_BLOCK, Material.STONECUTTER,
            Material.SPAWNER, Material.REINFORCED_DEEPSLATE, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK, Material.CONDUIT, Material.DISPENSER, Material.DROPPER};
}
