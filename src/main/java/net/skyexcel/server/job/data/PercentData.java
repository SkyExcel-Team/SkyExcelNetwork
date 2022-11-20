package net.skyexcel.server.job.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Material;
import skyexcel.data.file.Config;

public class PercentData {
    private Config config;

    public PercentData() {
        config = new Config("Job-Config.yml");
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public boolean getBaitChance() {
        return config.getPercent("percent.Mineworker.Bait");
    }

    public boolean getFeverTimeChance() {
        return config.getPercent("percent.Mineworker.FeverTime.chance");
    }

    public double getFeverTimeDuration() {
        return config.getDouble("percent.Mineworker.FeverTime.duration");
    }

    public int getFeverTimeAmplifier() {
        return config.getInteger("percent.Mineworker.FeverTime.amplifier");
    }


    public Material getFeverTimeType() {
        return Material.valueOf(config.getString("percent.Mineworker.BlastFurance"));
    }
    public boolean getBlastFuranceChance() {
        return config.getPercent("percent.Mineworker.BlastFurance");
    }

    public boolean getBlessingChance() {
        return config.getPercent("percent.Farmer.blessing");
    }
}
