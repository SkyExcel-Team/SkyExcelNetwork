package net.skyexcel.server.cmd;


import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
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
        ArrayList<String> result = new ArrayList<String>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(args.length + "");
            SkyBlockPlayerData playerData;
            if (args.length == 1) {
                result.add("도움말");
                result.add("생성");
                result.add("제거");
                result.add("초대");
                result.add("수락");
                result.add("거절");
                result.add("탈퇴");
                result.add("방문");
                result.add("추방");
                result.add("규칙");
                result.add("금고");
                result.add("디스코드");
                result.add("방문객");
                result.add("이름변경");
                result.add("스폰변경");

                result.add("업그레이드");
                result.add("양도");
                result.add("권한");
                result.add("호퍼");
                result.add("초기화");
                result.add("홈");
                result.add("설정");
                result.add("옵션");
                result.add("알바");
                result.add("순위");
                result.add("섬원");

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
                            result.add("추가");
                            result.add("제거");
                        }

                        result.add("보기");
                        break;
                    case "금고":

                        result.add("입금");
                        result.add("출금");
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result.add("기록");
                            result.add("잠금");
                        }
                        break;

                    case "디스코드":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result.add("설정");
                            result.add("삭제");
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
                        if (playerData.isOwner()) {
                            result.add("전투");
                            result.add("밴블록");
                            result.add("전투");
                            result.add("열기");
                            result.add("잠금");
                            result.add("시간");
                        }

                        result.add("전투");
                        result.add("밴블록");
                        result.add("전투");
                        result.add("열기");
                        result.add("잠금");
                        result.add("시간");

                        break;

                    case "알바":
                        playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            result.add("추가");
                            result.add("제거");
                            result.add("완료");
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
