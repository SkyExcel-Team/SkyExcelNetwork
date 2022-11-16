package net.skyexcel.server.skyblock.cmd;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.Visitor;
import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVault;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IslandAdminCmd implements CommandExecutor {

    public IslandAdminCmd() {
        Bukkit.getServer().getPluginCommand("섬어드민").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if(player.hasPermission("island.admin*")){
                if (args.length > 0) {
                    String name = args[0]; // 섬 이름
                    SkyBlock skyBlock = new SkyBlock(name);

                    switch (args[1]) {
                        case "금고" -> {
                            if (args.length > 2) {
                                if ((args[2].equalsIgnoreCase("입금"))) {
                                    if (args.length > 3) {
                                        try {
                                            long amount = Long.parseLong(args[3]);
                                            SkyBlockVault vault = new SkyBlockVault(player, name);

                                            if (vault.deposit(amount)) {
                                                player.sendMessage("架 §6" + name + "§f님의 섬에 §e" + amount + "§f원을 §a입금§f하였습니다!");
                                            }

                                        } catch (NumberFormatException e) {
                                            player.sendMessage("强 정수를 입력 해 주세요!");
                                        }
                                    } else {
                                        player.sendMessage("强 입금할 금액을 입력 해 주세요!");
                                    }
                                } else if ((args[2].equalsIgnoreCase("출금"))) {

                                    try {
                                        long amount = Long.parseLong(args[3]);
                                        SkyBlockVault vault = new SkyBlockVault(player, name);

                                        vault.withdraw(amount);
                                    } catch (NumberFormatException e) {
                                        player.sendMessage("强 정수를 입력 해 주세요!");
                                    }

                                } else if ((args[2].equalsIgnoreCase("설정"))) {
                                    if (args.length > 3) {
                                        try {
                                            long amount = Long.parseLong(args[3]);
                                            SkyBlockVault vault = new SkyBlockVault(player, name);

                                            if (vault.setAmount(amount)) {
                                                player.sendMessage("架 §6" + name + "§f의 금고 돈을 §e" + amount + "§f로 설정 하였습니다.");
                                            }

                                        } catch (NumberFormatException e) {
                                            player.sendMessage("强 정수를 입력 해 주세요!");
                                        }
                                    } else {
                                        player.sendMessage("强 설정할 금액을 입력 해 주세요!");
                                    }
                                }
                            }
                        } // 금고 명령어를 쳤을 때

                        case "옵션" -> {

                        } //설정 명령어를 쳤을 때

                        case "디스코드" -> {
                            if (args.length > 3) {

                            } else {
                                TextComponent url = new TextComponent("§a클릭");

                                url.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a섬 디스코드 이동!").create()));
                                url.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, skyBlock.getDiscord()));

                                TextComponent message = new TextComponent(name + " 섬 디스코드 링크 => ");
                                message.addExtra(url);
                                player.spigot().sendMessage(message);
                            }
                        }
                        case "방문객" -> {
                            Visitor visitor = new Visitor(name);
                            visitor.openGUI(player);
                        }
                    }
                }
            }
        }
        return false;
    }
}
