package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class MailBoxButton extends Items {
    public MailBoxButton() {
        getHeadItemFromHDB("2873");
        setCustomModelData(1);
        setDisplay("§8[ §7선물함 §8]");
        setLore(List.of("§f佳 §7아이템을 우클릭시, 아이템이 받아집니다."));
    }
}
