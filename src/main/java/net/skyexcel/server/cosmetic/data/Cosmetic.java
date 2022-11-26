package net.skyexcel.server.cosmetic.data;

import org.bukkit.Material;

public class Cosmetic {
    public enum BACK {
        NONE(Material.AIR, 0),
        TEST1(Material.GOLD_INGOT, 1),
        TEST2(Material.GOLD_INGOT, 2),
        TEST3(Material.GOLD_INGOT, 3),
        TEST4(Material.GOLD_INGOT, 4),
        TEST5(Material.GOLD_INGOT, 5);

        private final Material material;
        private final Integer customModelData;

        BACK(Material material, Integer customModelData) {
            this.material = material;
            this.customModelData = customModelData;
        }

        public Material getType() {
            return this.material;
        }

        public CosmeticType getCosmeticType() {
            return CosmeticType.BACK;
        }

        public Integer getCustomModelData() {
            return this.customModelData;
        }
    }


    public enum HAT {
        NONE(Material.AIR, 0),
        TEST1(Material.IRON_INGOT, 1),
        TEST2(Material.IRON_INGOT, 2),
        TEST3(Material.IRON_INGOT, 3),
        TEST4(Material.IRON_INGOT, 4),
        TEST5(Material.IRON_INGOT, 5);

        private final Material material;
        private final Integer customModelData;

        HAT(Material material, Integer customModelData) {
            this.material = material;
            this.customModelData = customModelData;
        }

        public Material getType() {
            return this.material;
        }

        public CosmeticType getCosmeticType() {
            return CosmeticType.HAT;
        }

        public Integer getCustomModelData() {
            return this.customModelData;
        }
    }

    public enum OFFHAND {
        NONE(Material.AIR, 0),
        TEST1(Material.COPPER_INGOT, 1),
        TEST2(Material.COPPER_INGOT, 2),
        TEST3(Material.COPPER_INGOT, 3),
        TEST4(Material.COPPER_INGOT, 4),
        TEST5(Material.COPPER_INGOT, 5);

        private final Material material;
        private final Integer customModelData;

        OFFHAND(Material material, Integer customModelData) {
            this.material = material;
            this.customModelData = customModelData;
        }

        public Material getType() {
            return this.material;
        }

        public CosmeticType getCosmeticType() {
            return CosmeticType.OFFHAND;
        }

        public Integer getCustomModelData() {
            return this.customModelData;
        }
    }
}
