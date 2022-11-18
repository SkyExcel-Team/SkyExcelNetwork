package net.skyexcel.server.job.data;

import net.skyexcel.server.job.data.farmer.Scarecrow;
import net.skyexcel.server.job.data.fisher.WaterBucket;
import net.skyexcel.server.job.gui.JobGUI;
import net.skyexcel.server.job.gui.JobSelectGUI;

import java.util.HashMap;
import java.util.UUID;

public class JobData {
    public static HashMap<UUID, JobGUI> gui = new HashMap<>();

    public static HashMap<UUID, WaterBucket> waterBucket = new HashMap<>();

    public static HashMap<UUID, JobSelectGUI> selectGUI = new HashMap<>();

    public static HashMap<UUID, Scarecrow> scarecrow = new HashMap<>();


    //스텟 속성 슬롯
    public static final int[] slot = {11, 13, 15};

    public static final int[] FishSlot = {12, 14};
}
