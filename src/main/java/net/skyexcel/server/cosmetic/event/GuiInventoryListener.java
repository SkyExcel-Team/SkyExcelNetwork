package net.skyexcel.server.cosmetic.event;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.CosmeticType;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GuiInventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (!SkyExcelNetworkCosmeticMain.guiUtil.pageMap.containsKey(player) || !SkyExcelNetworkCosmeticMain.guiUtil.typeMap.containsKey(player))
            return;

        e.setCancelled(true);
        e.setCursor(null);

        ItemStack item = e.getCurrentItem();
        if (item == null) return;

        if (item.getItemMeta().getDisplayName().equals("§c이전 페이지")) {
            SkyExcelNetworkCosmeticMain.guiUtil.openGui(player, SkyExcelNetworkCosmeticMain.guiUtil.typeMap.get(player), SkyExcelNetworkCosmeticMain.guiUtil.pageMap.get(player) - 1);

            return;
        } else if (item.getItemMeta().getDisplayName().equals("§a다음 페이지")) {
            SkyExcelNetworkCosmeticMain.guiUtil.openGui(player, SkyExcelNetworkCosmeticMain.guiUtil.typeMap.get(player), SkyExcelNetworkCosmeticMain.guiUtil.pageMap.get(player) + 1);

            return;
        }

        if (e.isRightClick()) {
            List<String> lore = item.getItemMeta().getLore();
            if (lore == null) return;

            String key = null;
            for (String line : lore) {
                if (line.startsWith("§6이름 : §7")) {
                    key = line.substring("§6이름 : §7".length());
                    break;
                }
            }

            if (key == null) return;

            CosmeticType type = SkyExcelNetworkCosmeticMain.guiUtil.typeMap.get(player);

            if (type == CosmeticType.BACK) {
                Cosmetic.BACK cosmetic;
                try {
                    cosmetic = Cosmetic.BACK.valueOf(key);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");

                    player.closeInventory();
                    return;
                }

                if (!new PlayerCosmeticData(player).getBackCosmetics().contains(cosmetic)) {

                    if (!player.isOp()) {
                        player.sendMessage("强 보유하지 않은 §6코스튬§f입니다.");

                        player.closeInventory();
                        return;
                    }
                    player.sendMessage("架 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                }

                new PlayerCosmeticData(player).setWearBackCosmetic(cosmetic);
                player.sendMessage("架 성공적으로 §6등 코스튬§f을 §a적용§f했습니다!");

                player.closeInventory();
                return;
            } else if (type == CosmeticType.HAT) {
                Cosmetic.HAT cosmetic;
                try {
                    cosmetic = Cosmetic.HAT.valueOf(key);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");

                    player.closeInventory();
                    return;
                }


                if (!new PlayerCosmeticData(player).getHatCosmetics().contains(cosmetic)) {
                    if (!player.isOp()) {
                        player.sendMessage("强 보유하지 않은 §6코스튬§f입니다.");

                        player.closeInventory();
                        return;
                    }
                    player.sendMessage("架 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                }

                new PlayerCosmeticData(player).setWearHatCosmetic(cosmetic);
                player.sendMessage("架 성공적으로 §6모자 코스튬§f을 §a적용§f했습니다!");

                player.closeInventory();
                return;
            } else if (type == CosmeticType.OFFHAND) {
                Cosmetic.OFFHAND cosmetic;
                try {
                    cosmetic = Cosmetic.OFFHAND.valueOf(key);
                } catch (Exception ignored) {
                    player.sendMessage("强 존재하지 않는 §6코스튬§f입니다.");

                    player.closeInventory();
                    return;
                }

                PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
                if (!playerCosmeticData.getOffhandCosmetics().contains(cosmetic)) {
                    if (!player.isOp()) {
                        player.sendMessage("强 보유하지 않은 §6코스튬§f입니다.");

                        player.closeInventory();
                        return;
                    }
                    player.sendMessage("架 §6코스튬§f을 §c강제 §a적용§f하였습니다!");
                }

                playerCosmeticData.setWearOffhandCosmetic(cosmetic);
                player.sendMessage("架 성공적으로 §6왼손 코스튬§f을 §a적용§f했습니다!");

                player.closeInventory();
                return;
            }

            player.sendMessage("强 §6알 수 없는 §c오류§f가 발생했습니다.");
            player.closeInventory();
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (SkyExcelNetworkCosmeticMain.guiUtil.pageMap.containsKey(player))
            SkyExcelNetworkCosmeticMain.guiUtil.pageMap.remove(player);
        if (SkyExcelNetworkCosmeticMain.guiUtil.typeMap.containsKey(player))
            SkyExcelNetworkCosmeticMain.guiUtil.typeMap.remove(player);
    }
}
