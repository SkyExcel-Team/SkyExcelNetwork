package net.skyexcel.server.fish.cmd;

import net.skyexcel.server.fish.data.FishType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testTab implements TabCompleter {


    @Nullable    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            List<FishType> fishTypes = new ArrayList<>(Arrays.stream(FishType.values()).filter(FishType::hasRank).toList());
            for(FishType type : fishTypes){
                result.add(type.name());
            }
        }
        return result;
    }
}
