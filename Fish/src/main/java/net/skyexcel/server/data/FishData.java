package net.skyexcel.server.data;

import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class FishData {
    private Player player; // 명령어를 실행시킨 플레이어.

    private Config config; //낚시 보관함을 불러올 객체


    public FishData(Player player) {
        this.player = player;
    }


    public void loadGUI(){ // 48, 45 NEXT, Previous


    }


}
