package net.skyexcel.server.skyblock.data.island;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;
import skyexcel.data.location.Region;


/**
 * 섬 옵션 정보를 담고 있는 클래스 객체
 * 정보 중에서도 마인크래프트와 밀접한 정보를 담고 있습니다.
 */
public class SkyBlockMeta {

    private WeatherType weatherType;

    private boolean worldBorderVisibility;

    private boolean pvpToggle;

    private long time;

    private boolean pvp;

    private Config config;

    public SkyBlockMeta(String name) {

        this.config = new Config("data/SkyBlock/SkyBlock/" + name + "/" + name);
        this.config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
    }

    public void setWorldBorderVisibilty(Player player) {


        if (isWorldBorder()) {
            player.sendMessage("§c● §f월드보더 비활성화");

            SkyExcelNetworkSkyBlockMain.getWorldBorderApi().resetWorldBorderToGlobal(player);

            for (Player online : player.getWorld().getPlayers()) {
                if (isInIsland(online))
                    SkyExcelNetworkSkyBlockMain.getWorldBorderApi().resetWorldBorderToGlobal(online);
            }
            setWorldBorder(false);
        } else {
            player.sendMessage("§a● §f월드보더 활성화");
            int size = getSize();

            SkyExcelNetworkSkyBlockMain.getWorldBorderApi().setBorder(player, size, getLocation());

            for (Player online : player.getWorld().getPlayers()) {
                if (isInIsland(online))
                    SkyExcelNetworkSkyBlockMain.getWorldBorderApi().setBorder(online, size, getLocation());
            }

            setWorldBorder(true);
        }
    }

    public boolean isWorldBorder() {
        return config.getConfig().getBoolean("SkyBlock.option.worldBorder");
    }

    public void setWorldBorder(boolean is) {
        config.setBoolean("SkyBlock.option.worldBorder", is);

    }

    public WeatherType getWeather() {
        return WeatherType.valueOf(config.getConfig().getString("SkyBlock.option.weather"));
    }


    public Location getLocation() {
        return (config.getLocation("SkyBlock.location") != null ? config.getLocation("SkyBlock.location") : SkyExcelNetworkSkyBlockMain.config.getLocation("location"));
    }

    public int getSize() {
        return config.getConfig().getInt("SkyBlock.level.size");
    }

    public boolean isInIsland(Player player) {


        if (!player.getWorld().getName().equalsIgnoreCase("world")) {
            if (getLocation() != null) {

                int size = getSize();
                org.bukkit.Location pos1 = getLocation(); //자신의 섬의 영역을 불러온다.
                pos1.add(size, 256, size);

                org.bukkit.Location pos2 = getLocation();

                size = -1 * size;
                pos2.add(size, -256, size);

                Region region = new Region(pos1, pos2);
                return region.locationIsInRegion(player.getLocation());
            }
        }
        return false;
    }

    public void setPvp(boolean isPvp) {
        config.getConfig().set("SkyBlock.option.pvp", isPvp);
        config.saveConfig();
    }

    public boolean isPvp() {
        return config.getConfig().getBoolean("SkyBlock.option.pvp");
    }
}
