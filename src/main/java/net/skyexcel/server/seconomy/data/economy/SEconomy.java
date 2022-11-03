package net.skyexcel.server.seconomy.data.economy;

import net.skyexcel.server.seconomy.SkyExcelNetworkSEConomyMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class SEconomy {


//    private Config config;

    private final long MAX_INTEGER = Long.MAX_VALUE;

    private OfflinePlayer player;

    private Config config;

    public SEconomy(OfflinePlayer player) {
        this.player = player;
        this.config = new Config("SEConomy-data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkSEConomyMain.plugin);
    }

    /**
     * 만약, 플레이어의 돈이 -1, 즉 Null일 경우에는 setMoney를 통해 amount 값을 초기 값으로 지정해준다.
     *
     * @param amount 입금 할 금액
     */
    public void deposit(long amount) {
        if (getMoney() != -1) {
            long result = getMoney() + amount;
            setMoney(result);
        } else {
            setMoney(amount);
        }
    }

    public boolean withdraw(long amount) {
        long result = getMoney() - amount;

        if (result > 0) {
            setMoney(getMoney() - amount);
            return true;
        }
        return false;
    }

    public void setMoney(long amount) {
        config.getConfig().set("name", player.getName());
        config.getConfig().set("money", amount);
        config.saveConfig();

    }

    public long getMoney() {
        return (config.getConfig().get("money") != null ? config.getConfig().getLong("money") : -1);
    }
}
