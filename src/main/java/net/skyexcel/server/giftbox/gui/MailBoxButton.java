package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class MailBoxButton extends Items {
    public MailBoxButton() {
        getHeadItemFromHDB("2873");
        setCustomModelData(1);
        setDisplay("선물함");
        setLore(List.of("§7아이템을 우클릭시, 보상이 받아집니다."));
    }
}
