package net.skyexcel.server.cosmetic.data;

import org.bukkit.Material;

public class Cosmetic {
    public enum BACK {
        NONE(Material.AIR, 0, ""),
        TEST1(Material.GOLD_INGOT, 1, "Test1"),
        TEST2(Material.GOLD_INGOT, 2, "Test2"),
        TEST3(Material.GOLD_INGOT, 3, "Test3"),
        TEST4(Material.GOLD_INGOT, 4, "Test4"),
        TEST5(Material.GOLD_INGOT, 5, "Test5");

        private final Material material;
        private final Integer customModelData;
        private final String name;

        private BACK(Material material, Integer customModelData, String name) {
            this.material = material;
            this.customModelData = customModelData;
            this.name = name;
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

        public String getName() {
            return this.name;
        }
    }


    public enum HAT {
        NONE(Material.AIR, 0, "'"),
        TEST1(Material.IRON_INGOT, 1, "Test1"),
        TEST2(Material.IRON_INGOT, 2, "Test2"),
        TEST3(Material.IRON_INGOT, 3, "Test3"),
        TEST4(Material.IRON_INGOT, 4, "Test4"),
        TEST5(Material.IRON_INGOT, 5, "Test5");

        private final Material material;
        private final Integer customModelData;
        private final String name;

        private HAT(Material material, Integer customModelData, String name) {
            this.material = material;
            this.customModelData = customModelData;
            this.name = name;
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

        public String getName() {
            return this.name;
        }
    }

    public enum OFFHAND {
        NONE(Material.AIR, 0, ""),
        TEST1(Material.COPPER_INGOT, 1, "Test1");

        private final Material material;
        private final Integer customModelData;
        private final String name;

        private OFFHAND(Material material, Integer customModelData, String name) {
            this.material = material;
            this.customModelData = customModelData;
            this.name = name;
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

        public String getName() {
            return this.name;
        }
    }
}
