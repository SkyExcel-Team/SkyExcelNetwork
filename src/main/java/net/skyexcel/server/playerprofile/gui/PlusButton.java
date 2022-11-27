package net.skyexcel.server.playerprofile.gui;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class PlusButton extends Items {
    public PlusButton() {
        getHeadItemFromHDB("9885");
        setDisplay("인기도 올리기");
        setLore(List.of("§7클릭시 인기도를 올립니다."));
        setCustomModelData(1);
    }
}
