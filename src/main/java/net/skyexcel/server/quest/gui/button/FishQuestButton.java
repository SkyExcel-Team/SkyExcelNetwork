package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class FishQuestButton extends Items {
    public FishQuestButton() {
        super("name");
        setMaterial(Material.FISHING_ROD, 1);
        setDisplay("물고기 낚기");
        setLore(List.of("§7B+ 이상의 물고기 2마리 낚기"));
    }
}
