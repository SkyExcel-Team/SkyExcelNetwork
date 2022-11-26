package net.skyexcel.server.cosmetic.cmd;

import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
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
            sender.sendMessage("해당 명령어는 플레이어만 사용할 수 있습니다!");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("/코스튬 [도움말|목록|장착|제거]");
            return false;
        }

        if (List.of("도움말", "help", "?").contains(args[0])) {
            if (args.length > 1) {
                player.sendMessage("입력값이 많습니다!");
                return false;
            }

            player.sendMessage("/코스튬 [도움말|목록|장착|제거]");
            return true;
        } else if (List.of("목록", "list").contains(args[0])) {
            if (args.length > 2) {
                if (player.isOp()) { //어드민 명령어
                    if (args.length > 3) {
                        player.sendMessage("입력값이 많습니다!");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        player.sendMessage("해당 플레이어를 찾을 수 없습니다!");
                        return false;
                    }

                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        List<Cosmetic.BACK> backs = new PlayerCosmeticData(target).getBackCosmetics();
                        backs.remove(Cosmetic.BACK.NONE);

                        if (backs.size() == 0) {
                            player.sendMessage(target.getDisplayName() + " <- 등 치장 하나 없노;");
                            return true;
                        }

                        List<String> backNames = new ArrayList<>();
                        backs.forEach(back -> backNames.add(back.name()));

                        player.sendMessage("[등 치장 목록]\n" + String.join(", ", backNames));
                        return true;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        List<Cosmetic.HAT> hats = new PlayerCosmeticData(target).getHatCosmetics();
                        hats.remove(Cosmetic.HAT.NONE);

                        if (hats.size() == 0) {
                            player.sendMessage(target.getDisplayName() + " <- 모자 하나 없노;");
                            return true;
                        }

                        List<String> hatNames = new ArrayList<>();
                        hats.forEach(hat -> hatNames.add(hat.name()));

                        player.sendMessage("[등 치장 목록]\n" + String.join(", ", hatNames));
                        return true;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        List<Cosmetic.OFFHAND> offhands = new PlayerCosmeticData(target).getOffhandCosmetics();
                        offhands.remove(Cosmetic.OFFHAND.NONE);

                        if (offhands.size() == 0) {
                            player.sendMessage(target.getDisplayName() + " <- 왼손 치장 하나 없노;");
                            return true;
                        }

                        List<String> offhandNames = new ArrayList<>();
                        offhands.forEach(offhand -> offhandNames.add(offhand.name()));

                        player.sendMessage("[등 치장 목록]\n" + String.join(", ", offhandNames));
                        return true;
                    } else {
                        player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                        return false;
                    }
                }

                player.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 2) {
                player.sendMessage("입력값이 적습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                List<Cosmetic.BACK> backs = new PlayerCosmeticData(player).getBackCosmetics();
                backs.remove(Cosmetic.BACK.NONE);

                if (backs.size() == 0) {
                    player.sendMessage("등 치장 하나 없노;");
                    return true;
                }

                List<String> backNames = new ArrayList<>();
                backs.forEach(back -> backNames.add(back.name()));

                player.sendMessage("[등 치장 목록]\n" + String.join(", ", backNames));
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                List<Cosmetic.HAT> hats = new PlayerCosmeticData(player).getHatCosmetics();
                hats.remove(Cosmetic.HAT.NONE);

                if (hats.size() == 0) {
                    player.sendMessage("모자 하나 없노;");
                    return true;
                }

                List<String> hatNames = new ArrayList<>();
                hats.forEach(hat -> hatNames.add(hat.name()));

                player.sendMessage("[등 치장 목록]\n" + String.join(", ", hatNames));
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                List<Cosmetic.OFFHAND> offhands = new PlayerCosmeticData(player).getOffhandCosmetics();
                offhands.remove(Cosmetic.OFFHAND.NONE);

                if (offhands.size() == 0) {
                    player.sendMessage("왼손 치장 하나 없노;");
                    return true;
                }

                List<String> offhandNames = new ArrayList<>();
                offhands.forEach(offhand -> offhandNames.add(offhand.name()));

                player.sendMessage("[등 치장 목록]\n" + String.join(", ", offhandNames));
                return true;
            } else {
                player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                return false;
            }
        } else if (List.of("장착", "wear").contains(args[0])) {
            if (args.length > 3) {
                if (player.isOp()) { //어드민 명령어
                    if (args.length > 4) {
                        player.sendMessage("입력값이 많습니다!");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[3]);
                    if (target == null) {
                        player.sendMessage("해당 플레이어를 찾을 수 없습니다!");
                        return false;
                    }

                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        Cosmetic.BACK cosmetic;

                        try {
                            cosmetic = Cosmetic.BACK.valueOf(args[2]);
                        } catch (Exception ignored) {
                            player.sendMessage("입력된 치장은 존재하지 않습니다.");
                            return false;
                        }

                        if (!new PlayerCosmeticData(target).getBackCosmetics().contains(cosmetic)) {
                            player.sendMessage("치장을 강제 적용합니다.. (미보유 치장)");
                        }

                        new PlayerCosmeticData(target).setWearBackCosmetic(cosmetic);
                        player.sendMessage("성공적으로 " + target.getDisplayName() + "님에게 등 치장을 적용했습니다!");
                        return true;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        Cosmetic.HAT cosmetic;

                        try {
                            cosmetic = Cosmetic.HAT.valueOf(args[2]);
                        } catch (Exception ignored) {
                            player.sendMessage("입력된 치장은 존재하지 않습니다.");
                            return false;
                        }

                        if (!new PlayerCosmeticData(target).getHatCosmetics().contains(cosmetic)) {
                            player.sendMessage("치장을 강제 적용합니다.. (미보유 치장)");
                        }

                        new PlayerCosmeticData(target).setWearHatCosmetic(cosmetic);
                        player.sendMessage("성공적으로 " + target.getDisplayName() + "님에게 모자 치장을 적용했습니다!");
                        return true;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        Cosmetic.OFFHAND cosmetic;

                        try {
                            cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                        } catch (Exception ignored) {
                            player.sendMessage("입력된 치장은 존재하지 않습니다.");
                            return false;
                        }

                        if (!new PlayerCosmeticData(target).getOffhandCosmetics().contains(cosmetic)) {
                            player.sendMessage("치장을 강제 적용합니다.. (미보유 치장)");
                        }

                        new PlayerCosmeticData(target).setWearOffhandCosmetic(cosmetic);
                        player.sendMessage("성공적으로 " + target.getDisplayName() + "님에게 왼손 치장을 적용했습니다!");
                        return true;
                    } else {
                        player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                        return false;
                    }
                }

                player.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 3) {
                player.sendMessage("입력값이 적습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                Cosmetic.BACK cosmetic;

                try {
                    cosmetic = Cosmetic.BACK.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (!player.isOp() && !new PlayerCosmeticData(player).getBackCosmetics().contains(cosmetic)) {
                    if (!player.isOp()) {
                        player.sendMessage("보유하고 있는 치장 아니네;");
                        return true;
                    } else
                        player.sendMessage("치장을 강제 적용합니다.. (미보유 치장, OP전용)");
                }

                new PlayerCosmeticData(player).setWearBackCosmetic(cosmetic);
                player.sendMessage("성공적으로 등 치장을 적용했습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                Cosmetic.HAT cosmetic;

                try {
                    cosmetic = Cosmetic.HAT.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (!player.isOp() && !new PlayerCosmeticData(player).getHatCosmetics().contains(cosmetic)) {
                    if (!player.isOp()) {
                        player.sendMessage("보유하고 있는 치장 아니네;");
                        return true;
                    } else
                        player.sendMessage("치장을 강제 적용합니다.. (미보유 치장, OP전용)");
                }

                new PlayerCosmeticData(player).setWearHatCosmetic(cosmetic);
                player.sendMessage("성공적으로 모자 치장을 적용했습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                Cosmetic.OFFHAND cosmetic;

                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (!new PlayerCosmeticData(player).getOffhandCosmetics().contains(cosmetic)) {
                    if (!player.isOp()) {
                        player.sendMessage("보유하고 있는 치장 아니네;");
                        return true;
                    } else
                        player.sendMessage("치장을 강제 적용합니다.. (미보유 치장, OP전용)");
                }

                new PlayerCosmeticData(player).setWearOffhandCosmetic(cosmetic);
                player.sendMessage("성공적으로 왼손 치장을 적용했습니다!");
                return true;
            } else {
                player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                return false;
            }
        } else if (List.of("제거", "remove").contains(args[0])) {
            if (args.length > 2) {
                if (player.isOp()) { //어드민 명령어
                    if (args.length > 3) {
                        player.sendMessage("입력값이 많습니다!");
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        player.sendMessage("해당 플레이어를 찾을 수 없습니다!");
                        return false;
                    }

                    if (List.of("등", "back").contains(args[1].toLowerCase())) {
                        new PlayerCosmeticData(target).setWearBackCosmetic(Cosmetic.BACK.NONE);
                        player.sendMessage("성공적으로 " + target.getDisplayName() + "님의 등 치장을 제거했습니다.");
                        return true;
                    } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                        new PlayerCosmeticData(target).setWearHatCosmetic(Cosmetic.HAT.NONE);
                        player.sendMessage("성공적으로 " + target.getDisplayName() + "님의 모자 치장을 제거했습니다.");
                        return true;
                    } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                        new PlayerCosmeticData(target).setWearOffhandCosmetic(Cosmetic.OFFHAND.NONE);
                        player.sendMessage("성공적으로 " + target.getDisplayName() + "님의 왼손 치장을 제거했습니다.");
                        return true;
                    } else {
                        player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                        return false;
                    }
                }

                player.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 2) {
                player.sendMessage("입력값이 적습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                new PlayerCosmeticData(player).setWearBackCosmetic(Cosmetic.BACK.NONE);
                player.sendMessage("성공적으로 등 치장을 제거했습니다.");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                new PlayerCosmeticData(player).setWearHatCosmetic(Cosmetic.HAT.NONE);
                player.sendMessage("성공적으로 모자 치장을 제거했습니다.");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                new PlayerCosmeticData(player).setWearOffhandCosmetic(Cosmetic.OFFHAND.NONE);
                player.sendMessage("성공적으로 왼손 치장을 제거했습니다.");
                return true;
            } else {
                player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                return false;
            }
        } else if (List.of("주기", "give").contains(args[0])) {
            if (args.length > 4) {
                player.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 4) {
                player.sendMessage("입력값이 적습니다!");
                return false;
            }

            if (!player.isOp()) {
                player.sendMessage("해당 명령어는 OP만 사용가능합니다!");
                return false;
            }

            Player target = Bukkit.getPlayer(args[3]);
            if (target == null) {
                player.sendMessage("해당 플레이어를 찾을 수 없습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                Cosmetic.BACK cosmetic;

                try {
                    cosmetic = Cosmetic.BACK.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (new PlayerCosmeticData(player).getBackCosmetics().contains(cosmetic)) {
                    player.sendMessage("해당 플레이어는 이미 이 치장을 가지고 있습니다.");
                    return false;
                }

                new PlayerCosmeticData(player).addCosmetic(cosmetic);

                player.sendMessage("성공적으로 등 치장을 지급했습니다.");
                target.sendMessage(player.getDisplayName() + "님이 등 치장(" + cosmetic.name() + ")을 지급했습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                Cosmetic.HAT cosmetic;

                try {
                    cosmetic = Cosmetic.HAT.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (new PlayerCosmeticData(player).getHatCosmetics().contains(cosmetic)) {
                    player.sendMessage("해당 플레이어는 이미 이 치장을 가지고 있습니다.");
                    return false;
                }

                new PlayerCosmeticData(player).addCosmetic(cosmetic);

                player.sendMessage("성공적으로 모자 치장을 지급했습니다.");
                target.sendMessage(player.getDisplayName() + "님이 모자 치장(" + cosmetic.name() + ")을 지급했습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                Cosmetic.OFFHAND cosmetic;

                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (new PlayerCosmeticData(player).getOffhandCosmetics().contains(cosmetic)) {
                    player.sendMessage("해당 플레이어는 이미 이 치장을 가지고 있습니다.");
                    return false;
                }

                new PlayerCosmeticData(player).addCosmetic(cosmetic);

                player.sendMessage("성공적으로 왼손 치장을 지급했습니다.");
                target.sendMessage(player.getDisplayName() + "님이 왼손 치장(" + cosmetic.name() + ")을 지급했습니다!");
                return true;
            } else {
                player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                return false;
            }
        } else if (List.of("뺏기", "take").contains(args[0])) {
            if (args.length > 4) {
                player.sendMessage("입력값이 많습니다!");
                return false;
            } else if (args.length < 4) {
                player.sendMessage("입력값이 적습니다!");
                return false;
            }

            if (!player.isOp()) {
                player.sendMessage("해당 명령어는 OP만 사용가능합니다!");
                return false;
            }

            Player target = Bukkit.getPlayer(args[3]);
            if (target == null) {
                player.sendMessage("해당 플레이어를 찾을 수 없습니다!");
                return false;
            }

            if (List.of("등", "back").contains(args[1].toLowerCase())) {
                Cosmetic.BACK cosmetic;

                try {
                    cosmetic = Cosmetic.BACK.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (!new PlayerCosmeticData(player).getBackCosmetics().contains(cosmetic)) {
                    player.sendMessage("해당 플레이어는 이미 이 치장을 가지고 있지 않습니다.");
                    return false;
                }

                new PlayerCosmeticData(player).removeCosmetic(cosmetic);

                player.sendMessage("성공적으로 등 치장을 빼앗았습니다.");
                target.sendMessage(player.getDisplayName() + "님이 등 치장(" + cosmetic.name() + ")을 빼앗았습니다!");
                return true;
            } else if (List.of("모자", "hat").contains(args[1].toLowerCase())) {
                Cosmetic.HAT cosmetic;

                try {
                    cosmetic = Cosmetic.HAT.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (!new PlayerCosmeticData(player).getHatCosmetics().contains(cosmetic)) {
                    player.sendMessage("해당 플레이어는 이미 이 치장을 가지고 있지 않습니다.");
                    return false;
                }

                new PlayerCosmeticData(player).removeCosmetic(cosmetic);

                player.sendMessage("성공적으로 등 치장을 빼앗았습니다.");
                target.sendMessage(player.getDisplayName() + "님이 등 치장(" + cosmetic.name() + ")을 빼앗았습니다!");
                return true;
            } else if (List.of("왼손", "offhand").contains(args[1].toLowerCase())) {
                Cosmetic.OFFHAND cosmetic;

                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(args[2]);
                } catch (Exception ignored) {
                    player.sendMessage("입력된 치장은 존재하지 않습니다.");
                    return false;
                }

                if (!new PlayerCosmeticData(player).getOffhandCosmetics().contains(cosmetic)) {
                    player.sendMessage("해당 플레이어는 이미 이 치장을 가지고 있지 않습니다.");
                    return false;
                }

                new PlayerCosmeticData(player).removeCosmetic(cosmetic);

                player.sendMessage("성공적으로 등 치장을 빼앗았습니다.");
                target.sendMessage(player.getDisplayName() + "님이 등 치장(" + cosmetic.name() + ")을 빼앗았습니다!");
                return true;
            } else {
                player.sendMessage("입력된 치장 종류는 존재하지 않습니다.");
                return false;
            }
        }

        player.sendMessage("/코스튬 [도움말|목록|장착|제거]");
        return false;
    }
}
