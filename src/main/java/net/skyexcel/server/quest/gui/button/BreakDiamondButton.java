package net.skyexcel.server.quest.gui.button;

import org.bukkit.Material;

import java.util.List;

public class BreakDiamondButton extends Button{
    public BreakDiamondButton() {
        super(16);
        setMaterial(Material.DIAMOND_PICKAXE, 1);
        setDisplay("다이아몬드 캐기");
        setLore(List.of("§7테스트"));
    }
}
