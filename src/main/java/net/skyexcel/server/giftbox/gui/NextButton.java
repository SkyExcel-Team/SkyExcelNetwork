package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class NextButton extends Items {
    public NextButton() {
        getHeadItemFromHDB("31813");
        setCustomModelData(1);
        setDisplay("다음 페이지");
        setLore(List.of("§7클릭시 다음 페이지로 넘어감."));
    }
}