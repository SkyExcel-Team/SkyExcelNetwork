package net.skyexcel.server.quest.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.struct.quest.*;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;


import java.util.List;
import java.util.Random;


public class QuestData {
    private Player player;


    private static Config config;

    public QuestData(Player player) {
        this.player = player;
        config = new Config("quest/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    /**
     * @param quest
     * @return
     */
    public static boolean hasQuest(Quest quest) {

        config = new Config("quest/" + quest.getPlayer().getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        return config.getConfig().getConfigurationSection("quest").getKeys(false).contains(quest.getName());
    }


    public void resetQuest() {
        List<Quest> quests = (List.of(new BreakDiamondQuest(player),
                new BreakTreeQuest(player),
                new BreakWheatQuest(player),
                new FlyingQuest(player),
                new FishCaughtQuest(player), new BlockPlaceQuest(player), new VoteQuest(player)));


        int[] size = new int[5];
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            size[i] = random.nextInt(5);
            for (int j = 0; j < i; j++) {
                if (size[i] == size[j])
                    i--;
            }
        }
        for (int index = 0; index < 5; index++) {
            Quest quest = quests.get(size[index]);
            config.setInteger("quest." + quest.getName(), 1);
        }
    }
}
