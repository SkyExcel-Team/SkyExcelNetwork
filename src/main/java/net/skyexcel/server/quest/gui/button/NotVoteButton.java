package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;


import java.util.List;

public class NotVoteButton extends Button {


    public NotVoteButton() {
        super(10);
        getHeadItemFromHDB("9382");
        setDisplay("§c아직 추천안함");
        setCustomModelData(1);
        setLore(List.of("§7메뚜기팜 서버를 추천하여 이 퀘스트를 클리어하세요!"));
    }
}
