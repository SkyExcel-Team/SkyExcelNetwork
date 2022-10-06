import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class OnPlayerInteractEvent implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.HAND) {
            if (event.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (!player.isSneaking()) {
                    player.sendTitle("", "§f상대의 프로필 확인 §e→ §c§l[쉬프트] §f+ §a§l[우클릭] ", 1, 20, 2);
                } else {
                    player.sendMessage("쉬프트 + 우클릭을 하셨습니다!");
                }
            }
        }
    }
}
