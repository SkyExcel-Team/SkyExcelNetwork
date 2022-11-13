package net.skyexcel.server.fish.event;

import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.fish.data.FishData;
import net.skyexcel.server.fish.data.FishStatus;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import skyexcel.util.ActionBar;

import java.util.Random;

public class FishEvent implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Entity fish = event.getCaught();

        int size;

        String[] test = {"철갑상어", "자이언트 구라미", "도라도", "아로와나", "가물치", "메기", "뱀장어", "쏘가리"};

        Random random = new Random();
        if (fish != null) {
            if (getPercent(85)) {
                size = random.nextInt(10) + 1; // 사이즈

                int index = random.nextInt(test.length - 1);
                String name = test[index]; //이름 설정

                ActionBar.sendMessage(player, size + "cm 크기의 " + name + " 물고기를 낚았습니다! ");
            } else {
                size = random.nextInt(20 - 10 + 1) + 10;

                int index = random.nextInt(test.length - 1);
                String name = test[index];
                ActionBar.sendMessage(player, ChatColor.YELLOW + "" + size + ChatColor.WHITE + "cm 크기의 " + name + " 물고기를 낚았습니다! ");
            }
        }
    }

    @EventHandler
    public void pickUp(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();

    }

    public boolean getPercent(int percent) {
        double result = (double) percent / (double) 100 * 100.0;
        int rand = new Random().nextInt(100) + 1;
        return rand <= result;
    }
}
