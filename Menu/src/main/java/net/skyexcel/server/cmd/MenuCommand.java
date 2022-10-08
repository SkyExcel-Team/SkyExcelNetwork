package net.skyexcel.server.cmd;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;
import skyexcel.command.tab.Tab;
import skyexcel.data.file.Config;

public class MenuCommand {

    private final String label = "메뉴";

    public MenuCommand() {
        Tab<Object, String> tab = new Tab<>(SkyExcelNetwork.plugin, label);

        tab.args("리로드", "[이름]");

        Config config = new Config("menu/");
        config.setPlugin(SkyExcelNetwork.plugin);

        config.fileListName().forEach(name ->{
            tab.args("열기", name);
        });

        tab.args("생성", "[이름]", "1234");

        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, label);

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
    }
}
