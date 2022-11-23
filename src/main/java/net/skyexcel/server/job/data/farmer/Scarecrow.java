package net.skyexcel.server.job.data.farmer;

import net.skyexcel.server.LoadType;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.StatMeta;
import net.skyexcel.server.job.data.farmer.scheduler.coolTime;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import org.bukkit.util.EulerAngle;
import skyexcel.data.file.Config;

import java.util.List;
import java.util.Objects;

public class Scarecrow extends StatMeta {

    private Location location;

    private int level;

    private HoeLevel hoeLevel = HoeLevel.WOODEN_HOE;

    private ArmorStand armorStand;

    private coolTime coolTime;

    private OfflinePlayer player;

    private Config config;


    /**
     * @param location 허수아비가 스폰 될 위치
     * @param level
     * @param player
     */
    public Scarecrow(Location location, int level, OfflinePlayer player) {
        super("허수아비", List.of("", "§6§l│ §6허수아비§f를 소환해, 주변의 §6농작물§f을 빠르게 자라게 해줍니다. ", "§6§l│ §f사용 방법:", "§6§l│ §f괭이를 들고 §6쉬프트 §f+ §6우클릭", ""));
        this.location = location;
        this.level = level;
        this.player = player;
    }

    public Scarecrow(OfflinePlayer offlinePlayer) {
        super("허수아비", List.of("", "§6§l│ §6허수아비§f를 소환해, 주변의 §6농작물§f을 빠르게 자라게 해줍니다. ", "§6§l│ §f사용 방법:", "§6§l│ §f괭이를 들고 §6쉬프트 §f+ §6우클릭", ""));
        this.player = offlinePlayer;
        this.config = new Config("job/" + offlinePlayer.getUniqueId() + "/Scarecrow");
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void setDefault() {
        this.config.setLong("level", 0);
        this.config.getConfig().set("location", null);
        this.config.saveConfig();
    }

    public void spawn(OfflinePlayer player) {


        //TODO 아머스탠드 중복 소환 방지 해야함.
        if (armorStand == null) {
            ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setArms(true);

            EntityEquipment equipmentSlot = armorStand.getEquipment();

            ItemStack head = SkyExcelNetworkMain.hdb.getItemHead("29830");

            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName("허수아비");


            switch (level) {
                case 2 -> {
                    this.hoeLevel = HoeLevel.STONE_HOE;
                }
                case 3 -> {
                    this.hoeLevel = HoeLevel.GOLDEN_HOE;

                }
                case 4 -> {
                    this.hoeLevel = HoeLevel.IRON_HOE;
                }
                case 5 -> {
                    this.hoeLevel = HoeLevel.DIAMOND_HOE;
                }
                case 6 -> {
                    this.hoeLevel = HoeLevel.NETHERITE_HOE;
                }
            }

            equipmentSlot.setItemInHand(new ItemStack(Material.valueOf(hoeLevel.name())));
            equipmentSlot.setHelmet(head);
            equipmentSlot.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            equipmentSlot.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            equipmentSlot.setBoots(new ItemStack(Material.LEATHER_BOOTS));

            EulerAngle eulerAngle = new EulerAngle(0.0, 0.0, 0.0);
            EulerAngle right = new EulerAngle(-0.5, 0.0, 0.0);

            armorStand.setLeftArmPose(eulerAngle);
            armorStand.setRightArmPose(right);
            this.armorStand = armorStand;
            this.coolTime = new coolTime(player);
            this.coolTime.setArmorStand(armorStand);
            this.coolTime.runTaskTimer(SkyExcelNetworkMain.getPlugin(), 0, 20);
            player.getPlayer().sendMessage(getDisplayName() + " 스킬을 사용 하였습니다.");
        } else {
            player.getPlayer().sendMessage("허수아비를 이미 스폰 했습니다");
        }
    }

    public void run(Player player) {
        if (SkyExcelNetworkMain.isLoaded(player)) {
            Scarecrow scarecrow = new Scarecrow(player.getLocation(), 1, player);
            scarecrow.spawn(player);
        }
    }


    public enum HoeLevel {
        WOODEN_HOE, STONE_HOE, GOLDEN_HOE, IRON_HOE, DIAMOND_HOE, NETHERITE_HOE
    }

}
