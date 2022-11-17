package net.skyexcel.server.job.data;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public interface JobPlayerData {

    default void setStatPoint(Player player, String name, double value) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.setDouble(name, value);
    }

    /**
     * 플레이어 스탯 포인트가 존재하지 않을 때 무조건 value 값으로 저장을 합니다.
     * @param player - 스텟 포인트를 저장할 곳.
     * @param name 스텟 포인트
     * @param value
     */
    default void increase(Player player, String name, double value) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);

        if (getStatPoint(player, name) != -1) {
            config.setDouble(name, getStatPoint(player, name) + value);
        } else {
            config.setDouble(name, value);
        }

    }

    /**
     * 플레이어 스탯 포인트가 존재하지 않을 때 save가 true일경우, value값으로 저장을 합니다.
     * @param player - 스텟 포인트를 저장할 곳.
     * @param name 스텟 포인트
     * @param value
     * @param save
     */
    default void increase(Player player, String name, double value, boolean save) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);

        if (getStatPoint(player, name) != -1) {
            config.setDouble(name, getStatPoint(player, name) + value);
        } else if (save) {
            config.setDouble(name, value);
        }

    }

    default void decrease(Player player, String name, double value) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.setDouble(name, getStatPoint(player, name) - value);
    }

    default double getStatPoint(Player player, String name) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);

        return config.getDouble(name);
    }

    default double getLevel(Player player) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);

        return config.getDouble("level");
    }
}
