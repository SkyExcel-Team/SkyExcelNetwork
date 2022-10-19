package net.skyexcel.server.island;

import net.md_5.bungee.api.chat.*;
import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.SkyBlockData;
import net.skyexcel.server.data.economy.SEconomy;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.data.player.SkyBlockRequest;
import net.skyexcel.server.data.vault.SkyBlockVaultRecord;
import net.skyexcel.server.data.vault.SkyBlockVault;
import net.skyexcel.server.menu.Menu;
import net.skyexcel.server.ui.gui.MaterialPagePartTime;
import net.skyexcel.server.ui.title.Loading;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import skyexcel.command.function.Cmd;

import java.util.Arrays;


public class IslandCmd {
    public IslandCmd() {
        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "섬");

        SkyBlockRequest request = new SkyBlockRequest();


        cmd.label(action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

            if (data.teleportSkyBlock(player)) {
                player.sendMessage("텔레포트 하였습니다!");
            } else {
                player.sendMessage("텔레포트 실패!");
            }
        });

        cmd.action("메뉴", 0, action -> {
            Player player = (Player) action.getSender();

            Menu menu = new Menu("섬");
            menu.load(player);
        });


        cmd.action("도움말", 0, action -> {
            Player player = (Player) action.getSender();
            player.sendMessage("test");
        });


        cmd.action("양도", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            SkyBlock data = new SkyBlock(playerData.getIsland());

            if (data.setOwner(target)) {
                player.sendMessage("양도 완료");
            }
        });


        cmd.action("탈퇴", 0, action -> {
            Player player = (Player) action.getSender();
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            SkyBlock data = new SkyBlock(playerData.getIsland());

            data.quickSkyBlock(player);

        });


        cmd.action("추방", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            SkyBlock island = new SkyBlock(playerData.getIsland());

            String reason = String.join(" ", Arrays.copyOfRange(action.getArgs(), 2, action.getArgs().length));

            assert target != null;
            if (island.kickMember(player, target, reason)) {

                player.sendMessage("해당 플레이어를 '" + reason + "' 사유로 추방 하였습니다!");

                TextComponent accept = new TextComponent("'" + ChatColor.UNDERLINE + reason + ChatColor.RESET + "'");
                TextComponent after = new TextComponent(ChatColor.RED + " 사유로 추방 당하였습니다! ");

                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(player.getDisplayName() + " 님이 추방하였습니다.").create()));
                TextComponent result = new TextComponent(ChatColor.translateAlternateColorCodes('&', island.getName()) +
                        ChatColor.RED + " 섬에서 " + ChatColor.WHITE);
                result.addExtra(accept);

                result.addExtra(after);

                target.spigot().sendMessage(result);

            } else {
                player.sendMessage("해당 플레이어는 섬원이 아닙니다!");
            }
        });

        cmd.action("초대", 0, action -> {
            Player player = (Player) action.getSender();


            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            assert target != null;
            SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            if (!targetData.hasIsland() || playerData.hasIsland()) {
                if (SkyBlockRequest.send(request, target, player)) {


                    TextComponent accept = new TextComponent("§a수락");

                    accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a클릭하여 수락하세요!").create()));
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 수락 " + player.getDisplayName()));

                    TextComponent deny = new TextComponent("§c거절");

                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c클릭하여 거절하세요").create()));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 거절 " + player.getDisplayName()));

                    TextComponent result = new TextComponent(player.getDisplayName() + " 님에게 섬 초대 요청이 왔습니다! ");
                    result.addExtra(accept);
                    result.addExtra("|");
                    result.addExtra(deny);

                    target.spigot().sendMessage(result);
                    player.sendMessage("초대를 보냈습니다!");

                } else {
                    player.sendMessage("상대방이 수락할때 까지 기달려 주세요.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "해당 플레이어는 이미 섬에 소속 되어 있습니다!");
            }
        });


        cmd.action("수락", 0, action -> {
            Player player = (Player) action.getSender();

            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            if (SkyBlockRequest.accept(request, player, target)) {

                assert target != null;
                SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);
                SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                playerData.setName(targetData.getIsland());

                SkyBlock islandData = new SkyBlock(targetData.getIsland());

                islandData.accept(target, player);

                player.sendMessage(targetData.getIsland() + " 섬에 입장하였습니다!");
            } else {
                player.sendMessage("초대 수락에 실패 하였습니다! ");
            }
        });


        cmd.action("거절", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);


            SkyBlock data = new SkyBlock(playerData.getIsland());
            if (SkyBlockRequest.deny(request, player, target)) {
                target.sendMessage(player.getDisplayName() + " 님이 초대 요청을 거절 하였습니다!");
                player.sendMessage("초대 요청을 거절 하였습니다!");
            } else {
                player.sendMessage("초대 요청 거절을 실패 하였습니다! ");
            }

        });


        cmd.action("홈", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            playerData.setSpawn();
        });

        cmd.action("스폰변경", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            if (playerData.setSpawn()) {
                player.sendMessage("스폰 설정");
            }
        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();
            String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);


            playerData.setName(name);
            SkyBlock data = new SkyBlock(name);

            if (data.delete()) {
                player.sendMessage("제거 완료");
            }
        });

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));

            SkyBlockData.loading.put(player.getUniqueId(), new Loading(player, 4));

            SkyBlockData.loading.get(player.getUniqueId()).runTaskTimer(SkyExcelNetwork.plugin, 0, 10);

            SkyBlock data = new SkyBlock(player, name);

            data.create(player);
        });


        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            SkyBlock data = new SkyBlock(playerData.getIsland());
            data.delete(player);
        });

        cmd.action("이름", 0, action -> {
            Player player = (Player) action.getSender();

            String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));

            SkyBlock data = new SkyBlock(name);
            if (data.rename(name)) {
                player.sendMessage("이름을 바꾸었습니다!");
            }
        });


        cmd.action("금고", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            SkyBlock data = new SkyBlock(playerData.getIsland());
            SkyBlockVault vault;
            int amount;
            SEconomy money;
            switch (args[1]) {
                case "입금":
                    data = new SkyBlock(player, playerData.getIsland());
                    vault = data.getVault();
                    amount = Integer.parseInt(args[2]);
                    money = new SEconomy(player);

                    if (vault.deposit(amount) && money.withdraw(amount)) {
                        SkyBlockVaultRecord record = new SkyBlockVaultRecord(playerData.getIsland());

                        record.record(player, amount, SkyBlockVaultRecord.Type.DEPOSIT);

                        player.sendMessage("입금 완료");
                    } else {
                        player.sendMessage("입금 실패!");
                    }
                    break;

                case "출금":
                    data = new SkyBlock(player, playerData.getIsland());

                    vault = data.getVault();
                    amount = Integer.parseInt(args[2]);
                    vault.setPlayer(player);
                    money = new SEconomy(player);
                    if (vault.withdraw(amount)) {
                        money.deposit(amount);
                        SkyBlockVaultRecord record = new SkyBlockVaultRecord(playerData.getIsland());

                        record.record(player, amount, SkyBlockVaultRecord.Type.WITHDRAW);

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


            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());


            if (args.length > 1) {
                switch (args[1]) {
                    case "설정" -> {
                        String link = args[2];
                        data.setDiscord(link);
                    }
                    case "삭제" -> data.removeDiscord();
                }
            } else {
                TextComponent url = new TextComponent("§a클릭");

                url.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a섬 디스코드 이동!").create()));
                url.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, data.getDiscord()));

                TextComponent message = new TextComponent("섬 디스코드 링크 => ");
                message.addExtra(url);
                player.spigot().sendMessage(message);
            }

        });

        cmd.action("기록", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

            switch (args[1]) {
                case "초기화":

                    player.sendMessage("기록을 초기화 했습니다!");
                    if (player.isOp())
                        data.removeAll();
                    break;

            }
        });


        cmd.action("알바", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

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
                case "제거":
                    data.removeDiscord();
                    break;
                case "밴블록":

                    break;

            }
        });

        cmd.action("규칙", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

            switch (args[1]) {
                case "추가":
                    if (data.addRule(args)) {
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
                        player.sendMessage("규칙 삭제 실패!");
                    }
                    break;

                case "보기":
                    data.getRule(player);
                    break;
            }
        });


        cmd.action("옵션", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

            switch (args[1]) {
                case "밴블록":
                    if (args.length > 2) {
                        switch (args[2]) {
                            case "알바":
                                MaterialPagePartTime material = new MaterialPagePartTime();

                                material.Show(player, "알바 밴블록");
                                SkyBlockData.partTimePage.put(player.getUniqueId(),material);
                                break;
                            case "맴버":

                                break;
                        }
                    }
                    break;
                case "pvp":
                    int index = Integer.parseInt(args[2]);
                    if (data.removeRule(index)) {
                        player.sendMessage("성공적으로 제거가 되었습니다!");
                    } else {
                        player.sendMessage("규칙 삭제 실패!");
                    }
                    break;

                case "열기":

                    break;
            }
        });

    }
}
