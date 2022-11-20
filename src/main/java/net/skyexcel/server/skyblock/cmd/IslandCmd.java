package net.skyexcel.server.skyblock.cmd;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.api.util.Translate;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.menu.menu.Menu;

import net.skyexcel.server.seconomy.data.SEConomy;
import net.skyexcel.server.skyblock.data.SkyBlockData;
import net.skyexcel.server.skyblock.data.island.DeleteRunnable;
import net.skyexcel.server.skyblock.data.island.InviteSkyBlock;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;

import net.skyexcel.server.skyblock.data.player.SkyBlockRequest;
import net.skyexcel.server.skyblock.ui.gui.PageVisitor;
import net.skyexcel.server.skyblock.ui.gui.UpgradeGUI;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;

import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVault;
import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVaultRecord;

import net.skyexcel.server.skyblock.ui.gui.MaterialPageMember;
import net.skyexcel.server.skyblock.ui.gui.MaterialPagePartTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.text.DecimalFormat;
import java.util.*;


public class IslandCmd implements CommandExecutor {

    private final List<String> help = List.of(
            "§6§l│ §f/섬 도움말 <1~4> : 섬 명령어를 §6모두 §f띄웁니다 ",
            "§6§l│ §f/섬 생성 [이름] : [이름]의 섬을 §a생성§f합니다.",
            "§6§l│ §f/섬 제거 : 자신의 섬을 §c제거§f합니다.",
            "§6§l│ §f/섬 초대 [플레이어] : 자신의 섬에 [플레이어를] §c추가§f합니다",
            "§6§l│ §f/섬 수락 [플레이어] : 초대온 섬 초대 요청을 §a수락§f합니다.",
            "§6§l│ §f/섬 거절 [플레이어] : 초대온 섬 초대 요청을 §c거절§f합니다.",
            "§6§l│ §f/섬 탈퇴 : 섬에서 §c탈퇴§f합니다.",
            "§6§l│ §f/섬 추방 [플레이어] [사유] : 자신의 섬에 있는 [플레이어]를 §c추방§f합니다.",
            "§6§l│ §f/섬 규칙 §a추가 §f[이름] : [이름]의 규칙을 §a추가합니다. §f佳 10줄까지 §a가능",
            "§6§l│ §f/섬 규칙 제거 [번호] : [번호]의 규칙을 §c제거§f합니다. ",
            "§6§l│ §f/섬 규칙 보기 : 섬 규칙을 확인합니다.  ",
            "§6§l│ §f/섬 금고 입금 <Amount> : 금고에 <Amount>만큼의 §e금액§f을 §a입금§f합니다. ",
            "§6§l│ §f/섬 금고 출금 <Amount> : 금고에 <Amount>만큼의 §e금액§f을 §c출금§f합니다.  ",
            "§6§l│ §f/섬 금고 기록 : 금고의 §6입출금 기록§f을 띄웁니다.  ",
            "§6§l│ §f/섬 금고 잠금 : 금고 출금을 금지시킵니다.  ",
            "§6§l│ §f/섬 디스코드 설정 [링크] : 섬 디스코드 링크를 [링크]로 §6설정§f합니다. ",
            "§6§l│ §f/섬 디스코드 삭제 : [링크]를 §a삭제§f합니다.  ",
            "§6§l│ §f/섬 방문객 : 최근 방문한 방문객을 확인합니다.",
            "§6§l│ §f/섬 이름변경 [이름] : 섬의 이름을 [이름]으로 §6변경§f합니다. ",
            "§6§l│ §f/섬 스폰변경 : 섬 스폰위치를 §a변경§f합니다.  ",
            "§6§l│ §f/섬 업그레이드 : 섬 업그레이드 GUI를 띄웁니다.",
            "§6§l│ §f/섬 양도 [플레이어] : 섬 §b소유권§f을 [플레이어]에게 §c양도§f합니다.  ",
            "§6§l│ §f/섬 권한 : 섬 §6권한 §f관련된 GUI를 띄웁니다.",
            "§6§l│ §f/섬 호퍼 : 섬에 설치된 §8호퍼 §f갯수를 확인합니다.",
            "§6§l│ §f/섬 초기화 : 섬을 §c초기화§f합니다.",
            "§6§l│ §f/섬 홈 : 자신의 섬 스폰을 §a지정§f합니다.",
            "§6§l│ §f/섬 설정 : 섬 세부 사항을 §6설정§fg합니다. \n" +
                    "§6§l│ ㄴ §c밴블록, §f시간§7(아침/점심/저녁/새벽)§f, 날씨§7(맑음/비/번개), pvp",
            "/섬 옵션 시간 <시간> : 섬의 시간을 설정 합니다.",
            "/섬 옵션 월드보더 끄기 : 섬의 월드보더를 끕니다.",
            "/섬 옵션 월드보더 보기 : 섬 월드보더를 보여지게 합니다.",
            "",
            "§6§l│ §f/섬 옵션 전투 §a허용 §7(사망시 섬 스폰으로 이동 됩니다. §7(패널티 X)",
            "§6§l│ §f/섬 옵션 전투 §c비허용 §7(섬에서의 전투를 비활성화 합니다.)",

            "§6§l│ §f/섬 옵션 방문 허용 : 섬 방문을 §a허용§f합니다.",
            "§6§l│ §f/섬 옵션 방문 잠금 : 섬 방문을 §a잠금§f합니다.",

            "§6§l│ §f/섬 옵션 밴블록 알바 : 알바의 §c밴블록§f을 §6설정§f합니다.",
            "§6§l│ §f/섬 옵션 밴블록 섬원 : 섬원의 §c밴블록§f을 §6설정§f합니다.",
            "§6§l│ §f/섬 옵션 전투 허용 : 섬 §c전투§f를 §a허용§f합니다.",
            "§6§l│ §f/섬 블랙리스트 추가 <플레이어> <사유> : §6플레이어§f를 섬 §c블랙리스트§f에 §a추가§f합니다.",
            "§6§l│ §f/섬 블랙리스트 제거 <Player> : 섬에 §c블랙리스트§f한 §6플레이어§f를 §c제거§f합니다.",
            "§6§l│ §f/섬 블랙리스트 목록 : 섬 §c블랙리스트 §6목록§f을 확인합니다.",
            "§6§l│ §f/섬 알바 추가 [이름] [돈] : §6플레이어§f를 섬 §e알바§f로 §a추가§f합니다.",
            "§6§l│ §f/섬 알바 제거 [이름] : §e알바§f가 끝난 §6플레이어§f를 §c제거§f합니다.",
            "§6§l│ §f/섬 순위 [페이지] : 모든 섬의 §6순위§f를 §a확인§f합니다.",
            "§6§l│ §f/섬 섬원 : 섬원 GUI를 띄웁니다.");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 0) {
                SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                if (playerData.hasIsland()) {
                    SkyBlock data = new SkyBlock(playerData.getIsland());
                    data.spawn(player, data.getLocation());

                    player.sendMessage("架 " + "자신의 섬으로 이동하였습니다. " + ChatColor.GRAY + "[/섬 도움말]");
                } else {
                    player.sendMessage("强 가입한 섬이 없습니다." + ChatColor.GREEN + " [/섬 생성 <이름>]");
                }


            } else {

                switch (args[0]) {
                    case "도움말" -> {
                        if (args.length > 1) {
                            int page = Integer.parseInt(args[1]);
                            List<String> test = message(player, help, page);
                            for (String text : test) {
                                player.sendMessage(text);
                            }
                            player.sendMessage("§6§l│ §f家 섬 §6도움말 §f페이지 §7[" + page + "/4]");
                        } else {
                            List<String> test = message(player, help, 1);
                            for (String text : test) {
                                player.sendMessage(text);
                            }
                            player.sendMessage("§6§l│ §f家 섬 §6도움말 §f페이지 §7[1/4]");
                        }
                    }
                    case "메뉴" -> {
                        Menu menu = new Menu("섬");
                        menu.load(player);
                    }
                    case "양도" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        Player target = Bukkit.getPlayer(args[1]);

                        SkyBlock data = new SkyBlock(playerData.getIsland());

                        if (data.setOwner(target)) {
                            player.sendMessage("强 §6" + target.getDisplayName() + "§f님에게 섬을 양도하였습니다!");
                        }
                    }
                    case "탈퇴" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        SkyBlock data = new SkyBlock(playerData.getIsland());

                        data.quickSkyBlock(player);

                    }
                    case "추방" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                        SkyBlock island = new SkyBlock(playerData.getIsland());

                        Translate translate = new Translate();
                        String reason = translate.collapse(args, 2);

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
                    }
                    case "초대" -> {

                        Player target = Bukkit.getPlayer(args[1]);

                        assert target != null;

                        SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);

                        InviteSkyBlock inviteSkyBlock = new InviteSkyBlock(player);

                        if (!targetData.hasIsland()) {
                            if (!SkyBlockData.inviteSkyBlock.containsKey(target.getUniqueId())) {
                                inviteSkyBlock.send(target);
                                SkyBlockData.inviteSkyBlock.put(target.getUniqueId(), inviteSkyBlock);
                            } else {
                                player.sendMessage("强 이미 초대 요청을 보낸 플레이어입니다!");
                            }
                        } else {
                            player.sendMessage("强 §6" + target.getDisplayName() + "§f님은 이미 섬에 소속 되어 있습니다!");
                        }
                    }
                    case "수락" -> {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (SkyBlockData.inviteSkyBlock.containsKey(player.getUniqueId())) {
                            SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);
                            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                            playerData.setName(targetData.getIsland());

                            SkyBlock islandData = new SkyBlock(targetData.getIsland());

                            islandData.accept(target, player);

                            player.sendMessage("架 §6" + targetData.getIsland() + " §f섬에 입장하였습니다!");

                            SkyBlockData.inviteSkyBlock.remove(player.getUniqueId());
                        } else {
                            player.sendMessage("强 초대 수락에 실패 하였습니다!");
                        }


                    }
                    case "거절" -> {
                        SkyBlockRequest request = new SkyBlockRequest();
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        Player target = Bukkit.getPlayer(args[1]);


                        SkyBlock data = new SkyBlock(playerData.getIsland());
                        if (SkyBlockRequest.deny(request, player, target)) {
                            target.sendMessage("家 §6" + player.getDisplayName() + "§f님이 초대 요청을 §c거절 §f하였습니다!");
                            player.sendMessage("架 초대 요청을 거절 하였습니다!");
                        } else {
                            player.sendMessage("强 초대 요청 §c거절§f을 §c실패 §f하였습니다! ");
                        }
                    }
                    case "블랙리스트" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                        SkyBlock skyBlock = new SkyBlock(playerData.getIsland());

                        if (args.length > 1) {
                            if (args[0].equalsIgnoreCase("추가")) {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                                String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                                if (skyBlock.addBlackList(player, target, reason)) {
                                    player.sendMessage("架 §6" + target.getName() + "§f님을 §e'" + reason + "'§f의 사유로 블랙리스트에 §a추가 §f하였습니다.");
                                }
                            } else if (args[0].equalsIgnoreCase("해제")) {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                                if (skyBlock.removeBlackList(target)) {
                                    player.sendMessage("架 §6" + target.getName() + "§f님을 섬 밴 목록에서 삭제 하였습니다.");
                                }
                            }
                        }
                    }
                    case "홈" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        playerData.setSpawn();
                    }

                    case "스폰" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        if (playerData.setSpawn()) {
                            player.sendMessage("架 현재 있는 위치를 섬 스폰으로 설정되었습니다!");
                        }
                    }

                    case "제거" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        SkyBlock data = new SkyBlock(playerData.getIsland());
                        if (!SkyBlockData.delete.containsKey(player.getUniqueId())) {
                            player.sendMessage("可 정말로 섬을 §c삭제§f하시겠습니까? §7(15초안에 다시 타이핑 해주세요!)");
                            DeleteRunnable runnable = new DeleteRunnable(player);

                            runnable.runTaskTimer(SkyExcelNetworkMain.getPlugin(), 0, 20);
                            SkyBlockData.delete.put(player.getUniqueId(), runnable);

                        } else {
                            data.remove(player);
                            SkyBlockData.delete.remove(player.getUniqueId());
                        }

                    }
                    case "생성" -> {
                        SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);
                        if (!skyBlockPlayerData.hasIsland()) {
                            if (args.length > 1) {
                                Translate translate = new Translate();
                                String name = translate.collapse(args, 1);
                                SkyBlock data = new SkyBlock(player, name);

                                if (name.length() <= 30) {
                                    data.create(player);
                                } else {
                                    player.sendMessage("强 섬 이름은 30글자 미만 이어야 합니다.");
                                }
                            } else {
                                player.sendMessage("섬 이름을 입력해 주세요!");
                            }

                        } else {
                            player.sendMessage("당신은 이미 섬에 가입되어 있습니다.");
                        }
                    }


                    case "이름" -> {
                        Translate translate = new Translate();
                        String name = translate.collapse(args, 1);

                        SkyBlock data = new SkyBlock(name);
                        if (data.rename(name)) {
                            player.sendMessage("架 섬 이름을 §6" + name + "§f으로 바꾸었습니다!");
                        }
                    }

                    case "방문" -> {
                        if (args.length > 1) {

                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                            System.out.println("test" + target);
                            assert target != null;
                            SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);

                            SkyBlock skyBlock = new SkyBlock(targetData.getIsland());
                            skyBlock.visitSkyBlock(player, target);

                        } else {
                            player.sendMessage("强 방문할 섬 이름을 입력해주세요.");
                        }
                    }
                    case "금고" -> {

                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);

                        if (playerData.hasIsland()) {
                            SkyBlock data = new SkyBlock(player, playerData.getIsland());
                            SkyBlockVault vault;
                            int amount;
                            SEConomy money;

                            if (args.length > 1) {
                                switch (args[1]) {
                                    case "입금" -> {
                                        data = new SkyBlock(player, playerData.getIsland());
                                        vault = data.getVault();
                                        amount = Integer.parseInt(args[2]);
                                        money = new SEConomy(player);


                                        if (vault.deposit(amount) && money.withdraw(amount)) {
                                            SkyBlockVaultRecord record = new SkyBlockVaultRecord(playerData.getIsland());

                                            record.record(player, amount, SkyBlockVaultRecord.Type.DEPOSIT);

                                            player.sendMessage("架 섬에 §6" + format(amount) + "§f을 입금 하였습니다!");

                                            List<String> members = data.getMembers();
                                            for (String member : members) {
                                                Player online = Bukkit.getPlayer(member);
                                                online.getPlayer().sendMessage("家 " + player.getPlayer().getDisplayName() + "님이 섬 금고에 §6" + format(amount) + "을 입금 하였습니다!");
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
                                        money = new SEConomy(player);

                                        if (vault.withdraw(amount)) {
                                            money.deposit(amount);
                                            SkyBlockVaultRecord record = new SkyBlockVaultRecord(playerData.getIsland());

                                            record.record(player, amount, SkyBlockVaultRecord.Type.WITHDRAW);

                                            player.sendMessage("架 섬 금고에서 §6" + format(amount) + "§f을 출금 하였습니다!");

                                            List<String> members = data.getMembers();
                                            for (String member : members) {
                                                Player online = Bukkit.getPlayer(member);
                                                online.getPlayer().sendMessage("家 " + player.getPlayer().getDisplayName() + "님이 섬 금고에서 §6" + format(amount) + "을 출금 하였습니다!");
                                            }
                                        } else {
                                            player.sendMessage("强 금고에서 §6출금§f을 §c실패§f하였습니다!");
                                        }
                                    }
                                    case "잠금" -> data.setVaultLock();
                                }
                            }

                        } else {
                            player.sendMessage("强 소속되어있는 섬이 없어 옵션 설정이 불가능합니다! ");
                        }
                    }
                    case "디스코드" -> {
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
                    }
                    case "알바" -> {
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
                                            player.sendMessage("强 올바른 숫자를 입력해 주세요!");
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
                    }
                    case "규칙" -> {
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
                    }
                    case "기록" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                        SkyBlock data = new SkyBlock(playerData.getIsland());

                        if ("초기화".equals(args[1])) {
                            player.sendMessage("架 기록을 초기화 했습니다!");
                            if (player.isOp())
                                data.removeAll();
                        }
                    }
                    case "초기화" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                        SkyBlock data = new SkyBlock(playerData.getIsland());
                        player.sendMessage("架 섬에 있는 모든 기록을 §6초기화§f하였습니다!");
                        data.reset(player);
                    }

                    case "방문객" -> {

                        PageVisitor visitor = new PageVisitor("방문객");
                        visitor.update(player);


                    }
                    case "업그레이드" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                        if (playerData.isOwner()) {
                            UpgradeGUI upgradeGUI = new UpgradeGUI(playerData.getIsland());
                            upgradeGUI.create(player);
                        }

                    }
                    case "옵션" -> {
                        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
                        SkyBlock data = new SkyBlock(playerData.getIsland());

                        System.out.println(playerData.hasIsland());
                        if (playerData.hasIsland()) {
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
                                    } else {
                                        player.sendMessage("밴블록 타겟을 선택 해 주세요!");
                                    }
                                    break;
                                case "전투":
                                    if (args.length > 2) {
                                        switch (args[2]) {
                                            case "활성화" -> {
                                                data.setPvp(true);
                                                player.sendMessage("§a● §f전투 활성화");
                                            }
                                            case "비활성화" -> {
                                                data.setPvp(false);
                                                player.sendMessage("§c● §f전투 비활성화");
                                            }
                                        }
                                    } else {
                                        player.sendMessage("强 전투허용 옵션을 선택 해 주세요!");
                                    }

                                    break;
                                case "워프":
                                    Translate translate = new Translate();
                                    if (args.length > 2) {
                                        switch (args[2]) {
                                            case "생성" -> {
                                                if (args.length > 3) {
                                                    String name = translate.collapse(args, 3);
                                                    data.addWarp(player);
                                                    player.sendMessage(name + " 의 워프지역이 생성 되었습니다!");
                                                }
                                            }
                                            case "제거" -> {
                                            }

                                        }
                                    }
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
                                            case "노을" -> {
                                                data.time(player, 1);
                                                player.sendMessage("§a● §7노을§f으로 변경하였습니다.");
                                            }
                                            case "일몰" -> {
                                                data.time(player, 12000);
                                                player.sendMessage("§a● §f§6일몰§f 시간으로 변경하였습니다.");
                                            }
                                            case "일출" -> {
                                                data.time(player, 23000);
                                                player.sendMessage("§a● §f일출§f 시간으로 변경하였습니다.");
                                            }
                                            case "밤" -> {
                                                data.time(player, 18000);
                                                player.sendMessage("§a● §0밤§f 시간으로 변경하였습니다.");
                                            }
                                        }
                                    } else {
                                        player.sendMessage("强 시간대를 입력 해 주세요!");
                                    }
                                    break;
                                case "월드보더":
                                    data.setWorldBorderVisibilty(player);
                                    break;

                                case "열기":
                                    if (args.length > 2) {
                                        if ("허용".equalsIgnoreCase(args[2])) {
                                            data.setOpen(player, true);
                                        } else if ("비허용".equalsIgnoreCase(args[2])) {
                                            data.setOpen(player, false);
                                        }
                                    }

                                    break;
                            }
                        }
                    }

                }
            }

        }
        return false;
    }

    // 0 ~ 10
    private List<String> message(Player player, List<String> help, int index) {
        List<String> result = new ArrayList<>();


        try {
            for (int i = 10 * (index - 1); i < 10 * (index); i++) {
                String line = help.get(i);
                result.add(line);

                if (help.get(i) == null) {
                    player.sendMessage("强 해당 페이지는 존재하지 않습니다.");
                    break;
                }

            }
        } catch (IndexOutOfBoundsException e) {

        }

        return result;
    }

    private String format(long amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        return decFormat.format(amount);
    }
}
