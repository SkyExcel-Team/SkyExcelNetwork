package net.skyexcel.server.job.data;

import net.skyexcel.server.job.data.stat.WaterBucket;
import net.skyexcel.server.job.gui.JobGUI;
import net.skyexcel.server.job.gui.JobSelectGUI;

import java.util.HashMap;
import java.util.UUID;

public class JobData {
    public static HashMap<UUID, JobGUI> gui = new HashMap<>();

    public static HashMap<UUID, net.skyexcel.server.job.data.stat.WaterBucket> waterBucket = new HashMap<>();

    public static HashMap<UUID, JobSelectGUI> selectGUI = new HashMap<>();


    //스텟 속성 슬롯
    public static final int[] slot = {10, 12, 14};

    public static final int[] FishSlot = {12, 14};
}
