package net.skyexcel.server.items.data;

import org.bukkit.Material;

import java.util.List;

public class TestItem extends Items {
    public TestItem() {
        super("테스트");
        setMaterial(Material.GRASS);
        setAmount(1);
        setDisplay("하이");
        setLore(List.of("테스트!"));
    }
}
