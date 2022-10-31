package net.skyexcel.server.island;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.server.Location;
import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.SkyBlockData;
import net.skyexcel.server.data.economy.SEconomy;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.data.player.SkyBlockRequest;
import net.skyexcel.server.data.vault.SkyBlockVault;
import net.skyexcel.server.data.vault.SkyBlockVaultRecord;
import net.skyexcel.server.menu.Menu;
import net.skyexcel.server.ui.gui.MaterialPageMember;
import net.skyexcel.server.ui.gui.MaterialPagePartTime;
import net.skyexcel.server.ui.title.Loading;
import net.skyexcel.server.util.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.OfflinePlayer;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.command.function.Cmd;


import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class IslandCmd implements TabCompleter {
    public IslandCmd() {


        Cmd cmd = new Cmd(SkyBlockCore.plugin, "섬");
        Bukkit.getServer().getPluginCommand("섬").setTabCompleter(this);

        SkyBlockRequest request = new SkyBlockRequest();


        cmd.label(action -> {
            Player player = (Player) action.getSender();

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
            SkyBlock data = new SkyBlock(playerData.getIsland());

            if (data.teleportSkyBlock(player)) {
                data.enableWorldBorder(player);
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

            if (!targetData.hasIsland()) {
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
            data.delete(player);
        });

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            String name = String.join(" ", Arrays.copyOfRange(action.getArgs(), 1, action.getArgs().length));
            SkyBlock data = new SkyBlock(player, name);

            if (!data.equalFileName(name)) {

                try {
                    data.create(player);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                Loading loading = new Loading(player, 5);

                loading.runTaskTimer(SkyBlockCore.plugin, 0, 10);

                loading.end(end -> {
                    if (data.getLocation() != null)
                        data.spawn(player, data.getLocation());
                });


                SkyBlockData.loading.put(player.getUniqueId(), loading);
            } else {
                player.sendMessage("이미 이름이 있습니다!");
            }

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

        cmd.action("방문", 0, action -> {
            Player player = (Player) action.getSender();

            if (action.getArgs().length > 1) {

                Player target = Bukkit.getPlayer(action.getArgs()[1]);

                assert target != null;
                SkyBlockPlayerData targetData = new SkyBlockPlayerData(target);

                SkyBlock skyBlock = new SkyBlock(targetData.getIsland());
                skyBlock.teleportSkyBlock(player);

                player.sendMessage(target.getDisplayName() + ChatColor.GREEN + " 님의 섬을 방문 했습니다!");
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
                    if (args.length > 2) {
                        String name = args[2];
                        if (args.length > 3) {
                            try {
                                int amount = Integer.parseInt(args[3]);

                                Player target = Bukkit.getPlayer(name);

                                if (data.addPartTime(target, amount)) {
                                    player.sendMessage("알바 추가가 되었습니다!");
                                    target.sendMessage(player.getDisplayName() + " 님이 당신을 알바로 고용했습니다!");
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
                                MaterialPagePartTime partTime = new MaterialPagePartTime("알바 밴블록");
                                partTime.update(player);

                                SkyBlockData.partTimePage.put(player.getUniqueId(), partTime);

                                player.openInventory(partTime.getInv());
                                break;
                            case "섬원":
                                MaterialPageMember member = new MaterialPageMember("섬원 밴블록");

                                member.update(player);
                                player.openInventory(member.getInv());

                                SkyBlockData.memberPage.put(player.getUniqueId(), member);
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
                case "시간":
                    if (args.length > 2) {
                        switch (args[2]) {
                            case "아침":
                                data.time(player, 1000);
                                break;
                            case "점심":
                                data.time(player, 6000);
                                break;
                            case "저녁":
                                data.time(player, 1);
                                break;
                            case "일몰":
                                data.time(player, 12000);
                                break;
                            case "일출":
                                data.time(player, 23000);
                                break;
                            case "밤":
                                data.time(player, 18000);
                                break;


                        }

                    }
                    break;
                case "월드보더":
                    data.disableWorldBorder(player);
                    break;

                case "열기":

                    break;
            }
        });

    }

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
