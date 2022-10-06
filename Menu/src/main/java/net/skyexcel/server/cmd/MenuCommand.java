package net.skyexcel.server.cmd;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.menu.Menu;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;
import skyexcel.command.tab.Tab;
import skyexcel.data.file.Config;

public class MenuCommand {

    private final String label = "메뉴";

    public MenuCommand() {
        Tab<Object, String> tab = new Tab<>(SkyExcelNetwork.plugin, label);

        tab.args("리로드");

        tab.args("열기", "[이름]");
        tab.args("생성", "[이름]");


        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, label);

        cmd.action("생성", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();

            String name;
            Menu menu;
            System.out.println(args[0]);
            switch (args[0]) {
                case "생성":
                    System.out.println("test");
                    if (args.length > 1) {
                        name = args[1];

                        menu = new Menu(name);
                        menu.create();

                        player.sendMessage(name + " 이름의 메뉴가 생성 되었습니다!");
                    } else {
                        player.sendMessage("test");
                    }

                    break;
                case "열기":
                    if (args.length <= 1) {
                        name = args[2];

                        menu = new Menu("menu/" + name);
                        menu.load();
                    }
                    break;
                case "리로드":

                    break;
            }
        });
    }
}
