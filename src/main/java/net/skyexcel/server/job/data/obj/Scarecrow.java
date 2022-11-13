package net.skyexcel.server.job.data.obj;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.lang.reflect.InvocationTargetException;

public class Scarecrow {


    public void spawn(Player player) {

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setArms(true);

        EntityEquipment equipmentSlot = armorStand.getEquipment();

        ItemStack head = SkyExcelNetworkMain.hdb.getItemHead("29830");

        equipmentSlot.setItemInHand(new ItemStack(Material.DIAMOND_HOE));
        equipmentSlot.setHelmet(head);
        equipmentSlot.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        equipmentSlot.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        equipmentSlot.setBoots(new ItemStack(Material.LEATHER_BOOTS));

        EulerAngle eulerAngle = new EulerAngle(0, 0, 0);
        EulerAngle right = new EulerAngle(-.5, 0, 0);

        armorStand.setLeftArmPose(eulerAngle);
        armorStand.setRightArmPose(right);
    }

}
