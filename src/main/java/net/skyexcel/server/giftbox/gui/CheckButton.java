package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class CheckButton extends Items {
    public CheckButton() {
        getHeadItemFromHDB("56787");
        setCustomModelData(1);
        setDisplay("§8[ §7모든 아이템 받기 §8]");
        setLore(List.of("§f佳 §7클릭시 모든 아이템이 받아집니다!"));
    }
}
