package net.skyexcel.server.trade.data;


import net.skyexcel.server.trade.gui.TradeGUI;

import java.util.HashMap;
import java.util.UUID;

public class Data {

    public static HashMap<UUID, PlayerInfo> playerInfo = new HashMap<>();
    public static HashMap<UUID, TargetInfo> targetInfo = new HashMap<>();
}
