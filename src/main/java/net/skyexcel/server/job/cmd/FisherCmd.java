package net.skyexcel.server.job.cmd;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.fisher.WaterBucket;
import net.skyexcel.server.job.data.type.Fisher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FisherCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            Job job = new Job(player);

            job.setJobType(JobType.FISHERMAN);
            if (job.getType().equals(JobType.FISHERMAN)) {

                WaterBucket waterBucket = new WaterBucket(player);
                if (waterBucket.getLevel() != 0) {
                    waterBucket.onGUI(player);
                    JobData.waterBucket.put(player.getUniqueId(), waterBucket);
                } else {
                    player.sendMessage("물통 레벨이 0이다 애송아");
                }

            } else {
                Fisher fisher = new Fisher();
                player.sendMessage(fisher.getDisplayName() + " 직업만 사용 가능한 기능입니다.");
            }
        }

        return false;
    }
}
