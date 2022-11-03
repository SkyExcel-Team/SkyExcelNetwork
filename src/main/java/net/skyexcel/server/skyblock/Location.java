package net.skyexcel.server.skyblock;

import org.bukkit.World;

/**
 * 해당 클래스는 버킷 기존의 Location 클래스에 확장 버젼으로, SkyBlock에 관한 더 많은 기능들을 사용 할 수 있습니다.
 */
public class Location extends org.bukkit.Location {

    private org.bukkit.Location location;

    public Location(World world, double x, double y, double z) {
        super(world, x, y, z);
        location = new org.bukkit.Location(world, x, y, z);
    }


    /**
     * 스폰 가능한 위치를 찾습니다.
     *
     * @return
     */
    public boolean canSpawn(double x, double y, double z) {

        return location.getNearbyEntities(x, y, z).isEmpty();
    }

    public org.bukkit.Location getLocation() {
        return location;
    }
}
