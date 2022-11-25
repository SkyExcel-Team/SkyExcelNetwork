package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class BlockPlaceQuestButton extends Button {


    private int now;

    public BlockPlaceQuestButton() {
        super(13);
        setMaterial(Material.GRASS_BLOCK, 1);
        setDisplay("블록 설치하기");
        setLore(List.of("§7320블록 설치", now + " / 320"));
    }

    public void setNow(int now) {
        this.now = now;
    }
}
