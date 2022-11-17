package net.skyexcel.server.fish.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum FishType {

    BIGBILLYBASS("매우큰입 우럭", 1),
    COLOSSALARAPAIMA("피라루쿠", 2),
    FLAREFLOUNDER("넙치", 38),
    GLEAMINGRAINBOWTROUT("무지개 송어", 39),
    GOLIATHGROUPER("골리앗참바리", 40),
    SLIMYCATFISH("메기", 41),
    TOOTHFISH("비막치어", 42),
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
    RAWALIGATORGAR("생 악어 가르", 43),
    RAWANCHOVY("생 앤초비", 44),
    RAWBROWNTROUT("생 브라운 송어", 45),
    RAWBULLHEADCATFISH("생 눈동자개", 46),
    RAWCHERRYSHRIMP("생 체리새우", 47),
    RAWCRAPPIE("생 크래피", 48),
    RAWCRAYFISH("생 가재", 49),
    RAWDESERTSUNFISH("생 사막 개복치", 50),
    RAWELECTRICEEL("생 전기뱀장어", 51),
    RAWFAIRYSHRIMP("생 풍년 새우", 52),
    RAWFORESTCRAYFISH("생 산 가재", 53),
    RAWFORESTSUNFISH("생 산 개복치", 54),
    RAWJUNGLECATFISH("생 정글 메기", 55),
    RAWLARGEMOUTHBASS("생 큰입 우럭", 56),
    RAWLIONFISH("생 쏠배감펭속", 57),
    RAWMANDARIN("생 만다린", 58),
    RAWMUDCARP("생 머드 잉어", 59),
    RAWOCTOPUS("생 문어", 60),
    RAWPERCH("생 농어", 61),
    RAWPIKE("생 강꼬치고기", 62),
    RAWPIRANHA("생 피라냐", 63),
    RAWPUPFISH("생 펍피쉬", 64),
    RAWRIVERCRAYFISH("생 민물 가재", 65),
    RAWRIVERSUNFISH("생 민물 개복치", 66),
    RAWSANDFISH("생 도루묵", 67),
    RAWSCORPIONCARP("생 전갈 잉어", 68),
    RAWSMALLMOUTHBASS("생 작은입 우럭", 69),
    RAWSOLE("생 서대기", 70),
    RAWSTURGEON("생 철갑상어", 71),
    RAWSWAMPCATFISH("생 늪 메기", 72),
    RAWSWAMPSUNFISH("생 늪 개복치", 73),
    RAWTAMBAQUI("생 땀바끼", 74),
    RAWTIGERTROUT("생 타이거 송어", 75),
    RAWTILAPIA("생 틸라피아", 76),
    RAWTUNA("생 참치", 77);


    private String translate;
    private int modelData;

    private Material material = Material.COD;

    FishType(String translate, int modelData) {

        this.translate = translate;
        this.modelData = modelData;
    }


    public ItemStack item(int amount) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(translate);
        meta.setCustomModelData(modelData);
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
