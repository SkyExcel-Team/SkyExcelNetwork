package net.skyexcel.server.ui.title;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;


public class Loading extends BukkitRunnable {

    private Player player;

    private int cooltime = 4;
    private int index = -1;

    private String on;
    private String off;

    private Consumer end;

    private final String[] uni = {"%img_loadingbar_3%" + "%img_loadingbar_3%" + "%img_loadingbar_3%" + "%img_loadingbar_3%" + "%img_loadingbar_3%",
            "%img_loadingbar_1%" + "%img_loadingbar_3%" + "%img_loadingbar_3%" + "%img_loadingbar_3%" + "%img_loadingbar_4%",
            "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_3%" + "%img_loadingbar_3%" + "%img_loadingbar_4%",
            "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_3%" + "%img_loadingbar_4%",
            "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_4%",
            "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_1%" + "%img_loadingbar_1%"};

    public Loading(Player player, int cooltime) {
        this.player = player;
        this.cooltime = cooltime;
        player.sendMessage("섬 제작중...");
    }

    /**
     * 로딩바가 끝나는 액션을 설정 할 수 있다.
     */
    public <T> void end(Consumer<T> end) {
        this.end = end;
    }

    @Override
    public void run() {
        if (cooltime != -1) {
            cooltime--;
            player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
            player.sendTitle("", PlaceholderAPI.setPlaceholders(player, uni[++index]), 20, 20, 1);
        } else {
            this.end.accept(end);
            cancel();
        }
    }
}
