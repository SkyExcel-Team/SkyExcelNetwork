package net.skyexcel.server.cmd;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;
import skyexcel.command.tab.Tab;
import skyexcel.data.file.Config;

public class MenuCommand {

    private final String label = "menu";
    public MenuCommand(){
        Tab<Object, String> tab = new Tab<>(SkyExcelNetwork.plugin, label);

        tab.args("메뉴", "열기" , "[이름]");
        tab.args(false, (Object) "메뉴", "리로드");
        tab.args(false, (Object) "메뉴", "생성", "[이름]");


        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, label);

        cmd.action("메뉴", 0, action -> {
            Player player = (Player) action.getSender();
            String[] args = action.getArgs();
            String name;
            Config config;

            switch (args[1]){
                case "생성":
                    if(args.length <= 1){
                        name = args[2];
                        config = new Config(name);
                        config.setPlugin(SkyExcelNetwork.plugin);

                        player.sendMessage(name + " 이름의 메뉴가 생성 되었습니다!");
                    }

                    break;
                case "리로드":

                    break;
            }
        });
    }
}
