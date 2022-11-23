package net.skyexcel.server.fish.data;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public enum FishType {

    BIGBILLYBASS("§x§0§a§3§a§0§0매§x§2§2§5§c§2§0우§x§3§a§7§e§4§1큰§x§5§1§a§0§6§1입 §x§6§9§c§2§8§2우§x§8§1§e§4§a§2럭", 1, FishRank.S),
    COLOSSALARAPAIMA("§x§4§4§3§0§0§d피§x§6§0§4§4§1§2라§x§7§c§5§7§1§8루§x§9§8§6§b§1§d크", 2, FishRank.S),
    FLAREFLOUNDER("§x§f§e§7§0§1§3넙§x§d§a§b§8§0§0치", 38, FishRank.S),
    GLEAMINGRAINBOWTROUT("§x§f§f§5§8§5§8무§x§f§d§d§6§3§6지§x§4§b§f§f§5§d개 §x§5§9§3§3§c§d송§x§c§0§4§b§d§3어", 39, FishRank.S),
    GOLIATHGROUPER("§x§2§a§2§9§2§a골§x§3§5§2§9§1§7리§x§4§f§3§7§1§3앗§x§8§8§6§2§2§d참§x§6§c§4§f§2§7바§x§2§5§1§d§1§1리", 40, FishRank.S),
    SLIMYCATFISH("§x§2§9§3§2§1§f메§x§8§2§b§1§6§b기", 41, FishRank.S),
    TOOTHFISH("§x§0§e§3§a§3§a비§x§1§8§6§4§6§4막§x§2§0§7§a§8§6치§x§2§7§7§b§a§0어", 42, FishRank.S),

    COOKEDALIGATORGAR("구운 앨리게이터가아", 3),
    COOKEDANCHOVY("구운 앤초비", 4),
    COOKEDBROWNTROUT("구운 브라운 송어", 5),
    COOKEDBULLHEADCATFISH("구운 눈동자개", 6),
    COOKEDCHERRYSHRIMP("구운 체리새우", 7),
    COOKEDCRAPPIE("구운 크래피", 8),
    COOKEDCRAYFISH("구운 가재", 9),
    COOKEDDESERTSUNFISH("구운 사막 개복치", 10),
    COOKEDELECTRICEEL("구운 전기뱀장어", 11),
    COOKEDFAIRYSHRIMP("구운 풍년새우", 12),
    COOKEDFORESTCRAYFISH("구운 산 가재", 13),
    COOKEDFORESTSUNFISH("구운 산 개복치", 14),
    COOKEDJUNGLECATFISH("구운 정글 메기", 15),
    COOKEDLARGEMOUTHBASS("작은입 우럭", 16),
    COOKEDLIONFISH("구운 쏠배감펭속", 17),
    COOKEDMANDARIN("구운 만다린", 18),
    COOKEDMUDCARP("구운 머드 잉어", 19),
    COOKEDOCTOPUS("구운 문어", 20),
    COOKEDPERCH("구운 농어", 21),
    COOKEDPIKE("구운 강꼬치고기", 22),
    COOKEDPIRANHA("구운 피라냐", 23),
    COOKEDPUPFISH("구운 펍피쉬", 24),
    COOKEDRIVERCRAYFISH("구운 민물 가재", 25),
    COOKEDRIVERSUNFISH("구운 민물 개복치", 26),
    COOKEDSANDFISH("구운 도루묵", 27),
    COOKEDSCORPIONCARP("구운 전갈 잉어", 28),
    COOKEDSMALLMOUTHBASS("구운 큰입 우럭", 29),
    COOKEDSOLE("구운 서대기", 30),
    COOKEDSTURGEON("구운 철갑상어", 31),
    COOKEDSWAMPCATFISH("구운 늪 메기", 32),
    COOKEDSWAMPSUNFISH("구운 늪 개복치", 33),
    COOKEDTAMBAQUI("구운 땀바끼", 34),
    COOKEDTIGERTROUT("구운 타이거 송어", 35),
    COOKEDTILAPIA("구운 틸라피아", 36),
    COOKEDTUNA("구운 참치", 37),

    RAWALIGATORGAR("§x§5§2§b§c§2§1생 악어 가르", 43, FishRank.B),
    RAWANCHOVY("§x§2§c§2§d§2§c생 앤초비", 44, FishRank.B),
    RAWBROWNTROUT("§x§9§8§5§2§0§6생 브라운 송어", 45, FishRank.B),
    RAWBULLHEADCATFISH("§x§8§e§4§c§0§6생 눈동자개", 46, FishRank.B),
    RAWCHERRYSHRIMP("§x§b§7§0§6§0§6생 체리새우", 47, FishRank.C),
    RAWCRAPPIE("생 크래피", 48, FishRank.B),
    RAWCRAYFISH("생 가재", 49, FishRank.C),
    RAWDESERTSUNFISH("생 사막 개복치", 50, FishRank.B),
    RAWELECTRICEEL("생 전기뱀장어", 51, FishRank.B),
    RAWFAIRYSHRIMP("생 풍년 새우", 52, FishRank.C),
    RAWFORESTCRAYFISH("생 산 가재", 53, FishRank.B),
    RAWFORESTSUNFISH("생 산 개복치", 54, FishRank.B),
    RAWJUNGLECATFISH("생 정글 메기", 55, FishRank.B),
    RAWLARGEMOUTHBASS("생 큰입 우럭", 56, FishRank.A),
    RAWLIONFISH("생 쏠배감펭속", 57, FishRank.A),
    RAWMANDARIN("생 만다린", 58, FishRank.A),

    RAWOCTOPUS("생 문어", 60, FishRank.C),
    RAWPERCH("생 농어", 61, FishRank.C),
    RAWPIKE("생 강꼬치고기", 62, FishRank.C),
    RAWPIRANHA("생 피라냐", 63, FishRank.C),
    RAWPUPFISH("생 펍피쉬", 64, FishRank.B),
    RAWRIVERCRAYFISH("생 민물 가재", 65, FishRank.C),
    RAWRIVERSUNFISH("생 민물 개복치", 66, FishRank.B),
    RAWSANDFISH("생 도루묵", 67, FishRank.C),
    RAWSCORPIONCARP("생 전갈 잉어", 68, FishRank.C),
    RAWSMALLMOUTHBASS("생 작은입 우럭", 69, FishRank.C),
    RAWSOLE("생 서대기", 70, FishRank.C),
    RAWSTURGEON("생 철갑상어", 71, FishRank.B),
    RAWSWAMPCATFISH("생 늪 메기", 72, FishRank.C),
    RAWSWAMPSUNFISH("생 늪 개복치", 73, FishRank.B),
    RAWTAMBAQUI("생 땀바끼", 74, FishRank.B),
    RAWTIGERTROUT("생 타이거 송어", 75, FishRank.C),
    RAWTILAPIA("§x§2§c§2§d§2§c생 틸라피아", 76, FishRank.C),
    NULL();


    private String translate;
    private int modelData;

    private int size;

    private Material material = Material.COD;

    private FishRank fishRank;

    private FishType fishType;


    FishType() {

    }

    FishType(String translate, int modelData) {
        this.translate = translate;
        this.modelData = modelData;
    }

    FishType(String translate, int modelData, FishRank fishRank) {
        this.translate = translate;
        this.modelData = modelData;
        this.fishRank = fishRank;
    }

    public ItemStack item(int amount) {

        ItemStack itemStack = new ItemStack(material, amount);

        ItemMeta meta = itemStack.getItemMeta();

        if (fishRank != null) {
            itemStack.addUnsafeEnchantment(Enchantment.CHANNELING, 1);

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "§8[" + fishRank.getName() + "§8] " + translate + " §7(" + size + "cm)"));
            meta.setCustomModelData(modelData);

            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }

    public FishType fishRankUp(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();

        if (hasRank()) {
            switch (getFishRank()) {
                case S -> {
                    setFishRank(FishRank.SPlus);

                    meta.addEnchant(Enchantment.CHANNELING, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    itemStack.setItemMeta(meta);
                }
                case A -> {
                    setFishRank(FishRank.APlus);

                    meta.addEnchant(Enchantment.CHANNELING, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    itemStack.setItemMeta(meta);
                }
                case B -> {
                    setFishRank(FishRank.BPlus);

                    meta.addEnchant(Enchantment.CHANNELING, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    itemStack.setItemMeta(meta);
                }
                case C -> {
                    setFishRank(FishRank.CPlus);

                    meta.addEnchant(Enchantment.CHANNELING, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    itemStack.setItemMeta(meta);
                }
            }
        }
        return this;
    }

    public void setFishRank(FishRank fishRank) {
        this.fishRank = fishRank;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FishRank getFishRank() {
        return fishRank;
    }

    public boolean hasRank() {
        return getFishRank() != null;
    }

    public Material getMaterial() {
        return material;
    }

    public String getTranslate() {
        return translate;
    }
}
