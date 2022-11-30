package net.skyexcel.server.chatchannel.gui;

import net.skyexcel.api.util.Translate;
import net.skyexcel.server.chatchannel.SkyExcelNetworkChatChannelMain;
import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class LogButton extends Items {

    private Translate translate = new Translate();

    public LogButton(String log) {
        String date = log.split(SkyExcelNetworkChatChannelMain.split)[0];

        String type = log.split(SkyExcelNetworkChatChannelMain.split)[1];

        String name = log.split(SkyExcelNetworkChatChannelMain.split)[2];

        String msg = translate.collapse(log.split("::>"), 3);

        setMaterial(Material.PAPER, 1);
        setDisplay("§a채팅 로그");
        setLore(List.of("§f날짜 > " + date,
                "§f채팅 타입 > " + type,
                "§f닉네임 > " + name,
                "§f메세지 > " + msg));
    }
}
