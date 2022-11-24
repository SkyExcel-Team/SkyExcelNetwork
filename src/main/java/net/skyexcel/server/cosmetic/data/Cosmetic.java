package net.skyexcel.server.cosmetic.data;

import org.bukkit.Material;

public class Cosmetic {
    public enum BACK {
        TEST(Material.GOLD_INGOT, "1");

        final Material material;
        final String customModelData;

        BACK(Material material, String customModelData) {
            this.material = material;
            this.customModelData = customModelData;
        }

        public Material getType() {
            return this.material;
        }

        public CosmeticType getCosmeticType() {
            return CosmeticType.BACK;
        }

        public String getCustomModelData() {
            return this.customModelData;
        }
    }

    public enum HAT {
        TEST(Material.IRON_INGOT, "1");

        final Material material;
        final String customModelData;

        HAT(Material material, String customModelData) {
            this.material = material;
            this.customModelData = customModelData;
        }

        public Material getType() {
            return this.material;
        }

        public CosmeticType getCosmeticType() {
            return CosmeticType.HAT;
        }

        public String getCustomModelData() {
            return this.customModelData;
        }
    }

    public enum OFFHAND {
        TEST(Material.COPPER_INGOT, "1");

        final Material material;
        final String customModelData;

        OFFHAND(Material material, String customModelData) {
            this.material = material;
            this.customModelData = customModelData;
        }

        public Material getType() {
            return this.material;
        }

        public CosmeticType getCosmeticType() {
            return CosmeticType.OFFHAND;
        }

        public String getCustomModelData() {
            return this.customModelData;
        }
    }
}
