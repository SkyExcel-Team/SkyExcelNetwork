package net.skyexcel.server.trade.data;

import net.skyexcel.server.trade.gui.TradeGUI;
import org.bukkit.entity.Player;

public class TargetInfo {

    private TradeGUI tradeGUI;
    private Player player;


    public TargetInfo(Player player, TradeGUI tradeGUI) {
        this.player = player;
        this.tradeGUI = tradeGUI;
    }


}
