package net.skyexcel.server.items.data;

import org.bukkit.Material;

import java.util.List;

public class TestItem extends Items {
    public TestItem() {
        super("테스트");
    }

    @Override
    public void func() {
        System.out.println("테스트다 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");

        setMaterial(Material.GRASS);
        setAmount(1);
        setLore(List.of("테스트!"));
        setDisplay("하이");
        
    }
}
