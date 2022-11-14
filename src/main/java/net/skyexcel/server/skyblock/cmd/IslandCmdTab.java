package net.skyexcel.server.skyblock.cmd;


import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.*;

public class IslandCmdTab implements TabCompleter {


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<String>();
        if (sender instanceof Player) {
            Player player = (Player) sender;

            SkyBlockPlayerData playerData;
            if (args.length == 1) {

                result = List.of("도움말", "생성", "제거", "초대", "수락", "거절", "탈퇴", "방문", "추방", "규칙",
                        "금고", "디스코드", "방문객", "이름변경", "스폰변경", "업그레이드", "업그레이드", "양도",
                        "권한", "호퍼", "초기화", "홈", "설정", "옵션", "알바", "순위", "섬원");

            } else if (args.length == 2) {
                switch (args[0]) {
                    case "생성":
                        result.add("[이름]");
                        break;
                    case "초대":
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            playerData = new SkyBlockPlayerData(online);
                            if (playerData.hasIsland()) {
                                result.add(online.getDisplayName());
                            }
                        }
                        break;
                    case "추방":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            SkyBlock skyBlock = new SkyBlock(playerData.getIsland());

                            for (String member : skyBlock.getMembers()) {
                                OfflinePlayer members = Bukkit.getOfflinePlayer(UUID.fromString(member));
                                result.add(members.getName());
                            }

                        }
                        break;

                    case "규칙":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result = List.of("추가", "제거");
                        }

                        result.add("보기");
                        break;
                    case "금고":
                        result = List.of("입금", "출금");

                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result = List.of("기록", "잠금", "입금", "출금");
                        }
                        break;

                    case "디스코드":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result = List.of("설정", "삭제");
                        }

                        break;
                    case "이름변경":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result.add("[이름]");
                        }
                        break;
                    case "양도":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                result.add(online.getDisplayName());
                            }
                        }
                        break;

                    case "옵션":
                        playerData = new SkyBlockPlayerData(player);
//                        if (playerData.isOwner()) {
//                            result = List.of("전투", "밴블록", "열기", "잠금", "시간");
//                        }
                        result = List.of("전투", "밴블록", "열기", "잠금", "시간", "월드보더");
                        break;

                    case "알바":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result = List.of("추가", "제거", "완료");
                        }
                        break;
                }
            } else if (args.length == 3) {

                if (equalArgs(args, 0, "추방")) {
                    playerData = new SkyBlockPlayerData(player);
                    if (playerData.isOwner())
                        result.add("사유");
                } else if (equalArgs(args, 0, "금고")) {
                    if (!equalArgs(args, 1, "잠금") || !equalArgs(args, 1, "기록")) {
                        result.add("<amount>");
                    }
                } else if (equalArgs(args, 0, "디스코드")) {
                    if (!equalArgs(args, 1, "삭제")) {
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner())
                            result.add("[링크]");
                    }
                } else if (equalArgs(args, 0, "옵션")) {
                    if (!equalArgs(args, 1, "열기")) {
                        if (equalArgs(args, 1, "밴블록")) {
                            result.add("알바");
                            result.add("섬원");
                        } else if (equalArgs(args, 1, "전투")) {
                            result.add("활성화");
                            result.add("비활성화");
                        } else if (equalArgs(args, 1, "알바")) {
                            if (equalArgs(args, 2, "추가")) {
                                playerData = new SkyBlockPlayerData(player);

                                SkyBlock skyBlock = new SkyBlock(playerData.getIsland());
                                List<String> members = skyBlock.getMembers();

                                for (Player online : Bukkit.getOnlinePlayers()) {
                                    if (members.contains(online.getUniqueId().toString())) {
                                        result.add(online.getDisplayName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean equalArgs(String[] args, int index, String other) {
        return args[index].equalsIgnoreCase(other);
    }
}
