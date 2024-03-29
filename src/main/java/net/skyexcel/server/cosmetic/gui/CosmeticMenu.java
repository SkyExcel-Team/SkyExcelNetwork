package net.skyexcel.server.cosmetic.gui;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.CosmeticType;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CosmeticMenu {
    public static final Map<Player, Integer> pageMap = new HashMap<>();
    public static final Map<Player, CosmeticType> typeMap = new HashMap<>();

    public static class Menu {
        private Inventory inv;
        private Player player;
        private CosmeticType type;
        private int page;

        public Menu(Player player, CosmeticType type, Integer page) {
            if (SkyExcelNetworkMain.isLoaded(player)) {
                this.inv = Bukkit.createInventory(player, 54, type.name());
                this.player = player;
                this.type = type;
                this.page = page;

                ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta glass_meta = glass.getItemMeta();
                glass_meta.setDisplayName(" ");
                glass.setItemMeta(glass_meta);
                List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53).forEach(slot -> inv.setItem(slot, glass));

                ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta head_meta = (SkullMeta) head.getItemMeta();
                head_meta.setOwnerProfile(player.getPlayerProfile());
                head_meta.setCustomModelData(1);
                head_meta.setDisplayName("§r§6" + player.getDisplayName() + "§r님의 §b치장§r목록 [" + (type == CosmeticType.BACK ? "등" : (type == CosmeticType.HAT ? "모자" : "왼손")) + "]");
                head.setItemMeta(head_meta);
                inv.setItem(4, head);

                if (page == 1) {
                    ItemStack previous = SkyExcelNetworkMain.hdb.getItemHead("32811");
                    ItemMeta meta = previous.getItemMeta();
                    meta.setCustomModelData(1);
                    meta.setDisplayName("§r§7이전 페이지");
                    previous.setItemMeta(meta);

                    inv.setItem(47, previous);
                } else {
                    ItemStack previousActive = SkyExcelNetworkMain.hdb.getItemHead("32812");
                    ItemMeta meta = previousActive.getItemMeta();
                    meta.setCustomModelData(1);
                    meta.setDisplayName("§r§c이전 페이지");
                    previousActive.setItemMeta(meta);
                    //                                                                                       ㄴ 이름 변경시, GuiInventoryListener.java의 24번째 줄도 변경해야 합니다.

                    inv.setItem(47, previousActive);
                }

                Object[] cosmeticValues = type.equals(CosmeticType.BACK) ? Cosmetic.BACK.values() : (type.equals(CosmeticType.HAT) ? Cosmetic.HAT.values() : Cosmetic.OFFHAND.values());
                if (cosmeticValues.length > page * 28) {
                    ItemStack nextActive = SkyExcelNetworkMain.hdb.getItemHead("32814");
                    ItemMeta meta = nextActive.getItemMeta();
                    meta.setCustomModelData(1);
                    meta.setDisplayName("§r§a다음 페이지");
                    nextActive.setItemMeta(meta);
                    //                                                                                       ㄴ 이름 변경시, GuiInventoryListener.java의 28번째 줄도 변경해야 합니다.

                    inv.setItem(51, nextActive);
                } else {
                    ItemStack next = SkyExcelNetworkMain.hdb.getItemHead("32813");
                    ItemMeta meta = next.getItemMeta();
                    meta.setCustomModelData(1);
                    meta.setDisplayName("§r§7다음 페이지");
                    next.setItemMeta(meta);

                    inv.setItem(51, next);
                }

                if (type == CosmeticType.BACK) {
                    PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);

                    int i = 0;
                    for (Cosmetic.BACK cosmetic : Cosmetic.BACK.values()) {
                        if (cosmetic == Cosmetic.BACK.NONE) continue;

                        i++;
                        if (page > 1 && i <= (page - 1) * 28) continue;
                        if (i > page * 28) break;

                        ItemStack item = new ItemStack(cosmetic.getType(), 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setCustomModelData(cosmetic.getCustomModelData());
                        meta.setDisplayName("§r" + cosmetic.getName() + (playerCosmeticData.getWearBackCosmetic() == cosmetic ? " §a(착용중)" : ""));

                        List<String> lore = new ArrayList<>();
                        lore.add("§r§7==[ 치장 아이템 정보 ]==");
                        lore.add("§r§6분류 : §7등");
                        lore.add("§r§6이름 : §7" + cosmetic.name()); // 변경 시, GuiInventoryListener.java의 46 ~ 47번째 줄도 변경해야 합니다.
                        lore.add("");
                        lore.add(playerCosmeticData.getBackCosmetics().contains(cosmetic) ? "§r§aUNLOCKED (우클릭 장착)" : "§r§cLOCKED");

                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        inv.addItem(item);
                    }
                } else if (type == CosmeticType.HAT) {
                    PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);

                    int i = 0;
                    for (Cosmetic.HAT cosmetic : Cosmetic.HAT.values()) {
                        if (cosmetic == Cosmetic.HAT.NONE) continue;

                        i++;
                        if (page != 1 && i <= (page - 1) * 28) continue;
                        if (i > page * 28) break;

                        ItemStack item = new ItemStack(cosmetic.getType(), 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setCustomModelData(cosmetic.getCustomModelData());
                        meta.setDisplayName("§r" + cosmetic.getName() + (playerCosmeticData.getWearHatCosmetic() == cosmetic ? " §a(착용중)" : ""));

                        List<String> lore = new ArrayList<>();
                        lore.add("§r§b==[ 치장 아이템 정보 ]==");
                        lore.add("§r§6분류 : §7모자");
                        lore.add("§r§6이름 : §7" + cosmetic.name()); // 변경 시, GuiInventoryListener.java의 46 ~ 47번째 줄도 변경해야 합니다.
                        lore.add("");
                        lore.add(playerCosmeticData.getHatCosmetics().contains(cosmetic) ? "§r§aUNLOCKED (우클릭 장착)" : "§r§cLOCKED");

                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        inv.addItem(item);
                    }
                } else if (type == CosmeticType.OFFHAND) {
                    PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);

                    int i = 0;
                    for (Cosmetic.OFFHAND cosmetic : Cosmetic.OFFHAND.values()) {
                        if (cosmetic == Cosmetic.OFFHAND.NONE) continue;

                        i++;
                        if (page != 1 && i <= (page - 1) * 28) continue;
                        if (i > page * 28) break;

                        ItemStack item = new ItemStack(cosmetic.getType(), 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setCustomModelData(cosmetic.getCustomModelData());
                        meta.setDisplayName("§r" + cosmetic.getName() + (playerCosmeticData.getWearOffhandCosmetic() == cosmetic ? " §a(착용중)" : ""));

                        List<String> lore = new ArrayList<>();
                        lore.add("§r§b==[ 치장 아이템 정보 ]==");
                        lore.add("§r§6분류 : §7왼손");
                        lore.add("§r§6이름 : §7" + cosmetic.name()); // 변경 시, GuiInventoryListener.java의 46 ~ 47번째 줄도 변경해야 합니다.
                        lore.add("");
                        lore.add(playerCosmeticData.getOffhandCosmetics().contains(cosmetic) ? "§r§aUNLOCKED (우클릭 장착)" : "§r§cLOCKED");

                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        inv.addItem(item);
                    }
                }
            }
        }

        public void openInventory() {
            player.openInventory(inv);

            pageMap.put(player, page);
            typeMap.put(player, type);
        }
    }
}
