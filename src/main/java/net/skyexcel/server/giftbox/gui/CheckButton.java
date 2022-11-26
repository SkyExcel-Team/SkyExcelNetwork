package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class CheckButton extends Items {
    public CheckButton() {
        getHeadItemFromHDB("56787");
        setCustomModelData(1);
        setDisplay("모든 아이템 받기");
        setLore(List.of("§7클릭시 모든 아이템 받아짐"));
    }
}
