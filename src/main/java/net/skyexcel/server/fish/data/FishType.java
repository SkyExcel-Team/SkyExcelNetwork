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

    COOKEDALIGATORGAR("§x§7§2§5§a§5§a구운 §x§7§2§4§d§4§d앨리게이터가아", 3),
    COOKEDANCHOVY("§x§7§2§5§a§5§a구운 §x§2§c§2§d§2§c앤초비", 4),
    COOKEDBROWNTROUT("§x§7§2§5§a§5§a구운 §x§9§8§5§2§0§6브라운 송어", 5),
    COOKEDBULLHEADCATFISH("§x§7§2§5§a§5§a구운 §x§8§e§4§c§0§6눈동자개", 6),
    COOKEDCHERRYSHRIMP("§x§7§2§5§a§5§a구운 §x§b§7§0§6§0§6체리새우", 7),
    COOKEDCRAPPIE("§x§7§2§5§a§5§a구운 §x§0§1§d§5§4§a크래피", 8),
    COOKEDCRAYFISH("§x§7§2§5§a§5§a구운 §x§a§a§4§8§0§6가재", 9),
    COOKEDDESERTSUNFISH("§x§7§2§5§a§5§a구운 §x§e§2§b§9§6§4사막 개복치", 10),
    COOKEDELECTRICEEL("§x§7§2§5§a§5§a구운 §x§4§d§6§a§9§d전기뱀장어", 11),
    COOKEDFAIRYSHRIMP("§x§7§2§5§a§5§a구운 §x§d§0§6§4§8§e풍년새우", 12),
    COOKEDFORESTCRAYFISH("§x§7§2§5§a§5§a구운 §x§7§5§8§6§a§7산 가재", 13),
    COOKEDFORESTSUNFISH("§x§7§2§5§a§5§a구운 §x§a§7§7§c§0§3산 개복치", 14),
    COOKEDJUNGLECATFISH("§x§7§2§5§a§5§a구운 §x§7§d§9§5§6§0정글 메기", 15),
    COOKEDLARGEMOUTHBASS("§x§7§2§5§a§5§a구운 §x§4§e§6§5§a§a작은입 우럭", 16),
    COOKEDLIONFISH("§x§7§2§5§a§5§a구운 §x§8§4§0§3§0§3쏠§x§9§2§2§2§1§b배§x§a§0§4§1§3§4감§x§a§e§5§f§4§c펭§x§b§c§7§e§6§4속", 17),
    COOKEDMANDARIN("§x§7§2§5§a§5§a구운 만§x§1§d§8§7§8§b다§x§9§d§4§5§0§6린", 18),
    COOKEDMUDCARP("§x§7§2§5§a§5§a구운 §x§6§f§6§2§5§f머드 잉어", 19),
    COOKEDOCTOPUS("§x§7§2§5§a§5§a구운 §x§5§c§0§4§7§7문어", 20),
    COOKEDPERCH("§x§7§2§5§a§5§a구운 §x§0§0§6§7§8§e농어", 21),
    COOKEDPIKE("§x§7§2§5§a§5§a구운 §x§4§d§c§3§d§3강§x§a§0§9§b§9§1꼬§x§a§0§9§b§9§1치§x§a§0§9§b§9§1고§x§4§d§c§3§d§3기", 22),
    COOKEDPIRANHA("§x§7§2§5§a§5§a구운 §x§4§b§7§4§0§0피라냐", 23),
    COOKEDPUPFISH("§x§7§2§5§a§5§a구운 §x§0§5§2§4§7§4펍§x§0§7§3§a§b§9피§x§a§2§5§6§0§7쉬", 24),
    COOKEDRIVERCRAYFISH("§x§7§2§5§a§5§a구운 §x§a§5§5§5§0§7민§x§0§0§8§e§d§5물§x§0§0§8§e§d§5가§x§8§4§4§4§0§6재", 25),
    COOKEDRIVERSUNFISH("§x§7§2§5§a§5§a구운 §x§a§f§8§2§0§2민물 개복치", 26),
    COOKEDSANDFISH("§x§7§2§5§a§5§a구운 §x§a§f§8§2§0§2도루묵", 27),
    COOKEDSCORPIONCARP("§x§7§2§5§a§5§a구운 §x§d§3§5§8§1§0전갈 잉어", 28),
    COOKEDSMALLMOUTHBASS("§x§7§2§5§a§5§a구운 §x§5§3§6§2§3§c큰입 우럭", 29),
    COOKEDSOLE("§x§7§2§5§a§5§a구운 §x§8§d§9§0§8§d서대기", 30),
    COOKEDSTURGEON("§x§7§2§5§a§5§a구운 §x§0§0§6§3§8§b철갑상어", 31),
    COOKEDSWAMPCATFISH("§x§7§2§5§a§5§a구운 §x§0§0§6§3§8§b늪 §x§0§0§9§5§d§5메§x§5§a§e§1§f§f기", 32),
    COOKEDSWAMPSUNFISH("§x§7§2§5§a§5§a구운 §x§a§5§7§b§0§4늪 개복치", 33),
    COOKEDTAMBAQUI("§x§7§2§5§a§5§a구운 §x§3§a§6§2§0§0땀§x§e§2§a§6§5§a바§x§f§f§4§b§4§b끼", 34),
    COOKEDTIGERTROUT("§x§7§2§5§a§5§a구운 §x§5§3§3§c§0§1타§x§7§9§5§7§0§2이§x§9§b§6§f§0§3거 §x§7§9§5§7§0§2송§x§f§f§7§b§7§b어", 35),
    COOKEDTILAPIA("§x§7§2§5§a§5§a구운 §x§2§c§2§d§2§c틸라피아", 36),
    COOKEDTUNA("§x§7§2§5§a§5§a구운 §x§4§c§6§c§d§d참치", 37),

    RAWALIGATORGAR("§x§5§2§b§c§2§1생 악어 가르", 43, FishRank.B),
    RAWANCHOVY("§x§2§c§2§d§2§c생 앤초비", 44, FishRank.B),
    RAWBROWNTROUT("§x§9§8§5§2§0§6생 브라운 송어", 45, FishRank.B),
    RAWBULLHEADCATFISH("§x§8§e§4§c§0§6생 눈동자개", 46, FishRank.B),
    RAWCHERRYSHRIMP("§x§b§7§0§6§0§6생 체리새우", 47, FishRank.C),
    RAWCRAPPIE("§x§0§1§d§5§4§a생 크래피", 48, FishRank.B),
    RAWCRAYFISH("§x§a§a§4§8§0§6생 가재", 49, FishRank.C),
    RAWDESERTSUNFISH("§x§e§2§b§9§6§4생 사막 개복치", 50, FishRank.B),
    RAWELECTRICEEL("§x§4§d§6§a§9§d생 전기뱀장어", 51, FishRank.B),
    RAWFAIRYSHRIMP("§x§d§0§6§4§8§e생 풍년새우", 52, FishRank.C),
    RAWFORESTCRAYFISH("§x§7§5§8§6§a§7생 산 가재", 53, FishRank.B),
    RAWFORESTSUNFISH("§x§a§7§7§c§0§3생 산 개복치", 54, FishRank.B),
    RAWJUNGLECATFISH("§x§7§d§9§5§6§0생 정글 메기", 55, FishRank.B),
    RAWLARGEMOUTHBASS("§x§5§3§6§2§3§c생 큰입 우럭", 56, FishRank.A),
    RAWLIONFISH("§x§8§4§0§3§0§3생 쏠§x§9§2§2§2§1§b배§x§a§0§4§1§3§4감§x§a§e§5§f§4§c펭§x§b§c§7§e§6§4속", 57, FishRank.A),
    RAWMANDARIN("§x§0§6§2§f§9§3생 만§x§1§d§8§7§8§b다§x§9§d§4§5§0§6린", 58, FishRank.A),

    RAWOCTOPUS("§x§5§c§0§4§7§7생 문어", 60, FishRank.C),
    RAWPERCH("§x§0§0§6§7§8§e생 농어", 61, FishRank.C),
    RAWPIKE("§x§4§d§c§3§d§3생 강§x§a§0§9§b§9§1꼬§x§a§0§9§b§9§1치§x§a§0§9§b§9§1고§x§4§d§c§3§d§3기", 62, FishRank.C),
    RAWPIRANHA("§x§4§b§7§4§0§0생 피라냐", 63, FishRank.C),
    RAWPUPFISH("§x§0§5§2§4§7§4생 펍§x§0§7§3§a§b§9피§x§a§2§5§6§0§7쉬", 64, FishRank.B),
    RAWRIVERCRAYFISH("§x§a§5§5§5§0§7생 민§x§0§0§8§e§d§5물§x§0§0§8§e§d§5가§x§8§4§4§4§0§6재", 65, FishRank.C),
    RAWRIVERSUNFISH("§x§a§f§8§2§0§2생 민물 개복치", 66, FishRank.B),
    RAWSANDFISH("§x§a§f§8§2§0§2생 도루묵", 67, FishRank.C),
    RAWSCORPIONCARP("§x§d§3§5§8§1§0생 전갈 잉어", 68, FishRank.C),
    RAWSMALLMOUTHBASS("§x§4§e§6§5§a§a생 작은입 우럭", 69, FishRank.C),
    RAWSOLE("§x§8§d§9§0§8§d생 서대기", 70, FishRank.C),
    RAWSTURGEON("§x§0§0§6§3§8§b생 철갑상어", 71, FishRank.B),
    RAWSWAMPCATFISH("§x§0§0§6§3§8§b생 늪 §x§0§0§9§5§d§5메§x§5§a§e§1§f§f기", 72, FishRank.C),
    RAWSWAMPSUNFISH("§x§a§5§7§b§0§4생 늪 개복치", 73, FishRank.B),
    RAWTAMBAQUI("§x§3§a§6§2§0§0생 땀§x§e§2§a§6§5§a바§x§f§f§4§b§4§b끼", 74, FishRank.B),
    RAWTIGERTROUT("§x§5§3§3§c§0§1생 타§x§7§9§5§7§0§2이§x§9§b§6§f§0§3거 §x§7§9§5§7§0§2송§x§f§f§7§b§7§b어", 75, FishRank.C),
    RAWTILAPIA("§x§2§c§2§d§2§c생 틸라피아", 76, FishRank.C),
    NULL();


    private String translate;
    private int modelData;

    private int size;

    private Material material = Material.COD;

    private FishRank fishRank;




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
            meta.setDisplayName("§8[" + fishRank.getName() + "§8] " + translate + " §7(" + size + "cm)");
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
