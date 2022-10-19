package net.skyexcel.server.data;

import net.skyexcel.server.ui.gui.MaterialPageMember;
import net.skyexcel.server.ui.gui.MaterialPagePartTime;
import net.skyexcel.server.ui.title.Loading;

import java.util.HashMap;
import java.util.UUID;

public class SkyBlockData {
    public static HashMap<UUID, Loading> loading = new HashMap<>();

    public static HashMap<UUID, MaterialPageMember> memberPage = new HashMap<>();

    public static HashMap<UUID, MaterialPagePartTime> partTimePage = new HashMap<>();

}
