package net.skyexcel.server.quest.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.struct.quest.*;
import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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

    public QuestData() {
        config = new Config("quest/");
        config.setPlugin(SkyExcelNetworkMain.getPlugin());

    }

    /**
     * @param quest
     * @return
     */
    public static boolean hasQuest(Quest quest) {

        config = new Config("quest/" + quest.getPlayer().getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        if (config.getConfig().get("quest ") != null)
            return config.getConfig().getConfigurationSection("quest").getKeys(false).contains(quest.getName());
        return false;
    }


    public void removeAllQuests() {
//        List<String> names = new ArrayList<>();
//        for (File files : config.getFileList()) {
//            files.delete();
//            names.add(files.getName().replace(".yml", ""));
//        }

//        config.removeKey("quest");
//        if (!names.isEmpty()) {
//            for (String name : names) {
//                Config config = new Config("quest/" + name);
//                config.setPlugin(SkyExcelNetworkMain.getPlugin());
//                resetQuest(config, name);
//            }
//        }
    }


    public void resetQuest(Config config, String name) {
        List<Quest> quests = (List.of(new BreakDiamondQuest(name),
                new BreakTreeQuest(name),
                new BreakWheatQuest(name),
                new FlyingQuest(name),
                new FishCaughtQuest(name), new BlockPlaceQuest(name)));


        int[] size = new int[4];
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            size[i] = random.nextInt(4);
            for (int j = 0; j < i; j++) {
                if (size[i] == size[j])
                    i--;
            }
        }
        for (int index = 0; index < 4; index++) {
            Quest quest = quests.get(size[index]);
            config.setInteger("quest." + quest.getName(), 1);
        }

        config.setInteger("quest." + new VoteQuest(name).getName(), 1);
    }

    public void resetQuest() {
        List<Quest> quests = (List.of(new BreakDiamondQuest(player),
                new BreakTreeQuest(player),
                new BreakWheatQuest(player),
                new FlyingQuest(player),
                new FishCaughtQuest(player), new BlockPlaceQuest(player)));


        int[] size = new int[4];
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            size[i] = random.nextInt(4);
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
