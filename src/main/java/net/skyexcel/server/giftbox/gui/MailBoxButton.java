package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class MailBoxButton extends Items {
    public MailBoxButton() {
        super("");
        getHeadItemFromHDB("18061");
        setCustomModelData(2);
        setDisplay("이전 페이지");
        setLore(List.of("§7클릭시 이전 페이지로 넘어감."));
    }
}
