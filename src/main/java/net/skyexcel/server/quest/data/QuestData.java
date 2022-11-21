package net.skyexcel.server.quest.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.struct.quest.BreakDiamondQuest;
import net.skyexcel.server.quest.struct.Quest;
import net.skyexcel.server.quest.struct.quest.BreakTreeQuest;
import net.skyexcel.server.quest.struct.quest.BreakWheatQuest;
import net.skyexcel.server.quest.struct.quest.FlyingQuest;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.List;

public class QuestData {
    private Player player;


    private Config config;

    public QuestData(Player player) {
        this.player = player;
        this.config = new Config("quest/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void resetQuest() {
        List<Quest> quests = List.of(new BreakDiamondQuest(), new BreakTreeQuest(), new BreakWheatQuest(), new FlyingQuest());

        for (Quest quest : quests) {
            config.setString("quest." + quest.getName(), quest.getName());
        }
    }
}
