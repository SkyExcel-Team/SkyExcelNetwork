package net.skyexcel.server.giftbox.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class PreviousButton extends Items {
    public PreviousButton() {
        super("");
        getHeadItemFromHDB("701");
        setCustomModelData(1);
        setDisplay("§c← §8[ §7이전페이지 §8]");
        setLore(List.of("§f架 §7클릭시 이전 페이지로 돌아갑니다"));
    }
}
