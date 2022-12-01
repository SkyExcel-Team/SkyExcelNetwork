package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class NextButton extends Items {
    public NextButton() {
        getHeadItemFromHDB("701");
        setCustomModelData(1);
        setDisplay("§8[ §7다음페이지 §8] §c→");
        setLore(List.of("§f架 §7클릭시 다음 페이지로 넘어갑니다"));
    }
}