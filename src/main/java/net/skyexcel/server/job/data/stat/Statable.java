package net.skyexcel.server.job.data.stat;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

/**
 * 스탯포인트를 찍을 수 있는 클래스 입니다.
 */
public abstract class Statable {

    private int level;

    private String name;

    private OfflinePlayer player;

    private Config config;

    private String path;


    public Statable(String name, String path, OfflinePlayer player) {
        this.name = name;
        this.player = player;
        this.path = path;
        this.config = new Config(path);
        this.config.setPlugin(SkyExcelNetworkJobMain.plugin);
    }


    public int getLevel() {
        return (this.config == null ? -1 : this.config.getInteger("level"));
    }

    public void setStat() {

    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
