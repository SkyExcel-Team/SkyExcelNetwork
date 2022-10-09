package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StringData {

    public static String myCash(Player player) {
        Cash cash = new Cash(player);

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("my_cash")
                .replaceAll("%cash%", String.valueOf(cash.getAmount())));
    }

    public static String checkPlayerCash(Player target) {
        Cash cash = new Cash(target);
        return ChatColor.translateAlternateColorCodes('&', getCashMessage("check_player_cash")
                .replaceAll("%cash%", String.valueOf(cash.getAmount()))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public static String sendCash(Player target, int amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("send_cash")
                .replaceAll("%cash%", String.valueOf(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public static String sendCashAllPlayer(int amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("send_cash_all_player")
                .replaceAll("%cash%", String.valueOf(amount)));
    }

    public static String removeCash(Player target, int amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("remove_cash")
                .replaceAll("%cash%", String.valueOf(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public static String setCash(Player target, int amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("set_cash")
                .replaceAll("%cash%", String.valueOf(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public static String resetCash(Player target) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("reset_cash")
                .replaceAll("%player%", target.getDisplayName()));
    }

    public static String buyOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("buy_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", String.valueOf(price)));
    }

    public static String sellOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("sell_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", String.valueOf(price)));
    }

    public static String settingBuyPrice() {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("setting_buy_price"));
    }

    public static String settingSellPrice() {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("setting_sell_price"));
    }

    public static String createCashShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("create_cashshop")
                .replaceAll("%cashshop_name%", name));
    }

    public static String recyclebinCashShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("recyclebin_cashshop")
                .replaceAll("%cashshop_name%", name));
    }

    public static String deleteCashShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("delete_cashshop")
                .replaceAll("%cashshop_name%", name));
    }

    public static String setGuiSizeCashShop(String name, int size) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("set_gui_size")
                .replaceAll("%cashshop_name%", name)
                .replaceAll("%cashshop_gui_size%", String.valueOf(size)));
    }


    public static String setNameCashShop(String original, String newName) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("set_gui_name")
                .replaceAll("%cashshop_name%", original)
                .replaceAll("%set_cashshop_name%", newName));
    }

    public static String setBuyPriceCashShop(int price) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("set_buy_price")
                .replaceAll("%price%", String.valueOf(price)));
    }

    public static String overFlow() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("overflow"));
    }


    public static String canNotBuy() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("cannot_buy"));
    }

    public static String canNotSell() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("cannot_sell"));
    }

    public static String inventoryFull() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("inventoryFull"));
    }

    public static String nonePlayer() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("command_none_player"));
    }

    public static String impossibleItemBuy() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("impossibleItem_buy"));
    }

    public static String impossibleItemSell() {
        return ChatColor.translateAlternateColorCodes('&',getErrorMessage("impossibleItem_sell"));
    }


    public static String noneCash() {
        return ChatColor.translateAlternateColorCodes('&',getErrorMessage("command_none_cash"));
    }

    private static String getCashMessage(String path) {

        return SkyExcelNetwork.message.getString("cash_message." + path);
    }

    private static String getCashShopMessage(String path) {

        return SkyExcelNetwork.message.getString("cashshop_message." + path);
    }

    private static String getErrorMessage(String path) {

        return SkyExcelNetwork.message.getString("error_message." + path);
    }

    private static String getOtherMessage(String path) {

        return SkyExcelNetwork.message.getString("other_message." + path);
    }
}
