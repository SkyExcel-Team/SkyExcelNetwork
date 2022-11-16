package net.skyexcel.server.job.listener;


import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.stat.AntiFragile;
import net.skyexcel.server.job.data.stat.Bait;
import net.skyexcel.server.job.data.stat.BlastFurnace;
import net.skyexcel.server.job.data.stat.FeverTime;
import net.skyexcel.server.job.data.type.Farmer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class JobListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Farmer job = new Farmer(player);
        job.setJobType(JobType.FARM);


        if (job.hasJob()) {
            if (player.isSneaking() && player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_HOE)) {
                job.run(player);
            }

        } else {
            player.sendMessage("직업이 없습니다!");
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        Job job = new Job(player);
        if (job.hasJob()) {
            job.setJobType(JobType.MINEWORKER);
            if (job.getType().equals(JobType.MINEWORKER)) { //광부일때 광부 패시브를 발동한다.

                FeverTime feverTime = new FeverTime();
                feverTime.run(player, block);

                //TODO 모든 광물 다 불러와야됨 ㅅㄱ
                BlastFurnace blastFurnace = new BlastFurnace();
                blastFurnace.run(player, block);

                //TODO 이벤트 캔슬 시켜야됨
                AntiFragile antiFragile = new AntiFragile();
                antiFragile.run(player);

                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.getDurability() == 0)
                    item.setDurability((short) 1);

                item.setDurability((short) (item.getDurability() - 1));


                event.setCancelled(true);

            } else if (job.getType().equals(JobType.FARM)) { // 농부일때 농부의 축복 페시브를 발동한다.

            }
        }
    }

    @EventHandler
    public void onGrow(BlockGrowEvent event) {

    }


    @EventHandler
    public void Change(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        ItemStack previous = player.getInventory().getItem(event.getPreviousSlot());
        Job job = new Job(player);
        if (job.hasJob()) {
            job.setJobType(JobType.FISHERMAN);
            if (job.getType().equals(JobType.FISHERMAN)) {
                Bait bait = new Bait();
                bait.run(player, item, previous);
            } else {
                player.sendMessage("응 너 어부아ㅏㅇ님");
            }
        }
    }

}
