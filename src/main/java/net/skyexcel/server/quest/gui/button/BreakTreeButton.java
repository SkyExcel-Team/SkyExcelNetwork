package net.skyexcel.server.quest.gui.button;

import org.bukkit.Material;

import java.util.List;

public class BreakTreeButton extends Button {
    public BreakTreeButton() {
        super(14);
        setMaterial(Material.WHEAT, 1);
        setDisplay("밀 캐기");
        setLore(List.of("§7밀 64개 캐기"));
    }
}
