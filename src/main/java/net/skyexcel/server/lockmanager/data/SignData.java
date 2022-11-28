package net.skyexcel.server.lockmanager.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class SignData {

    private Config config;

    private List<List<Location>> location = new ArrayList<>();

    private List<List<Player>> players = new ArrayList<>();


    public SignData() {
        config = new Config("lockmanager/sign");
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    //TODO 잠금 상자 기능
    // 섬과 연동하여 잠금 표지판을 설치한 플레이어가 자기 섬이 아닐 경우, 캔슬을 시킨다.
    // 맴버가 자기 자신의 잠금을 설치 할 경우 허용이 되며 해당 옵션은 어드민만 확인 할 수 있다.
    // 캐시로 잠금 풀기권을 구매하여 꼭 필요할때 구매 하여 자신의 맴버의 상자를 볼 수 있다. (아이템을 빼 가는것은 캔슬이 된다)


    public void addPlayer() {

    }
}
