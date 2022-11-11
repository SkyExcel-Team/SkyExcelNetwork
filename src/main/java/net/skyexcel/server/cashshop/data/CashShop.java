package net.skyexcel.server.cashshop.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.util.Translate;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import skyexcel.data.file.GUI;
import skyexcel.data.file.util.Stockable;

import java.util.List;

public class CashShop extends Stockable {


    private Type type = Type.DEFAULT;

    private Inventory editGUI;

    private int slot;

    private long amount;

    private ItemStack itemStack;

    private String name;

    public CashShop(String name) {
        super("shop/cash", name, SkyExcelNetworkCashShopMain.plugin);
        this.name = name;
    }

    public void editGUI(Player player) {
        editGUI = Bukkit.createInventory(null, 27, "구매가격/판매가격");
        Items.newItem("", Material.LIME_WOOL, 1, List.of("", ChatColor.translateAlternateColorCodes('&', "&f[&b!&f] 클릭시 구매가격을 설정합니다!"), ""), 13, editGUI
        );
        player.openInventory(editGUI);
    }

    @Override
    public void open(Player player) {
        super.open(player);
    }

    @Override
    public void load(Player player) {
        super.load(player);
        for (ItemStack item : getInv().getContents()) {
            if (item != null) {
                ItemMeta meta = item.getItemMeta();

                assert meta != null;
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                if (!pdc.getKeys().isEmpty()) {

                    NamespacedKey sell_Key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "sell");
                    NamespacedKey buy_Key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "buy");

                    long sell = (pdc.get(sell_Key, PersistentDataType.LONG) != null ? pdc.get(sell_Key, PersistentDataType.LONG) : -1);
                    long buy = (pdc.get(buy_Key, PersistentDataType.LONG) != null ? pdc.get(buy_Key, PersistentDataType.LONG) : -1);


                    List<String> lore = List.of();

                    // 구매와 판매 둘다 못할 경우
                    if (sell == -1 && buy == -1) {
                        lore = Translate.msgCollapse(List.of(""
                                , "&f[&b!&f] 구매를 못하는 상품입니다."
                                , "&f[&b!&f] 판매를 못하는 상품입니다."
                                , ""));
                    } else if (sell != -1 && buy != -1) { //구매와 판매 둘다 가능할 경우
                        lore = Translate.msgCollapse(List.of(""
                                , "&f[&b!&f] 구매 가격:  " + buy + " 캐시"
                                , "&f[&b!&f] 판매 가격:  " + sell + " 캐시"

                                , ""
                                , "&f[&b!&f] 쉬프트 + 좌클릭: 64개 구매"
                                , "&f[&b!&f] 쉬프트 + 우클릭: 64개 판매"

                                , ""));
                    } else if (sell != -1) { //판매가 가능할 경우
                        lore = Translate.msgCollapse(List.of(""
                                , "&f[&b!&f] 판매 가격:  " + sell + " 캐시"

                                , ""
                                , "&f[&b!&f] 구매가 불가능한 상품 입니다."
                                , "&f[&b!&f] 쉬프트 + 우클릭: 64개 판매"

                                , ""));
                    } else { //구매가 가능할 경우
                        lore = Translate.msgCollapse(List.of(""
                                , "&f[&b!&f] 구매 가격:  " + buy + " 캐시"

                                , ""
                                , "&f[&b!&f] 쉬프트 + 좌클릭: 64개 구매"
                                , "&f[&b!&f] 판매가 불가능한 상품입니다."

                                , ""));
                    }

                    meta.setLore(lore);
                }
                item.setItemMeta(meta);
            }
        }
    }

    public void purchase(Player player, int slot) {
        Inventory inv = getInv();
        ItemStack select = inv.getItem(slot);
        Cash cash = new Cash(player);

        if (select != null) {

            ItemMeta meta = select.getItemMeta();
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            NamespacedKey buy_Key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "buy");
            long amount = (pdc.get(buy_Key, PersistentDataType.LONG) != null ? pdc.get(buy_Key, PersistentDataType.LONG) : -1);

            if (amount != -1) {

                if (cash.withdraw(amount)) {
                    if (!isInventoryFull(player)) {
                        player.getInventory().addItem(select);

                    } else {
                        player.sendMessage("인벤토리가 꽉 찼습니다!");
                        cash.deposit(amount);
                    }

                } else {
                    player.sendMessage("캐시가 부족하여 구매를 하지 못합니다!");
                }

            } else {
                player.sendMessage("해당 아이템은 구매를 하지 못합니다.");
            }
        }


    }

    public boolean isInventoryFull(Player p) {
        int slot = p.getInventory().firstEmpty();

        return slot == -1;
    }

    public boolean removeOne(Inventory inventory, ItemStack item, int amount) {
        int size = inventory.getSize();
        for (int i = 0; i < size; i++) {
            ItemStack other = inventory.getItem(i);
            if (item.isSimilar(other)) {
                if (amount == 64) {
                    if (other.getAmount() == 64) {
                        other.setAmount(other.getAmount() - amount);
                        return true;
                    } else {
                        return false;
                    }

                } else if (amount == 1) {
                    other.setAmount(other.getAmount() - amount);
                    return true;
                }
                inventory.setItem(i, other);
                break;
            }
        }
        return false;
    }

    public boolean inventoryIsFull(Player p) {
        Inventory pInv = p.getInventory();
        boolean pInvIsNotFull = false;
        for (ItemStack item : pInv.getContents()) {
            if (item == null) {
                pInvIsNotFull = true;
                break;
            }
        }
        return !pInvIsNotFull;
    }


    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setAmount(long amount) {
        GUI gui = getGui();
        switch (getType()) {
            case BUY -> {
                gui.setLong(slot + ".data.buy", amount);
            }
            case SELL -> {
                gui.setLong(slot + ".data.sell", amount);
            }
        }
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public Inventory getEditGUI() {
        return editGUI;
    }

    public Type getType() {
        return type;
    }

    public enum Type {

        SET, SELL, BUY, CLOSE, DEFAULT, OPEN, EDIT
    }


}
