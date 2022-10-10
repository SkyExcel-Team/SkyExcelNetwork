package net.skyexcel.server.island;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.island.IslandData;
import net.skyexcel.server.data.player.PlayerData;
import net.skyexcel.server.data.vault.VaultRecord;
import net.skyexcel.server.data.vault.Vault;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class IslandCmd {
    public IslandCmd() {
        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "섬");


        cmd.label(action -> {
            Player player = (Player) action.getSender();

            PlayerData playerData = new PlayerData(player);
            IslandData data = new IslandData(playerData.getIsland());

            if (data.teleportIsland(player)) {
                player.sendMessage("텔레포트 하였습니다!");
            } else {
                player.sendMessage("텔레포트 실패!");
            }
        });

        cmd.action("도움말", 0, action -> {
            Player player = (Player) action.getSender();
            player.sendMessage("test");
        });


        cmd.action("양도", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);

            PlayerData playerData = new PlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            IslandData data = new IslandData(playerData.getIsland());

            if (data.setOwner(target)) {
                player.sendMessage("양도 완료");
            }
        });


        cmd.action("탈퇴", 0, action -> {
            Player player = (Player) action.getSender();
            PlayerData playerData = new PlayerData(player);

            IslandData data = new IslandData(playerData.getIsland());

            data.quickIsland(player);

        });


        cmd.action("추방", 0, action -> {
            Player player = (Player) action.getSender();

            PlayerData playerData = new PlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            IslandData island = new IslandData(playerData.getIsland());

            String reason = Translate.msgCollapse(action.getArgs(), 2);

            if (island.kickMember(player, target, reason)) {
                player.sendMessage("해당 플레이어를 '" + reason + "' 사유로 추방 하였습니다!");
            } else {
                player.sendMessage("해당 플레이어는 섬원이 아닙니다!");
            }

        });


        cmd.action("초대", 0, action -> {
            Player player = (Player) action.getSender();
            String name = Translate.msgCollapse(action.getArgs(), 1);
            PlayerData playerData = new PlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);
            playerData.setName(name);
            IslandData data = new IslandData(name);


            assert target != null;
            if (data.invite(player, target)) {
                player.sendMessage("초대를 보냈습니다!");
            } else {
                player.sendMessage("초대에 문제가 생겼습니다!");
            }
        });

        cmd.action("수락", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.msgCollapse(action.getArgs(), 1);
            PlayerData playerData = new PlayerData(player);


            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            IslandData data = new IslandData(playerData.getIsland());


            assert target != null;
            if (data.accept(Bukkit.getPlayer(data.getOwner()), target)) {

                player.sendMessage("수락을 했습니다!");
            } else {
                player.sendMessage("수락에 문제가 생겼습니다!");
            }
        });

        cmd.action("거절", 0, action -> {
            Player player = (Player) action.getSender();
            String name = Translate.msgCollapse(action.getArgs(), 1);
            PlayerData playerData = new PlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);
            playerData.setName(name);
            IslandData data = new IslandData(name);

            assert target != null;
            if (data.deny(target)) {
                player.sendMessage("초대를 거절 하였습니다!");
            }
        });


        cmd.action("홈", 0, action -> {
            Player player = (Player) action.getSender();

            PlayerData playerData = new PlayerData(player);

            playerData.setSpawn();
        });

        cmd.action("스폰변경", 0, action -> {
            Player player = (Player) action.getSender();

            PlayerData playerData = new PlayerData(player);

            if (playerData.setSpawn()) {
                player.sendMessage("스폰 설정");
            }
        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();
            String name = Translate.msgCollapse(action.getArgs(), 1);
            PlayerData playerData = new PlayerData(player);


            playerData.setName(name);
            IslandData data = new IslandData(name);

            if (data.delete()) {
                player.sendMessage("제거 완료");
            }
        });

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            String name = Translate.msgCollapse(action.getArgs(), 1);
            PlayerData playerData = new PlayerData(player);

            playerData.setName(name);
            IslandData data = new IslandData(name);

            data.create(player);



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
            Vault vault;
            int amount;

            switch (args[1]) {
                case "입금":
                    data = new IslandData(player, playerData.getIsland());
                    vault = data.getVault();
                    amount = Integer.parseInt(args[2]);

                    if (vault.deposit(amount)) {
                        VaultRecord record = new VaultRecord(playerData.getIsland());

                        record.record(player, amount, VaultRecord.Type.DEPOSIT);

                        player.sendMessage("입금 완료");
                    } else {
                        player.sendMessage("입금 실패!");
                    }
                    break;

                case "출금":
                    data = new IslandData(player, playerData.getIsland());

                    vault = data.getVault();
                    amount = Integer.parseInt(args[2]);
                    vault.setPlayer(player);

                    if (vault.withdraw(amount)) {
                        VaultRecord record = new VaultRecord(playerData.getIsland());

                        record.record(player, amount, VaultRecord.Type.WITHDRAW);

                        player.sendMessage("출금 완료");
                    } else {
                        player.sendMessage("출금 실패!");
                    }
                    break;
                case "잠금":

                    if (data.setVaultLock()) {
                        player.sendMessage("금고를 잠궜습니다.");
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
