package net.skyexcel.server.alphachest.struct;

import net.skyexcel.server.items.data.Items;
import org.bukkit.Material;

import java.util.List;

public class StorageItem extends Items {
    public StorageItem() {
        super("가상창고 이용권");
        setMaterial(Material.PAPER, 1);
        setDisplay("§a가상창고 이용권");
        setLore(List.of("§7우클릭시 아이템이 사용됩니다."));
    }

}
