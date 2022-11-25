package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class FlyingQuestButton extends Button {
    public FlyingQuestButton() {
        super(14);
        setMaterial(Material.ELYTRA, 1);
        setDisplay("날기");
        setCustomModelData(1);
        setLore(List.of("§75분간 날기"));
    }
}
