package net.skyexcel.server.island;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.IslandData;
import net.skyexcel.server.data.PlayerData;
import net.skyexcel.server.data.Vault;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;
import skyexcel.data.file.Config;

public class IslandCmd {
    public IslandCmd() {
        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "섬");


        cmd.label(action -> {
            Player player = (Player) action.getSender();
            player.sendMessage("test");
        });

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            String name = Translate.msgCollapse(action.getArgs(), 1);
            PlayerData playerData = new PlayerData(player);

            playerData.setName(name);
            IslandData data = new IslandData(name);

            data.create(player);

            IslandCmdTab.addArg("제거", name);

        });
        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();
            String name = Translate.msgCollapse(action.getArgs(), 1);

            IslandData data = new IslandData(name);
            data.delete(player);
        });

        cmd.action("이름", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);

            IslandData data = new IslandData(name);
            if (data.rename(name)) {
                player.sendMessage("이름을 바꾸었습니다!");
            }
        });

        cmd.action("금고", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            PlayerData playerData = new PlayerData(player);

            IslandData data = new IslandData(playerData.getIsland());


            switch (args[1]) {
                case "입금":
                    Vault vault = data.getVault();
                    int amount = Integer.parseInt(args[2]);
                    if (vault.deposit(amount)) {
                        player.sendMessage("입금 완료");
                    } else {
                        player.sendMessage("입금 실패!");
                    }
                    break;
            }
        });

        cmd.action("디스코드", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            PlayerData playerData = new PlayerData(player);
            IslandData data = new IslandData(playerData.getIsland());

            switch (args[1]) {
                case "설정":
                    String link = args[2];
                    data.setDiscord(link);
                    break;
                case "삭제":
                    data.removeDiscord();
                    break;
            }
        });


        cmd.action("알바", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            PlayerData playerData = new PlayerData(player);
            IslandData data = new IslandData(playerData.getIsland());

            switch (args[1]) {
                case "추가":
                    String name = args[2];
                    if (args.length > 2) {
                        if (args.length > 3) {
                            try {
                                int amount = Integer.parseInt(args[3]);

                                Player target = Bukkit.getPlayer(name);

                                if (data.addPartTime(target, amount)) {
                                    player.sendMessage("알바 추가가 되었습니다!");
                                } else {
                                    player.sendMessage("알바 추가가 안됨 ㅅㄱ");
                                }
                            } catch (NumberFormatException e) {
                                player.sendMessage("올바른 숫자를 입력해 주세요!");
                            }

                        } else {
                            player.sendMessage("금액을 입력해 주세요!");
                        }
                    } else {
                        player.sendMessage("이름을 입력해 주세요!");
                    }
                    break;
                case "삭제":
                    data.removeDiscord();
                    break;
            }
        });


        cmd.action("규칙", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            PlayerData playerData = new PlayerData(player);
            IslandData data = new IslandData(playerData.getIsland());

            switch (args[1]) {
                case "추가":
                    String rule = args[2];
                    if (data.addRule(rule)) {
                        player.sendMessage("성공적으로 추가가 되었습니다!");
                    } else {
                        player.sendMessage("규칙 추가 실패!");
                    }
                    break;
                case "삭제":
                    int index = Integer.parseInt(args[2]);
                    if (data.removeRule(index)) {
                        player.sendMessage("성공적으로 제거가 되었습니다!");
                    } else {
                        player.sendMessage("규칙 추가 실패!");
                    }
                    break;

                case "보기":
                    for (String line : data.getRule()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
                    }
                    break;
            }
        });
    }
}
