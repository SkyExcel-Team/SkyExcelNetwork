package net.skyexcel.server.seconomy.data;

import net.skyexcel.api.util.Translate;
import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
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
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;
import skyexcel.data.file.util.Stockable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SEconomyShop extends Stockable {


    private Type type = Type.DEFAULT;

    private Inventory editGUI;

    private int slot;

    private long amount;

    private ItemStack itemStack;

    private String name;

    private Translate translate;

    public SEconomyShop() {
        super("", "", SkyExcelNetworkMain.getPlugin());

    }
    public SEconomyShop(String name) {
        super("shop/money", name, SkyExcelNetworkMain.getPlugin());
        this.name = name;
        translate = new Translate();
    }

    public void editGUI(Player player) {

        editGUI = Bukkit.createInventory(null, 27, "구매가격/판매가격");
        Items.newItem("가격 설정", Material.LIME_WOOL, 1, translate.msgCollapse(List.of("", "&f[&b!&f]좌클릭시 구매가격을 설정합니다 !", "&f[&b!&f]우클릭시 판매가격을 설정합니다 !")), 13, editGUI);
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

                    LoreType type;
                    List<String> lore = List.of();

                    // 구매와 판매 둘다 못할 경우
                    if (sell == -1 && buy == -1) {
                        type = LoreType.CANTBOTH;
                    } else if (sell != -1 && buy != -1) { //구매와 판매 둘다 가능할 경우
                        type = LoreType.CANBOTH;
                    } else if (sell != -1) { //판매가 가능할 경우
                        type = LoreType.CANSELL;
                    } else { //구매가 가능할 경우
                        type = LoreType.CANBUY;
                    }

                    if (!meta.hasLore()) {
                        meta.setLore(getLore(type, lore, buy, sell));
                    } else {
                        lore = meta.getLore();
                        meta.setLore(addLore(type, lore, buy, sell));
                    }
                }
                item.setItemMeta(meta);
            }
        }
    }

    private List<String> getLore(LoreType type, List<String> lore, long buy, long sell) {
        StringData stringData = new StringData();
        switch (type) {
            case CANBUY -> {
                lore = stringData.canBuy(buy);
            }
            case CANBOTH -> {
                lore = stringData.canBoth(buy, sell);
            }
            case CANSELL -> {
                lore = stringData.canSell(sell);
            }
            case CANTBOTH -> {
                lore = stringData.cantBoth();
            }
        }
        return lore;
    }

    private List<String> addLore(LoreType type, List<String> lore, long buy, long sell) {

        List<String> add = getLore(type, lore, buy, sell);

        if (!new HashSet<>(lore).containsAll(add)) {
            lore.addAll(add);
        }
        return lore;
    }

    public void removeLore(ItemStack itemStack) {

        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            NamespacedKey buy_Key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "buy");
            NamespacedKey sell_key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "sell");
            long buy = (pdc.get(buy_Key, PersistentDataType.LONG) != null ? pdc.get(buy_Key, PersistentDataType.LONG) : -1);

            long sell = (pdc.get(sell_key, PersistentDataType.LONG) != null ? pdc.get(sell_key, PersistentDataType.LONG) : -1);

            StringData stringData = new StringData();

            List<String> lore = meta.getLore();

            List<String> canBoth = stringData.canBoth(buy, sell);

            List<String> cantBoth = stringData.cantBoth();

            List<String> canSell = stringData.canSell(sell);

            List<String> canBuy = stringData.canBuy(buy);

            if (new HashSet<>(lore).containsAll(canBoth)) {
                lore.removeAll(canBoth);
            } else if (new HashSet<>(lore).containsAll(canBuy)) {
                lore.removeAll(canBuy);
            } else if (new HashSet<>(lore).containsAll(canSell)) {
                lore.removeAll(canSell);
            } else if (new HashSet<>(lore).containsAll(cantBoth)) {
                lore.removeAll(cantBoth);
            }

            pdc.remove(buy_Key);
            pdc.remove(sell_key);

            meta.setLore(lore);
            itemStack.setItemMeta(meta);
        }

    }



    public void purchase(Player player, int slot, int amount) {
        Inventory inv = getInv();
        ItemStack select = inv.getItem(slot);
        StringData stringData = new StringData();

        SEConomy cash = new SEConomy(player);

        if (select != null) {

            ItemMeta meta = select.getItemMeta();
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            NamespacedKey buy_Key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "buy");
            NamespacedKey sell_key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "sell");
            long buy = (pdc.get(buy_Key, PersistentDataType.LONG) != null ? pdc.get(buy_Key, PersistentDataType.LONG) : -1);

            long sell = (pdc.get(sell_key, PersistentDataType.LONG) != null ? pdc.get(sell_key, PersistentDataType.LONG) : -1);


            List<String> remove = stringData.canBoth(buy, sell);

            List<String> cantBoth = stringData.cantBoth();

            List<String> canSell = stringData.canSell(sell);

            List<String> canBuy = stringData.canBuy(buy);

            if (buy != -1) {

                if (cash.withdraw(buy)) {
                    if (!isInventoryFull(player)) {
                        ItemStack newItem = Items.newItem(select.getItemMeta().getDisplayName(), select.getType(), amount, select.getItemMeta().getLore());

                        ItemMeta newItemMeta = newItem.getItemMeta();
                        List<String> lore = newItemMeta.getLore();

                        if (new HashSet<>(lore).containsAll(remove)) {
                            lore.removeAll(remove);
                        } else if (new HashSet<>(lore).containsAll(canBuy)) {
                            lore.removeAll(canBuy);
                        } else if (new HashSet<>(lore).containsAll(canSell)) {
                            lore.removeAll(canSell);
                        } else if (new HashSet<>(lore).containsAll(cantBoth)) {
                            lore.removeAll(cantBoth);
                        }

                        pdc.remove(buy_Key);
                        pdc.remove(sell_key);

                        meta.setLore(lore);
                        newItem.setItemMeta(meta);
                        player.getInventory().addItem(newItem);

                        purchaseCompare(meta, player, select, amount);
                    } else {
                        player.sendMessage("인벤토리가 꽉 찼습니다!");
                        cash.deposit(buy);
                    }
                } else {
                    player.sendMessage("캐시가 부족하여 구매를 하지 못합니다!");
                }
            } else {
                player.sendMessage("해당 아이템은 구매를 하지 못합니다.");
            }
        }
    }

    private void purchaseCompare(ItemMeta meta, Player player, ItemStack itemStack, int amount) {
        if (meta.hasDisplayName()) {
            player.sendMessage(meta.getDisplayName() + ChatColor.WHITE + " 아이템을 구매 하였습니다." + ChatColor.GRAY + " x" + amount);
        } else {

            player.sendMessage(ChatColor.GRAY + meta.getLocalizedName() + "" + ChatColor.WHITE + " 아이템을 구매 하였습니다." + ChatColor.GRAY + " x" + amount);
        }
    }

    private void sellCompare(ItemMeta meta, Player player, ItemStack itemStack, int amount) {
        if (meta.hasDisplayName()) {
            player.sendMessage(meta.getDisplayName() + ChatColor.WHITE + " 아이템을 판매 하였습니다." + ChatColor.GRAY + " x" + amount);
        } else {

            player.sendMessage(ChatColor.GRAY + itemStack.getType().name() + "" + ChatColor.WHITE + " 아이템을 판매 하였습니다. " + ChatColor.GRAY + " x" + amount);

        }
    }

    //TODO 아이템이 64개 보다 적을 때, 예외처리 해야됨 ㅅㄱ
    public void sell(Player player, int slot, int amount) {
        Inventory inv = getInv();
        ItemStack select = inv.getItem(slot);

        if (select != null) {
            ItemMeta meta = select.getItemMeta();
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            NamespacedKey sell_key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "sell");
            NamespacedKey buy_key = new NamespacedKey(SkyExcelNetworkMain.getPlugin(), "buy");
            long sell = (pdc.get(sell_key, PersistentDataType.LONG) != null ? pdc.get(sell_key, PersistentDataType.LONG) : -1);

            long buy = (pdc.get(buy_key, PersistentDataType.LONG) != null ? pdc.get(buy_key, PersistentDataType.LONG) : -1);

            if (sell != -1) {
                isSimilar(player, select, buy, sell, amount);
                sellCompare(meta, player, select, amount);
            } else {
                player.sendMessage("해당 상품은 판매가 불가능합니다");
            }

        } else {
            player.sendMessage("해당 아이템은 판매가 불가능 합니다.");
        }
    }

    private boolean isInventoryFull(Player p) {
        int slot = p.getInventory().firstEmpty();

        return slot == -1;
    }


    /**
     * 플레이어의 인벤토리를 가져와서, 판매한 아이템과 비교 후 값을 반환합니다.
     *
     * @return
     */
    private void isSimilar(Player player, ItemStack compare, long buy, long sell, int amount) {
        StringData stringData = new StringData();
        List<String> CANBOTH = stringData.canBoth(buy, sell);

        List<String> CANBUY = stringData.canBuy(buy);

        ItemStack newItem = Items.newItem(compare.getItemMeta().getDisplayName(), compare.getType(), compare.getAmount(), compare.getItemMeta().getLore());

        ItemMeta meta = newItem.getItemMeta();

        List<String> lore = meta.getLore();

        if (new HashSet<>(lore).containsAll(CANBOTH)) {
            lore.removeAll(CANBOTH);
        } else if (new HashSet<>(lore).containsAll(CANBUY)) {
            lore.removeAll(CANBUY);
        }

        meta.setLore(lore);
        newItem.setItemMeta(meta);
        Inventory inv = player.getInventory();

        removeItems(inv, newItem.getType(), amount);

    }

    private void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
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

    public void list(Player player, int index) {
        Config list = new Config("shop/money/");
        list.setPlugin(SkyExcelNetworkMain.getPlugin());
        List<String> result = message(player, list.fileListName(), index);


        for (String text : result) {
            player.sendMessage(text);
        }
    }

    private List<String> message(Player player, List<String> help, int index) {
        List<String> result = new ArrayList<>();


        try {
            player.sendMessage("§8■ §7══════°• §8[ §6캐시상점 §f목록 §8] §7•°══════ §8■");
            player.sendMessage("");

            for (int i = 10 * (index - 1); i < 10 * (index); i++) {
                String line = help.get(i);
                result.add(line);

                if (help.get(i) == null) {
                    player.sendMessage("强 해당 페이지는 존재하지 않습니다.");
                    break;
                }

            }
        } catch (IndexOutOfBoundsException e) {

        }
        return result;
    }

    public enum LoreType {
        CANBUY, CANSELL, CANTBOTH, CANBOTH
    }

    public enum Type {

        SET, SELL, BUY, CLOSE, DEFAULT, OPEN, EDIT
    }
}
