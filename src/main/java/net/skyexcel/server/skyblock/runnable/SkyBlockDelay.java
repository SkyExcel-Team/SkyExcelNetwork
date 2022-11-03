package net.skyexcel.server.skyblock.runnable;

import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.scheduler.BukkitRunnable;
import skyexcel.data.Time;
import skyexcel.data.file.Config;

public class SkyBlockDelay extends BukkitRunnable {

    private Time time;

    private Config config;

    private boolean isCommand;

    public SkyBlockDelay(SkyBlockPlayerData playerData, boolean isCommand) {
        this.isCommand = isCommand;
        this.time = new Time(1, 0, 0, 0);
        this.config = playerData.getConfig();
    }

    //커맨드에서 실행을 했는지 여부를 불러와서 true일 때에는 time값이 null 일 경우에도 설정을 시켜준다.
    @Override
    public void run() {
        if (config.getConfig().get("island.time") != null) {
            long timeData = config.getLong("island.time");
            Time newTime = new Time(timeData);

            newTime.minSecond(1);
            config.setLong("island.time", newTime.SECOND_IN_MILLIS());

        } else {
            if(isCommand){
                time.minSecond(1);
                config.setLong("island.time", time.SECOND_IN_MILLIS());
            }
        }
    }
}
