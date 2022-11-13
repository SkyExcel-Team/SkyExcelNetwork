package net.skyexcel.server.lockmanager.data;

import net.skyexcel.server.lockmanager.SkyExcelNetworkLock;
import net.skyexcel.server.lockmanager.packet.SignEdit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignData {

    private Config config;


    //TODO 잠금 상자 기능
    // 섬과 연동하여 잠금 표지판을 설치한 플레이어가 자기 섬이 아닐 경우,
    // 캔슬을 시킨다. 맴버가 자기 자신의 잠금을 설치 할 경우 허용이 되며 해당 옵션은
    // 어드민만 확인 할 수 있다. 캐시로 잠금 풀기권을 구매하여 꼭 필요할때 구매 하여 자신의 맴버의 상자를 볼 수 있다.
    // 아이템을 빼 가는것은 캔슬이 된다.

    public SignData() {

        this.config = new Config("data/sign/sign");
        this.config.setPlugin(SkyExcelNetworkLock.getPlugin());
    }

    public boolean removeSign(Player player) {
        if (player.isOp()) {

            return true;
        }
        return false;
    }


    /**
     * 데이터 저장 방식
     * rf
     *
     * @param player
     */
    public void addSign(Player player, Location sign, Location chest) {
        addObject("sign.players", player.getUniqueId());


    }

    public boolean openChest(Player player) {
        if (player.isOp()) {
            return true;
        }
        return false;
    }

    public void addObject(String path, Object obj) {
        if (config.getConfig().get(path) == null) {
            List<Object> uuid = new ArrayList<>();
            uuid.add(obj);
            config.getConfig().set(path, uuid);
            config.saveConfig();
        } else {
            List<Object> uuid = (List<Object>) config.getConfig().getList(path);
            uuid.add(obj);
            config.getConfig().set(path, uuid);
            config.saveConfig();
        }
    }

    /*
     * <p>name</p>
     * <p>name</p>
     * <p>name</p>
     * <p>name</p>
     * 이미 설치한
     *
     *
     * @param player 표지판을 설치한 플레이어
     * @param block 설치한 블록이 표지판인지 체크.
     */
    public void edit(Player player, Block block) {


//
//        String oneLine = (sign.getLine(0) != null ? sign.getLine(0) : "");
//        String twoLine = (sign.getLine(1) != null ? sign.getLine(1) : "");
//        String threeLine = (sign.getLine(2) != null ? sign.getLine(2) : "");
//        String fourLIne = (sign.getLine(3) != null ? sign.getLine(3) : "");
        signGUI(player, Arrays.asList("oneLine", "twoLine" ));

    }

    private void signGUI(Player target, List<String> lore) {
        SignEdit edit = new SignEdit(SkyExcelNetworkLock.getPlugin());
        SignEdit.Menu menu = edit.newMenu(List.of())
                .reopenIfFail(true)
                .response((player, strings) -> {
                    if (!strings[3].equalsIgnoreCase("sign")) {
                        player.sendMessage("Wrong!");
                        return false;
                    }
                    return true;
                });
        menu.open(target);
    }
}
