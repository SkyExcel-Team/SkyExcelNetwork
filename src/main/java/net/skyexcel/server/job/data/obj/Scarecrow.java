package net.skyexcel.server.job.data.obj;


import net.skyexcel.server.SkyExcelNetworkMain;
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


    private CoolTime coolTime;


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

        coolTime = new CoolTime(armorStand, player);
        coolTime.runTaskTimer(SkyExcelNetworkMain.getPlugin(), 0, 20);

        config.setLocation("location", player.getLocation());
    }

    public CoolTime getCoolTime() {
        return coolTime;
    }

    public class CoolTime extends BukkitRunnable {
        private Time time;

        private ArmorStand armorStand;

        private Player player;

        private Config config;

        public CoolTime(OfflinePlayer offlinePlayer) {
            config = new Config("data/Scarecrow/" + player.getUniqueId());
            config.setPlugin(SkyExcelNetworkMain.getPlugin());
        }

        public CoolTime(ArmorStand armorStand, Player player) {
            this.player = player;
            time = new Time(0, 0, 3, 0);
            this.armorStand = armorStand;

        }

        @Override
        public void run() {
            if (time.SECOND() == 0 && time.MINUTE() == 0) {
                player.sendMessage("허수아비가 없어졌습니다!");
                armorStand.remove();
                cancel();
            }
            time.minSecond(1);
            config.setLong("cooltime", time.SECOND_IN_MILLIS());
            armorStand.setCustomName(time.MINUTE() + "분 " + time.SECOND() + " 초 남았습니다");
        }
    }
}
