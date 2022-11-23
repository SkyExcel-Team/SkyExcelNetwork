package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class WheatQuestButton extends Items {
    public WheatQuestButton() {
        super("밀 캐기");
        setMaterial(Material.WHEAT, 1);
        setDisplay("밀 캐기");
        setLore(List.of("§7밀 64개 캐기"));
    }
}
