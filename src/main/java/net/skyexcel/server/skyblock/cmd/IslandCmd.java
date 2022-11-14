package net.skyexcel.server.skyblock.cmd;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.server.menu.menu.Menu;
import net.skyexcel.server.seconomy.data.economy.SEconomy;
import net.skyexcel.server.skyblock.data.SkyBlockData;
import net.skyexcel.server.skyblock.data.StringData;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import net.skyexcel.server.skyblock.data.player.SkyBlockRequest;
import net.skyexcel.server.skyblock.ui.title.Loading;
import net.skyexcel.server.skyblock.util.Translate;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;

import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVault;
import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVaultRecord;

import net.skyexcel.server.skyblock.ui.gui.MaterialPageMember;
import net.skyexcel.server.skyblock.ui.gui.MaterialPagePartTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;


import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.*;


public class IslandCmd {


    public IslandCmd() {
        Cmd cmd = new Cmd(SkyExcelNetworkSkyBlockMain.plugin, "섬");

        SkyBlockRequest request = new SkyBlockRequest();
        StringData stringData = new StringData();


        cmd.label(action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

            if (data.teleportSkyBlock(player)) {
                player.sendMessage("架 " + "자신의 섬으로 이동하였습니다 " + ChatColor.GRAY + "[/섬 도움말]");
            } else {
                player.sendMessage("强 소속되어있는 섬이 없어 텔레포트가 불가능합니다! ");
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
                player.sendMessage("强 §6" + target.getDisplayName() + "§f님에게 섬을 양도하였습니다!");
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

            OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);

            SkyBlock island = new SkyBlock(playerData.getIsland());

            String reason = Translate.collapse(action.getArgs(), 2);

            assert target != null;
            if (island.kickMember(player, target, reason)) {

                player.sendMessage("架 해당 플레이어를 §e'" + reason + "'§f의 사유로 §a추방 §f하였습니다!");

                TextComponent accept = new TextComponent("'" + ChatColor.UNDERLINE + reason + ChatColor.RESET + "'");
                TextComponent after = new TextComponent("家 §e'" + reason + "'§f의 사유로 §c추방 §f당하였습니다! ");

                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("家 §6" + player.getDisplayName() + "§f님이 §c추방§당하셨습니다.").create()));
                TextComponent result = new TextComponent(ChatColor.translateAlternateColorCodes('&', island.getName()) +
                        ChatColor.RED + " 섬에서 " + ChatColor.WHITE);
                result.addExtra(accept);

                result.addExtra(after);
                if (target.isOnline()) {

                }
                target.getPlayer().spigot().sendMessage(result);

            } else {
                player.sendMessage("强 §f해당 플레이어는 섬원이 아닙니다!");
            }
        });

        cmd.action("초대", 0, action -> {
            Player player = (Player) action.getSender();


            Player target = Bukkit.getPlayer(action.getArgs()[1]);

            assert target != null;
            SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);


            if (!targetData.hasIsland()) {
                if (SkyBlockRequest.send(request, target, player)) {
                    TextComponent accept = new TextComponent("§a수락");

                    accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("家 §a클릭하여 수락하세요!").create()));
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 수락 " + player.getDisplayName()));

                    TextComponent deny = new TextComponent("§c거절");

                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("家 §c클릭하여 거절하세요").create()));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/섬 거절 " + player.getDisplayName()));

                    TextComponent result = new TextComponent("家 §6" + player.getDisplayName() + "§f님에게 섬 초대 요청이 왔습니다! ");
                    result.addExtra(accept);
                    result.addExtra("|");
                    result.addExtra(deny);

                    target.spigot().sendMessage(result);
                    player.sendMessage("家 §6" + target.getDisplayName() + "§f님에게 섬 §a초대§f를 보냈습니다!");

                } else {
                    player.sendMessage("强 이미 초대 요청을 보낸 플레이어입니다!");
                }
            } else {
                player.sendMessage("强 §6" + target.getDisplayName() + "§f님은 이미 섬에 소속 되어 있습니다!");
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

                player.sendMessage("架 §6" + targetData.getIsland() + " §f섬에 입장하였습니다!");
            } else {
                player.sendMessage("强 초대 수락에 실패 하였습니다!");
            }
        });


        cmd.action("거절", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            Player target = Bukkit.getPlayer(action.getArgs()[1]);


            SkyBlock data = new SkyBlock(playerData.getIsland());
            if (SkyBlockRequest.deny(request, player, target)) {
                target.sendMessage("家 §6" + player.getDisplayName() + "§f님이 초대 요청을 §c거절 §f하였습니다!");
                player.sendMessage("架 초대 요청을 거절 하였습니다!");
            } else {
                player.sendMessage("强 초대 요청 §c거절§f을 §c실패 §f하였습니다! ");
            }

        });


        cmd.action("블랙리스트", 0, action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock skyBlock = new SkyBlock(playerData.getIsland());

            String[] args = action.getArgs();
            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("추가")) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                    String reason = String.join(" ", Arrays.copyOfRange(args, 2, action.getArgs().length));

                    if (skyBlock.addBlackList(player, target, reason)) {
                        player.sendMessage("架 §6" + target.getName() + "§f님을 §e'" + reason + "'§f의 사유로 블랙리스트에 §a추가 §f하였습니다.");
                    }
                } else if (args[0].equalsIgnoreCase("해제")) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                    if (skyBlock.removeBlackList(target)) {

                    }
                }
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
                player.sendMessage("架 현재 있는 위치를 섬 스폰으로 설정되었습니다!");
            }
        });

        cmd.action("제거", 0, action -> {
            Player player = (Player) action.getSender();
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            SkyBlock data = new SkyBlock(playerData.getIsland());
            data.remove(player);
        });

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();
            if (args.length <= 30) {
                String name = Translate.collapse(action.getArgs(), 1);
                SkyBlock data = new SkyBlock(player, name);
                if (!data.equalFileName(name)) {

                    try {
                        data.create(player);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    Loading loading = new Loading(player, 5);

                    loading.runTaskTimer(SkyExcelNetworkSkyBlockMain.plugin, 0, 10);

                    loading.end(end -> {
                        if (data.getLocation() != null)
                            data.spawn(player, data.getLocation());
                    });


                    SkyBlockData.loading.put(player.getUniqueId(), loading);
                } else {
                    player.sendMessage("强 이미 사용중인 섬 이름입니다!");
                }
            } else {
                player.sendMessage("强 섬 이름은 30글자 미만 이어야 합니다.");
            }

        });

        cmd.action("이름", 0, action -> {
            Player player = (Player) action.getSender();

            String name = Translate.collapse(action.getArgs(), 1);

            SkyBlock data = new SkyBlock(name);
            if (data.rename(name)) {
                player.sendMessage("架 섬 이름을 §6" + name + "§f으로 바꾸었습니다!");
            }
        });

        cmd.action("방문", 0, action -> {
            Player player = (Player) action.getSender();

            if (action.getArgs().length > 1) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);

                assert target != null;
                SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);

                SkyBlock skyBlock = new SkyBlock(targetData.getIsland());
                skyBlock.teleportSkyBlock(player);


                player.sendMessage("架 §6" + target.getName() + "§f님의 섬을 방문 했습니다!");
            }
        });

        cmd.action("금고", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

            if (playerData.hasIsland()) {
                SkyBlock data = new SkyBlock(playerData.getIsland());
                SkyBlockVault vault;
                int amount;
                SEconomy money;
                switch (args[1]) {
                    case "입금" -> {
                        data = new SkyBlock(player, playerData.getIsland());
                        vault = data.getVault();
                        amount = Integer.parseInt(args[2]);
                        money = new SEconomy(player);


                        if (vault.deposit(amount) && money.withdraw(amount)) {
                            SkyBlockVaultRecord record = new SkyBlockVaultRecord(playerData.getIsland());

                            record.record(player, amount, SkyBlockVaultRecord.Type.DEPOSIT);

                            player.sendMessage("架 섬에 §6" + format(money.getLong()) + "§f을 입금 하였습니다!");

                            List<String> members = data.getMembers();
                            for (String member : members) {
                                Player online = Bukkit.getPlayer(member);
                                online.getPlayer().sendMessage("家 " + player.getPlayer().getDisplayName() + "님이 섬 금고에 §6" + format(money.getLong()) + "을 입금 하였습니다!");
                            }

                        } else {
                            player.sendMessage("强 금고에 §6입금§f을 §c실패§f하였습니다!");
                        }

                    }
                    case "출금" -> {
                        data = new SkyBlock(player, playerData.getIsland());
                        vault = data.getVault();
                        amount = Integer.parseInt(args[2]);
                        vault.setPlayer(player);
                        money = new SEconomy(player);
                        if (vault.withdraw(amount)) {
                            money.deposit(amount);
                            SkyBlockVaultRecord record = new SkyBlockVaultRecord(playerData.getIsland());

                            record.record(player, amount, SkyBlockVaultRecord.Type.WITHDRAW);

                            player.sendMessage("架 섬 금고에서 §6" + format(money.getLong()) + "§f을 출금 하였습니다!");

                            List<String> members = data.getMembers();
                            for (String member : members) {
                                Player online = Bukkit.getPlayer(member);
                                online.getPlayer().sendMessage("家 " + player.getPlayer().getDisplayName() + "님이 섬 금고에서 §6" + format(money.getLong()) + "을 출금 하였습니다!");
                            }
                        } else {
                            player.sendMessage("强 금고에서 §6출금§f을 §c실패§f하였습니다!");
                        }
                    }
                    case "잠금" -> data.setVaultLock();
                }
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

                url.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("强 §a클릭해주세요!").create()));
                url.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, data.getDiscord()));

                TextComponent message = new TextComponent("家 섬 디스코드 링크 => ");
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

                    player.sendMessage("架 기록을 초기화 했습니다!");
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
                    if (args.length > 2) {
                        String name = args[2];
                        if (args.length > 3) {
                            try {
                                int amount = Integer.parseInt(args[3]);

                                Player target = Bukkit.getPlayer(name);

                                if (data.addPartTime(target, amount)) {
                                    player.sendMessage("架 §6" + target.getDisplayName() + "§f님을 알바로 추가하였습니다");
                                    target.sendMessage("家 §6" + player.getDisplayName() + "§f님이 당신을 알바로 고용했습니다!");
                                } else {
                                    player.sendMessage("强 알바 추가가 불가능합니다.");
                                }
                            } catch (NumberFormatException e) {
                                player.sendMessage("强올 바른 숫자를 입력해 주세요!");
                            }

                        } else {
                            player.sendMessage("强 금액을 입력해 주세요!");
                        }
                    } else {
                        player.sendMessage("强 이름을 입력해 주세요!");
                    }
                    break;
                case "제거":
                    data.removePartTime(player);
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
                        player.sendMessage("架 성공적으로 규칙이 추가가 되었습니다!");
                    } else {
                        player.sendMessage("强 규칙 추가 실패!");
                    }
                    break;
                case "삭제":
                    int index = Integer.parseInt(args[2]);
                    if (data.removeRule(index)) {
                        player.sendMessage("架 성공적으로 규칙이 제거가 되었습니다!");
                    } else {
                        player.sendMessage("强 규칙 삭제를 실패하였습니다!");
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
                            case "알바" -> {
                                MaterialPagePartTime partTime = new MaterialPagePartTime("알바 밴블록");
                                partTime.update(player);
                                SkyBlockData.partTimePage.put(player.getUniqueId(), partTime);
                                player.openInventory(partTime.getInv());
                            }
                            case "섬원" -> {
                                MaterialPageMember member = new MaterialPageMember("섬원 밴블록");
                                member.update(player);
                                player.openInventory(member.getInv());
                                SkyBlockData.memberPage.put(player.getUniqueId(), member);
                            }
                        }
                    }
                    break;
                case "pvp":


                    break;
                case "시간":
                    if (args.length > 2) {
                        switch (args[2]) {
                            case "아침" -> {
                                data.time(player, 1000);
                                player.sendMessage("§a● §f§e아침§f으로 변경하였습니다.");
                            }
                            case "점심" -> {
                                data.time(player, 6000);
                                player.sendMessage("§a● §f§e점심§f으로 변경하였습니다.");
                            }
                            case "저녁" -> {
                                data.time(player, 1);
                                player.sendMessage("§a● §7저녁§f으로 변경하였습니다.");
                            }
                            case "일몰" -> {
                                data.time(player, 12000);
                                player.sendMessage("§a● §f§6일몰§f 시간대로 변경하였습니다.");
                            }
                            case "일출" -> {
                                data.time(player, 23000);
                                player.sendMessage("§a● §f일출§f 시간대로변경하였습니다.");
                            }
                            case "밤" -> {
                                data.time(player, 18000);
                                player.sendMessage("§a● §0밤§f 시간대로변경하였습니다.");
                            }
                        }
                    }
                    break;
                case "월드보더":
                    data.setWorldBorderVisibilty(player);
                    break;

                case "열기":
                    data.setOpen(player);
                    break;
            }
        });
    }

    private String format(long amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        return decFormat.format(amount);
    }
}
