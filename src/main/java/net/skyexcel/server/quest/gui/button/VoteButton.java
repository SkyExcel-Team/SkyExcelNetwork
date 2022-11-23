package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;

import java.util.List;

public class VoteButton extends Items {
    public VoteButton() {
        super("추천");
        getHeadItemFromHDB("56787");
        setCustomModelData(2);
        setLore(List.of("§7메뚜기팜 서버를 추천하여 이 퀘스트를 클리어하세요!"));
    }


}
