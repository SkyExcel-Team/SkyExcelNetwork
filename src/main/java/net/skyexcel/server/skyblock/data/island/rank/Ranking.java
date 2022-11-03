package net.skyexcel.server.skyblock.data.island.rank;

import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private Config config;


    private Config rankdata;

    private List<Rank> ranks = new ArrayList<>();

    private List<Integer> Rank_num = new ArrayList<>();


    public Ranking() {
        config = new Config("SkyBlock/");
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);

        rankdata = new Config("rank");
        rankdata.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
    }


    public void update(Player player){
        for (String name : config.fileListName()) {
            SkyBlock skyBlock = new SkyBlock(name);
            int level = skyBlock.getLevel();

            Rank rank = new Rank(level, skyBlock.getName());

            Rank_num.add(rank.level);
            ranks.add(rank);
        }

        for (Rank rank : ranks) {
            int index = ranks.indexOf(rank);
            rank.setRank(getRanksArray(Rank_num)[index]);

            rankdata.setString(rank.Rank + ".name", rank.name);
            rankdata.setInteger(rank.Rank + ".rank", rank.Rank);
            rankdata.setInteger(rank.Rank + ".level", rank.level);
        }

    }



    public String getRankName(int rank) {
        return (rank <= 10 ? rankdata.getString(rank + ".name") : "");
    }

    public int getRank(int rank) {
        System.out.println(rankdata.getConfig().getInt(rank + ".rank"));
        return (rank <= 10 ? rankdata.getConfig().getInt(rank + ".rank") : -1);
    }

    public int getRankLevel(int rank) {
        System.out.println(rankdata.getConfig().getInt(rank + ".level"));
        return (rank <= 10 ? rankdata.getConfig().getInt(rank + ".level") : -1);
    }


    public int[] getRanksArray(List<Integer> array) {
        int[] result = new int[array.size()];

        for (int i = 0; i < array.size(); i++) {
            int count = 0;
            for (Integer integer : array) {
                if (integer > array.get(i)) {
                    count++;
                }
            }
            result[i] = count + 1;
        }
        return result;
    }

    public static class Rank {

        private int level;

        private String name;

        private int Rank;

        public Rank(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public void setRank(int rank) {
            Rank = rank;
        }
    }
}
