package net.skyexcel.server.menu.cmd;

import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.menu.gui.PlayerProfile;
import net.skyexcel.server.menu.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class MenuCommand {

    public MenuCommand() {
        String label = "메뉴";
        Cmd cmd = new Cmd(SkyExcelNetworkMenuMain.plugin, label);
        cmd.label(action -> {

        });

        cmd.action("리로드", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {

                String[] args = action.getArgs();
                if (args.length > 1) {
                    String name = args[1];

                    Menu menu = new Menu(name);
                    menu.reload();

                    player.sendMessage(ChatColor.GREEN + "리로드가 완료 되었습니다!");
                }
            }
        });

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                String[] args = action.getArgs();
                if (args.length > 1) {
                    String name = args[1];

                    Menu menu = new Menu(name);
                    menu.create();

                    player.sendMessage(name + " 이름의 메뉴가 생성 되었습니다!");
                }
            }
        });
        cmd.action("열기", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();
            if (args.length > 1) {
                String name = args[1];

                Menu menu = new Menu(name);
                menu.load(player);
            }
        });
        cmd.action("test", 0, action -> {
            Player player = (Player) action.getSender();
            PlayerProfile gui = new PlayerProfile(player);
            gui.open();

        });
    }
}
