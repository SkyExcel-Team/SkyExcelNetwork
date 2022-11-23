package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class WoodQuestButton extends Items {
    public WoodQuestButton() {
        super("나무 32개 캐기");
        setMaterial(Material.WOODEN_AXE, 1);
        setDisplay("나무 캐기");
        setLore(List.of("§7나무 32개 캐기"));
    }
}
