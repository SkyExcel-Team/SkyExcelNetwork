package net.skyexcel.server.fish.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class FishData {
    private Player player; // 명령어를 실행시킨 플레이어.

    private Config config; //낚시 보관함을 불러올 객체

    private List<String> items = new ArrayList<>();


    public FishData(Player player) {
        this.player = player;
        this.config = new Config("data/fish");
        if(config.getConfig().get("fish." + player.getUniqueId()) == null){
            config.newArrayList("fish." + player.getUniqueId());
        }
    }

    public void addItem(Entity fish){
        

    }

    public void loadGUI(){ // 48, 45 NEXT, Previous


    }


}
