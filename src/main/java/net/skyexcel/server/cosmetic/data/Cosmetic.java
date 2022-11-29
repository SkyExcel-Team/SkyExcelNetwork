package net.skyexcel.server.cosmetic.data;

import org.bukkit.Material;

public class Cosmetic {
    public enum BACK {
        NONE(Material.AIR, 0, ""),
        BUNNY_BACKPACK(Material.GOLD_INGOT, 1, "Bunny Backpack [토끼 가방]"),
        COLORFUL_BACKPACK(Material.GOLD_INGOT, 2, "Colorful Backpack [화려한 가방]"),
        FRUIT_BASKET(Material.GOLD_INGOT, 3, "Fruit Basket [과일 바스켓]"),
        WARRIOR_BELT(Material.GOLD_INGOT, 4, "Warrior Belt [전사의 허리띠]"),
        MERCY_WINGS(Material.GOLD_INGOT, 5, "Mercy Wings [메르시의 날개]"),
        ANGEL_WINGS(Material.GOLD_INGOT, 6, "Angel Wings [천사의 날개]"),
        CYBER_MACHINE(Material.GOLD_INGOT, 7, "Cyber Machine [사이버 머신]"),
        CYBER_MACHINE_BLUE(Material.GOLD_INGOT, 8, "Cyber Machine(Blue) [파란 사이버 머신]"),
        CYBER_MACHINE_RED(Material.GOLD_INGOT, 9, "Cyber Machine(Red) [빨간 사이버 머신]"),
        DEMON_WINGS(Material.GOLD_INGOT, 10, "Demon Wings [악마의 날개]"),
        DRAGON_WINGS(Material.GOLD_INGOT, 11, "Dragon Wings [용의 날개]"),
        DRAGON_WINGS_BLUE(Material.GOLD_INGOT, 12, "Dragon Wings(Blue) [파란 용의 날개]"),
        DRAGON_WINGS_RED(Material.GOLD_INGOT, 13, "Dragon Wings(Red) [빨간 용의 날개]"),
        MYTHICAL_BLADES(Material.GOLD_INGOT, 14, "Mythical Blades [신화의 검]"),
        PHOENIX_WINGS(Material.GOLD_INGOT, 15, "Phoenix Wings [피닉스의 날개]"),
        BASIC_BACKPACK(Material.GOLD_INGOT, 16, "Basic Backpack [평범한 가방]"),
        CAMPER_BACKPACK(Material.GOLD_INGOT, 17, "Camper Backpack [캠핑 가방]"),
        FROG_BACKPACK(Material.GOLD_INGOT, 18, "Frog Backpack [개구리 가방]"),
        SCHOOL_BACKPACK(Material.GOLD_INGOT, 19, "School Backpack [책 가방]"),
        GOTH_BACKPACK(Material.GOLD_INGOT, 20, "Goth Backpack [고스 가방]"),
        TEDDY_BEAR_BACKPACK(Material.GOLD_INGOT, 21, "TeddyBear Backpack [곰돌이 가방]"),
        BUTTERFLY_WINGS(Material.GOLD_INGOT, 22, "Butterfly Wings [나비의 날개]"),
        CRYSTAL_WINGS(Material.GOLD_INGOT, 23, "Crystal Wings [수정의 날개]"),
        DARKNESS_WINGS(Material.GOLD_INGOT, 24, "Darkness Wings [암흑의 날개]"),
        FAIRY_WINGS(Material.GOLD_INGOT, 25, "Fairy Wings [정령의 날개]"),
        PINK_WINGS(Material.GOLD_INGOT, 26, "Pink Wings [분홍 날개]"),
        TEST1(Material.GOLD_INGOT, 26, "Pink Wings [분홍 날개1]"),
        TEST2(Material.GOLD_INGOT, 26, "Pink Wings [분홍 날개2]"),
        TEST3(Material.GOLD_INGOT, 26, "Pink Wings [분홍 날개3]"),
        TEST4(Material.GOLD_INGOT, 26, "Pink Wings [분홍 날개4]"),
        TEST5(Material.GOLD_INGOT, 26, "Pink Wings [분홍 날개5]");

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

        public static BACK valueOfName(String name) {
            for(BACK e: BACK.values()) {
                if(e.name.equalsIgnoreCase(name)) {
                    return e;
                }
            }

            return null;
        }
    }


    public enum HAT {
        NONE(Material.AIR, 0, ""); //TODO

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

        public static HAT valueOfName(String name) {
            for(HAT e: HAT.values()) {
                if(e.name.equalsIgnoreCase(name)) {
                    return e;
                }
            }

            return null;
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
