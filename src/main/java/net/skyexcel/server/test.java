package net.skyexcel.server;

import net.skyexcel.server.fish.data.FishRank;

import java.util.Locale;

public class test {
//    public static void main(String[] args) throws Exception {
//        String asdf = "bigbillybass : 매우큰입 우럭 : 1 : S\n" +
//                "colossalarapaima : 피라루쿠 : 2 : S\n" +
//                "flareflounder : 넙치 : 38 : S\n" +
//                "gleamingrainbowtrout : 무지개 송어 : 39 : S\n" +
//                "goliathgrouper : 골리앗참바리 : 40 : S\n" +
//                "slimycatfish : 메기 : 41 : S\n" +
//                "toothfish : 비막치어 : 42 : S";
//
//
//        rank(asdf);
//
//        String qwer = "cookedaligatorgar : 구운 앨리게이터가아 : 3 \n" +
//                "cookedanchovy : 구운 앤초비 : 4 \n" +
//                "cookedbrowntrout : 구운 브라운 송어 : 5\n" +
//                "cookedbullheadcatfish : 구운 눈동자개 : 6 \n" +
//                "cookedcherryshrimp : 구운 체리새우 : 7\n" +
//                "cookedcrappie : 구운 크래피 : 8\n" +
//                "cookedcrayfish : 구운 가재 : 9\n" +
//                "cookeddesertsunfish : 구운 사막 개복치 : 10\n" +
//                "cookedelectriceel : 구운 전기뱀장어 : 11\n" +
//                "cookedfairyshrimp : 구운 풍년새우 : 12\n" +
//                "cookedforestcrayfish : 구운 산 가재 : 13\n" +
//                "cookedforestsunfish : 구운 산 개복치 : 14\n" +
//                "cookedjunglecatfish : 구운 정글 메기 : 15\n" +
//                "cookedlargemouthbass : 작은입 우럭 : 16\n" +
//                "cookedlionfish : 구운 쏠배감펭속 : 17\n" +
//                "cookedmandarin : 구운 만다린 : 18\n" +
//                "cookedmudcarp : 구운 머드 잉어 : 19\n" +
//                "cookedoctopus : 구운 문어 : 20\n" +
//                "cookedperch : 구운 농어 : 21\n" +
//                "cookedpike : 구운 강꼬치고기 : 22\n" +
//                "cookedpiranha : 구운 피라냐 : 23\n" +
//                "cookedpupfish : 구운 펍피쉬 : 24\n" +
//                "cookedrivercrayfish : 구운 민물 가재 : 25\n" +
//                "cookedriversunfish : 구운 민물 개복치 : 26\n" +
//                "cookedsandfish : 구운 도루묵 : 27\n" +
//                "cookedscorpioncarp : 구운 전갈 잉어 : 28\n" +
//                "cookedsmallmouthbass : 구운 큰입 우럭 : 29\n" +
//                "cookedsole : 구운 서대기 : 30\n" +
//                "cookedsturgeon : 구운 철갑상어 : 31\n" +
//                "cookedswampcatfish: 구운 늪 메기 : 32\n" +
//                "cookedswampsunfish : 구운 늪 개복치 : 33\n" +
//                "cookedtambaqui : 구운 땀바끼 : 34\n" +
//                "cookedtigertrout : 구운 타이거 송어 : 35\n" +
//                "cookedtilapia : 구운 틸라피아 : 36\n" +
//                "cookedtuna : 구운 참치 : 37\n";
//
//        rank(qwer);
//        String zxcv =
//                "rawaligatorgar : 생 악어 가르 : 43 : B\n" +
//                        "rawanchovy : 생 앤초비 : 44 : B\n" +
//                        "rawbrowntrout : 생 브라운 송어 : 45 : A \n" +
//                        "rawbullheadcatfish : 생 눈동자개 : 46 : B\n" +
//                        "rawcherryshrimp : 생 체리새우 : 47 : C\n" +
//                        "rawcrappie : 생 크래피 : 48 : B\n" +
//                        "rawcrayfish : 생 가재 : 49 : C\n" +
//                        "rawdesertsunfish : 생 사막 개복치 : 50 : B\n" +
//                        "rawelectriceel : 생 전기뱀장어 : 51 : B\n" +
//                        "rawfairyshrimp : 생 풍년 새우 : 52 : C\n" +
//                        "rawforestcrayfish : 생 산 가재 : 53 : B\n" +
//                        "rawforestsunfish : 생 산 개복치 : 54 : B\n" +
//                        "rawjunglecatfish : 생 정글 메기 : 55 : B\n" +
//                        "rawlargemouthbass : 생 큰입 우럭 : 56 : A\n" +
//                        "rawlionfish : 생 쏠배감펭속 : 57 : A\n" +
//                        "rawmandarin : 생 만다린 : 58 : A\n" +
//                        "rawmudcarp : 생 머드 잉어 : 59 : C\n" +
//                        "rawoctopus : 생 문어 : 60 : C\n" +
//                        "rawperch : 생 농어 : 61 : C\n" +
//                        "rawpike : 생 강꼬치고기 : 62 : C\n" +
//                        "rawpiranha : 생 피라냐 : 63 : C\n" +
//                        "rawpupfish : 생 펍피쉬 : 64 : B\n" +
//                        "rawrivercrayfish : 생 민물 가재 : 65 : C\n" +
//                        "rawriversunfish : 생 민물 개복치 : 66 : B\n" +
//                        "rawsandfish : 생 도루묵 : 67 : C\n" +
//                        "rawscorpioncarp : 생 전갈 잉어 : 68 : C\n" +
//                        "rawsmallmouthbass : 생 작은입 우럭 : 69 : C\n" +
//                        "rawsole : 생 서대기 : 70 : C\n" +
//                        "rawsturgeon : 생 철갑상어 : 71 : B\n" +
//                        "rawswampcatfish : 생 늪 메기 : 72 : C\n" +
//                        "rawswampsunfish : 생 늪 개복치 : 73 : B\n" +
//                        "rawtambaqui : 생 땀바끼 : 74 : B\n" +
//                        "rawtigertrout : 생 타이거 송어 : 75 : C\n" +
//                        "rawtilapia : 생 틸라피아 : 76 : C";
//
//        rank(zxcv);
//        try {
//
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("값이 없습니다.");
//        }
//
//    }

//    public static void main(String[] args) {
//        String title = "asdf's zxcv (1)";
//
//        System.out.println(title.substring(title.length() - 11, title.length() - 4));
//        System.out.println(title.substring(0, title.length() - 10).startsWith("님의 가상창고"));
//    }

    public static void rank(String asdf) {

        String[] result = asdf.split("\n");


        for (String s : result) {

            String namespace = s.split(":")[0].toUpperCase(Locale.ROOT);

            String translate = s.split(":")[1];
            translate = translate.substring(0, translate.length() - 1);
            translate = translate.substring(1);
            int modelData = Integer.parseInt(s.split(":")[2].replace(" ", ""));


            try {
                FishRank fishType = FishRank.valueOf(s.split(":")[3].substring(1));

                System.out.println(namespace + "(\"" + translate + "\"," + modelData + ", FishRank." + fishType + "),");
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println(namespace + "(\"" + translate + "\"," + modelData + "),");
            }


        }
    }
}
