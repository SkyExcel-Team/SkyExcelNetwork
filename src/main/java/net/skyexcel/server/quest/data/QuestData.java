package net.skyexcel.server.quest.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.struct.quest.*;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import skyexcel.data.file.Config;


import java.util.*;


public class QuestData {
    private OfflinePlayer player;


    private static Config config;


    public QuestData(OfflinePlayer player) {
        this.player = player;
        config = new Config("quest/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    /**
     * @param quest
     * @return
     */
    public boolean hasQuest(Quest quest) {

        config = new Config("quest/" + quest.getPlayer().getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        if (config.getConfig().get("quest") != null)
            return config.getConfig().getConfigurationSection("quest").getKeys(false).contains(quest.getName());
        return false;
    }


    public List<Quest> getQuests() {
        List<Quest> newArray = new ArrayList<>();
        if (config != null) {
            ConfigurationSection section = config.getConfig().getConfigurationSection("quest");
            for (String name : section.getKeys(false)) {
                FlyingQuest flyingQuest = new FlyingQuest(player);
                if (flyingQuest.getName().equalsIgnoreCase(name)) {
                    newArray.add(flyingQuest);
                }
            }
            return newArray;
        }
        return Collections.emptyList();
    }


    /**
     * @return 모든 퀘스트를 깻을때 참이 반환된다.
     */
    public boolean isAllComplete() {
        List<Boolean> booleans = new ArrayList<>();
        ConfigurationSection section = config.getConfig().getConfigurationSection("quest");

        if (config.getConfig().get("quest") != null) {
            for (String name : section.getKeys(false)) {
                if (config.getConfig().get(name) instanceof Boolean) {
                    boolean result = config.getBoolean("quest." + name);
                    if (result)
                        booleans.add(true);
                }
            }

            return booleans.size() != 5;
        }
        return false;
    }


    public void resetQuest() {
        config.removeKey("quest");
        List<Quest> quests = (List.of(new BreakDiamondQuest(player),
                new BreakTreeQuest(player),
                new BreakWheatQuest(player),
                new FlyingQuest(player),
                new FishCaughtQuest(player), new BlockPlaceQuest(player)));


        int[] size = new int[quests.size()];
        for (int i = 0; i < quests.size(); i++) {
            Random random = new Random();
            size[i] = random.nextInt(quests.size());
            for (int j = 0; j < i; j++) {
                if (size[i] == size[j])
                    i--;
            }
        }
        for (int index = 0; index < 4; index++) {
            Quest quest = quests.get(size[index]);
            config.setInteger("quest." + quest.getName(), 1);
        }

        config.setInteger("quest." + new VoteQuest(player).getName(), 1);
    }
}
