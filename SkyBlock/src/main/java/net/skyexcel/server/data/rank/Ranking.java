package net.skyexcel.server.data.rank;

import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.island.SkyBlock;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {

    private Config config;


    private List <Rank> ranks;

    public Ranking(){
        config = new Config("SkyBlock/");
        config.setPlugin(SkyBlockCore.plugin);
        ranks = new ArrayList<Rank>();
    }


    public void update(Player player){
        List<Integer> levels = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for(String name : config.fileListName()){
            SkyBlock skyBlock = new SkyBlock(name);
            int level = skyBlock.getLevel();
            ranks.add(new Rank(name,level));
        }
        Collections.sort(ranks, Collections.reverseOrder());
    }


    public static class Rank{
        private String name;

        private int level;

        public Rank(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }
    }


}
