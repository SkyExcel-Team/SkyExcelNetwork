package net.skyexcel.server.cosmetic.cmd;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.CosmeticType;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import net.skyexcel.server.cosmetic.gui.CosmeticMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CosmeticCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("强 §c해당 명령어는 플레이어만 사용가능합니다!");
            return false;
        }

        if (args.length == 0) {
            sendHelp(player);
            return false;
        }

        if (List.of("도움말", "help", "?").contains(args[0])) {
            if (args.length > 1) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            sendHelp(player);
            return true;
        } else if (List.of("목록", "list").contains(args[0])) {
            if (args.length > 2) {
                if (player.isOp()) { //어드민 명령어
                    if (args.length > 3) {
                        player.sendMessage("强 §c잘못된 명령어 입니다!");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        player.sendMessage("强 해당 플레이어를 찾을 수 없습니다!");
                        return false;
                    }

                    PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(target);
                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        List<Cosmetic.BACK> backs = playerCosmeticData.getBackCosmetics();
                        backs.remove(Cosmetic.BACK.NONE);

                        if (backs.size() == 0) {
                            player.sendMessage("强 §6" + target.getDisplayName() + " §e← §f보유중인 §6등 코스튬§f이 없습니다.");
                            return true;
                        }

                        List<String> backNames = new ArrayList<>();
                        backs.forEach(back -> backNames.add(back.name()));

                        player.sendMessage("[등 코스튬 목록]\n" + String.join(", ", backNames));
                        return true;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        List<Cosmetic.HAT> hats = playerCosmeticData.getHatCosmetics();
                        hats.remove(Cosmetic.HAT.NONE);

                        if (hats.size() == 0) {
                            player.sendMessage("强 §6" + target.getDisplayName() + " §e← §f보유중인 §6모자 코스튬§f이 없습니다.");
                            return true;
                        }

                        List<String> hatNames = new ArrayList<>();
                        hats.forEach(hat -> hatNames.add(hat.name()));

                        player.sendMessage("[등 코스튬 목록]\n" + String.join(", ", hatNames));
                        return true;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        List<Cosmetic.OFFHAND> offhands = playerCosmeticData.getOffhandCosmetics();
                        offhands.remove(Cosmetic.OFFHAND.NONE);

                        if (offhands.size() == 0) {
                            player.sendMessage("强 §6" + target.getDisplayName() + " §e← §f보유중인 §6왼손 코스튬§f이 없습니다.");
                            return true;
                        }

                        List<String> offhandNames = new ArrayList<>();
                        offhands.forEach(offhand -> offhandNames.add(offhand.name()));

                        player.sendMessage("[등 코스튬 목록]\n" + String.join(", ", offhandNames));
                        return true;
                    } else {
                        player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                        return false;
                    }
                }

                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            } else if (args.length < 2) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                List<Cosmetic.BACK> cosmetics = playerCosmeticData.getBackCosmetics();
                cosmetics.remove(Cosmetic.BACK.NONE);

                if (cosmetics.size() == 0) {
                    player.sendMessage("强 §f보유중인 §6등 코스튬§f이 없습니다.");
                    return true;
                }

                List<String> backNames = new ArrayList<>();
                cosmetics.forEach(back -> backNames.add(back.getName()));

                player.sendMessage("[등 코스튬 목록]\n" + String.join(", ", backNames));
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                List<Cosmetic.HAT> cosmetics = playerCosmeticData.getHatCosmetics();
                cosmetics.remove(Cosmetic.HAT.NONE);

                if (cosmetics.size() == 0) {
                    player.sendMessage("强 §f보유중인 §6모자 코스튬§f이 없습니다.");
                    return true;
                }

                List<String> hatNames = new ArrayList<>();
                cosmetics.forEach(hat -> hatNames.add(hat.getName()));

                player.sendMessage("[등 코스튬 목록]\n" + String.join(", ", hatNames));
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                List<Cosmetic.OFFHAND> cosmetics = playerCosmeticData.getOffhandCosmetics();
                cosmetics.remove(Cosmetic.OFFHAND.NONE);

                if (cosmetics.size() == 0) {
                    player.sendMessage("强 §f보유중인 §6왼손 코스튬§f이 없습니다.");
                    return true;
                }

                List<String> offhandNames = new ArrayList<>();
                cosmetics.forEach(offhand -> offhandNames.add(offhand.getName()));

                player.sendMessage("[등 코스튬 목록]\n" + String.join(", ", offhandNames));
                return true;
            } else {
                player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                return false;
            }
        } else if (List.of("장착", "wear").contains(args[0])) {
            if (args.length > 3) {
                if (player.isOp()) { //어드민 명령어
                    if (args.length > 4) {
                        player.sendMessage("强 §c잘못된 명령어 입니다!");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[3]);
                    if (target == null) {
                        player.sendMessage("强 해당 플레이어를 찾을 수 없습니다!");
                        return false;
                    }

                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        Cosmetic.BACK cosmetic;

                        try {
                            cosmetic = Cosmetic.BACK.valueOf(args[2]);
                        } catch (Exception ignored) {
                            player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                            return false;
                        }

                        PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(target);
                        if (!playerCosmeticData.getBackCosmetics().contains(cosmetic)) {
                            player.sendMessage("架 §6" + target.getDisplayName() + "§f님에게 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                        }

                        playerCosmeticData.setWearBackCosmetic(cosmetic);
                        player.sendMessage("架 성공적으로 §6" + target.getDisplayName() + "§f님에게 §6등 코스튬§f을 §a적용§f했습니다!");
                        return true;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        Cosmetic.HAT cosmetic;

                        try {
                            cosmetic = Cosmetic.HAT.valueOf(args[2]);
                        } catch (Exception ignored) {
                            player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                            return false;
                        }

                        PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(target);
                        if (!playerCosmeticData.getHatCosmetics().contains(cosmetic)) {
                            player.sendMessage("架 §6" + target.getDisplayName() + "§f님에게 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                        }

                        playerCosmeticData.setWearHatCosmetic(cosmetic);
                        player.sendMessage("架 성공적으로 §6" + target.getDisplayName() + "§f님에게 §6모자 코스튬§f을 §a적용§f했습니다!");
                        return true;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        Cosmetic.OFFHAND cosmetic;

                        try {
                            cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                        } catch (Exception ignored) {
                            player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                            return false;
                        }

                        PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(target);
                        if (!playerCosmeticData.getOffhandCosmetics().contains(cosmetic)) {
                            player.sendMessage("架 §6" + target.getDisplayName() + "§f님에게 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                        }

                        playerCosmeticData.setWearOffhandCosmetic(cosmetic);
                        player.sendMessage("架 성공적으로 §6" + target.getDisplayName() + "§f님에게 §6왼손 코스튬§f을 §a적용§f했습니다!");
                        return true;
                    } else {
                        player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                        return false;
                    }
                }

                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            } else if (args.length < 3) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                Cosmetic.BACK cosmetic;

                try {
                    cosmetic = Cosmetic.BACK.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getBackCosmetics().contains(cosmetic)) {
                    if (player.isOp()) player.sendMessage("架 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                    else {
                        player.sendMessage("强 보유하지 않은 §6코스튬§f입니다.");
                        return false;
                    }
                }

                playerCosmeticData.setWearBackCosmetic(cosmetic);
                player.sendMessage("架 성공적으로 §6등 코스튬§f을 §a적용§f했습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                Cosmetic.HAT cosmetic;

                try {
                    cosmetic = Cosmetic.HAT.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getHatCosmetics().contains(cosmetic)) {
                    if (player.isOp()) player.sendMessage("架 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                    else {
                        player.sendMessage("强 보유하지 않은 §6코스튬§f입니다.");
                        return false;
                    }
                }

                playerCosmeticData.setWearHatCosmetic(cosmetic);
                player.sendMessage("架 성공적으로 §6모자 코스튬§f을 §a적용§f했습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                Cosmetic.OFFHAND cosmetic;

                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getOffhandCosmetics().contains(cosmetic)) {
                    if (!player.isOp()) {
                        player.sendMessage("强 보유하지 않은 §6코스튬§f입니다.");
                        return true;
                    } else player.sendMessage("架 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                }

                playerCosmeticData.setWearOffhandCosmetic(cosmetic);
                player.sendMessage("架 성공적으로 §6왼손 코스튬§f을 §a적용§f했습니다!");
                return true;
            } else {
                player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                return false;
            }
        } else if (List.of("제거", "remove").contains(args[0])) {
            if (args.length > 2) {
                if (player.isOp()) { //어드민 명령어
                    if (args.length > 3) {
                        player.sendMessage("强 §c잘못된 명령어 입니다!");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        player.sendMessage("强 해당 플레이어를 찾을 수 없습니다!");
                        return false;
                    }

                    PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(target);
                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        playerCosmeticData.setWearBackCosmetic(Cosmetic.BACK.NONE);
                        player.sendMessage("架 성공적으로 §6" + target.getDisplayName() + "§f님에게 §6등 코스튬§f을 §c제거§f하였습니다!");
                        return true;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        playerCosmeticData.setWearHatCosmetic(Cosmetic.HAT.NONE);
                        player.sendMessage("架 성공적으로 §6" + target.getDisplayName() + "§f님에게 §6모자 코스튬§f을 §c제거§f하였습니다!");
                        return true;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        playerCosmeticData.setWearOffhandCosmetic(Cosmetic.OFFHAND.NONE);
                        player.sendMessage("架 성공적으로 §6" + target.getDisplayName() + "§f님에게 §6왼손 코스튬§f을 §c제거§f하였습니다!");
                        return true;
                    } else {
                        player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                        return false;
                    }
                }

                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            } else if (args.length < 2) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                playerCosmeticData.setWearBackCosmetic(Cosmetic.BACK.NONE);
                player.sendMessage("架 성공적으로 §6등 코스튬§f을 §c제거§f하였습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                playerCosmeticData.setWearHatCosmetic(Cosmetic.HAT.NONE);
                player.sendMessage("架 성공적으로 §6모자 코스튬§f을 §c제거§f하였습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                playerCosmeticData.setWearOffhandCosmetic(Cosmetic.OFFHAND.NONE);
                player.sendMessage("架 성공적으로 §6왼손 코스튬§f을 §c제거§f하였습니다!");
                return true;
            } else {
                player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                return false;
            }
        } else if (List.of("주기", "give").contains(args[0])) {
            if (args.length > 4) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            } else if (args.length < 4) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            if (!player.isOp()) {
                player.sendMessage("§f强 해당 §6명령어§f는 §cOP§f만 §a사용§f가능합니다!");
                return false;
            }

            Player target = Bukkit.getPlayer(args[3]);
            if (target == null) {
                player.sendMessage("强 해당 플레이어를 찾을 수 없습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                Cosmetic.BACK cosmetic;

                try {
                    cosmetic = Cosmetic.BACK.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (playerCosmeticData.getBackCosmetics().contains(cosmetic)) {
                    player.sendMessage("强 해당 플레이어는 이미 해당 §6코스튬§f을 가지고 있습니다!");
                    return false;
                }

                playerCosmeticData.addCosmetic(cosmetic);

                player.sendMessage("架 성공적으로 §6등 코스튬§f을 §a지급§f했습니다.");
                target.sendMessage(player.getDisplayName() + "님이 등 코스튬(" + cosmetic.getName() + ")을 지급했습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                Cosmetic.HAT cosmetic;

                try {
                    cosmetic = Cosmetic.HAT.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (playerCosmeticData.getHatCosmetics().contains(cosmetic)) {
                    player.sendMessage("强 해당 플레이어는 이미 해당 §6코스튬§f을 가지고 있습니다!");
                    return false;
                }

                playerCosmeticData.addCosmetic(cosmetic);

                player.sendMessage("架 성공적으로 §6모자 코스튬§f을 §a지급§f했습니다.");
                target.sendMessage("佳 §6" + player.getDisplayName() + "§f님이 §6모자 코스튬 §8(§7" + cosmetic.getName() + "§8)§f을 §a지급§f했습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                Cosmetic.OFFHAND cosmetic;

                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (playerCosmeticData.getOffhandCosmetics().contains(cosmetic)) {
                    player.sendMessage("强 해당 플레이어는 이미 해당 §6코스튬§f을 가지고 있습니다!");
                    return false;
                }

                playerCosmeticData.addCosmetic(cosmetic);

                player.sendMessage("성공적으로 왼손 코스튬을 지급했습니다.");
                target.sendMessage("佳 §6" + player.getDisplayName() + "§f님이 §6왼손 코스튬 §8(§7" + cosmetic.getName() + "§8)§f을 §a지급§f했습니다!");
                return true;
            } else {
                player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                return false;
            }
        } else if (List.of("뺏기", "take").contains(args[0])) {
            if (args.length > 4) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            } else if (args.length < 4) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            if (!player.isOp()) {
                player.sendMessage("§f强 해당 §6명령어§f는 §cOP§f만 §a사용§f가능합니다!");
                return false;
            }

            Player target = Bukkit.getPlayer(args[3]);
            if (target == null) {
                player.sendMessage("强 해당 플레이어를 찾을 수 없습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                Cosmetic.BACK cosmetic;

                try {
                    cosmetic = Cosmetic.BACK.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getBackCosmetics().contains(cosmetic)) {
                    player.sendMessage("强 보유중이지 않은 §6코스튬§f입니다.");
                    return false;
                }

                playerCosmeticData.removeCosmetic(cosmetic);

                player.sendMessage("架 성공적으로 §6등 코스튬§f을 §c빼앗았습니다.");
                target.sendMessage("佳 §6" + player.getDisplayName() + "§f님이 §6등 코스튬 §8(§7" + cosmetic.getName() + "§8)§f을 §c빼앗았습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                Cosmetic.HAT cosmetic;

                try {
                    cosmetic = Cosmetic.HAT.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getHatCosmetics().contains(cosmetic)) {
                    player.sendMessage("强 보유중이지 않은 §6코스튬§f입니다.");
                    return false;
                }

                playerCosmeticData.removeCosmetic(cosmetic);

                player.sendMessage("架 성공적으로 §6모자 코스튬§f을 §c빼앗았습니다.");
                target.sendMessage("佳 §6" + player.getDisplayName() + "§f님이 §6모자 코스튬 §8(§7" + cosmetic.getName() + "§8)§f을 §c빼앗았습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                Cosmetic.OFFHAND cosmetic;

                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                    return false;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getOffhandCosmetics().contains(cosmetic)) {
                    player.sendMessage("强 보유중이지 않은 §6코스튬§f입니다.");
                    return false;
                }

                playerCosmeticData.removeCosmetic(cosmetic);

                player.sendMessage("架 성공적으로 §6왼손 코스튬§f을 §c빼앗았습니다.");
                target.sendMessage("佳 §6" + player.getDisplayName() + "§f님이 §6왼손 코스튬 §8(§7" + cosmetic.getName() + "§8)§f을 §c빼앗았습니다!");
                return true;
            } else {
                player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                return false;
            }
        } else if (List.of("메뉴", "menu").contains(args[0].toLowerCase())) {
            if (args.length > 2) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            } else if (args.length < 2) {
                player.sendMessage("强 §c잘못된 명령어 입니다!");
                return false;
            }

            if (!SkyExcelNetworkMain.isLoaded(player)) {
                player.sendMessage("佳 §c잠시 후 다시 시도해주세요!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                new CosmeticMenu.Menu(player, CosmeticType.BACK, 1).openInventory();

                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                new CosmeticMenu.Menu(player, CosmeticType.HAT, 1).openInventory();

                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                new CosmeticMenu.Menu(player, CosmeticType.OFFHAND, 1).openInventory();

                return true;
            } else {
                player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");
                return false;
            }
        }

        sendHelp(player);
        return false;
    }

    private void sendHelp(Player player) {
        player.sendMessage("§8■ §7══════°• §8[ §6코스튬 §f도움말 §8] §7•°══════ §8■");
        player.sendMessage("§6> §f家 §6코스튬§f을 꾸밉니다.");
        player.sendMessage("");
        player.sendMessage("§6> §f/코스튬 도움말 §8- §f도움말을 확인합니다.");
        player.sendMessage("§6> §f/코스튬 목록 <등|모자|왼손> §8- §f보유중인 코스튬을 확인합니다.");
        player.sendMessage("§6> §f/코스튬 장착 <등|모자|왼손> <코스튬>§8- §6코스튬§f을 장착합니다.");
        player.sendMessage("§6> §f/코스튬 제거 <등|모자|왼손>§8- §6코스튬§f을 해제합니다.");
        player.sendMessage("§8■ §7══════°• ═════════════════════ §7•°══════ §8■");
    }
}
