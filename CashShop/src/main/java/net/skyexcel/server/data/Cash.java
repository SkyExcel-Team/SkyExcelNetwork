package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class Cash  {

    private final long MAX_INTEGER = Long.MAX_VALUE;

    private OfflinePlayer player;

    private Config config;

    public Cash(OfflinePlayer player) {
        this.player = player;
        this.config = new Config("data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetwork.plugin);
    }

    /**
     * 만약, 플레이어의 돈이 -1, 즉 Null일 경우에는 setMoney를 통해 amount 값을 초기 값으로 지정해준다.
     *
     * @param amount 입금 할 금액
     */
    public void deposit(long amount) {
        if (getAmount() != -1) {
            long result = getAmount() + amount;
            Set(result);
        } else {
            Set(amount);
        }
    }

    public boolean withdraw(long amount) {
        long result = getAmount() - amount;

        if (result > 0) {
            Set(getAmount() - amount);
            return true;
        }
        return false;
    }

    public void Set(long amount) {
        config.getConfig().set("name", player.getName());
        config.getConfig().set("money", amount);
        config.saveConfig();

    }

    public long getAmount() {
        return (config.getConfig().get("money") != null ? config.getConfig().getLong("money") : -1);
    }
}
