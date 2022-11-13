package net.skyexcel.server.job.data.obj;


import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.runnable.CoolTime;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import skyexcel.data.Time;
import skyexcel.data.file.Config;

public class Scarecrow {


    private Config config;


    public Scarecrow(OfflinePlayer player) {

        config = new Config("data/Scarecrow/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void spawn(Player player) {

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setArms(true);

        EntityEquipment equipmentSlot = armorStand.getEquipment();
        ItemStack head = SkyExcelNetworkMain.hdb.getItemHead("29830");

        armorStand.setCustomNameVisible(true);

        armorStand.setCustomName("허수아비");

        equipmentSlot.setItemInHand(new ItemStack(Material.DIAMOND_HOE));
        equipmentSlot.setHelmet(head);
        equipmentSlot.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        equipmentSlot.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        equipmentSlot.setBoots(new ItemStack(Material.LEATHER_BOOTS));

        EulerAngle eulerAngle = new EulerAngle(0, 0, 0);
        EulerAngle right = new EulerAngle(-.5, 0, 0);

        armorStand.setLeftArmPose(eulerAngle);
        armorStand.setRightArmPose(right);

        CoolTime coolTime = new CoolTime(armorStand, player);
        coolTime.runTaskTimer(SkyExcelNetworkMain.getPlugin(), 0, 20);
    }


}
