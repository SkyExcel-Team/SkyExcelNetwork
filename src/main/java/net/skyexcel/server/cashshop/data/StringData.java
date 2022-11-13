package net.skyexcel.server.cashshop.data;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.util.Translate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

public class StringData {

    private Translate translate;

    public StringData() {
        translate = new Translate();
    }

    public String myCash(Player player) {
        Cash cash = new Cash(player);

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("my_cash")
                .replaceAll("%cash%", String.valueOf(cash.getLong())));
    }

    public String checkPlayerCash(Player target) {
        Cash cash = new Cash(target);
        return ChatColor.translateAlternateColorCodes('&', getCashMessage("check_player_cash")
                .replaceAll("%cash%", format(cash.getLong()))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String sendCash(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("send_cash")
                .replaceAll("%cash%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    /**
     * 구매와 판매 모두 못할 경우 출력되는 로어 입니다.
     *
     * @return 설명
     */
    public List<String> cantBoth() {

        return translate.msgCollapse(List.of("",
                "&f[&b!&f] 구매가 불가능한 상품 입니다."
                , "&f[&b!&f] 판매가 불가능한 상품입니다."
                , ""));
    }

    /**
     * 판매와 구매 모두 가능 할 때 출력되는 로어 입니다.
     *
     * @param buy  구매 가격
     * @param sell 판매 가격
     * @return 설명
     */
    public List<String> canBoth(long buy, long sell) {

        return translate.msgCollapse(List.of(""
                , "&f[&b!&f] 구매 가격:  " + buy + " 캐시"
                , "&f[&b!&f] 판매 가격:  " + sell + " 캐시"

                , ""
                , "&f[&b!&f] 쉬프트 + 좌클릭: 64개 구매"
                , "&f[&b!&f] 쉬프트 + 우클릭: 64개 판매"

                , ""));
    }

    /**
     * 판매가 불가능 하고, 구매만 가능 했을 때 로어입니다.
     *
     * @param buy 구매 가격
     * @return 로어
     */
    public List<String> canBuy(long buy) {
        return translate.msgCollapse(List.of(""
                , "&f[&b!&f] 구매 가격:  " + buy + " 캐시"

                , ""
                , "&f[&b!&f] 판매가 불가능한 상품 입니다."
                , "&f[&b!&f] 쉬프트 + 우클릭: 64개 구매"

                , ""));
    }

    /**
     * 구매가 불가능하고, 판매만 가능 했을 때 로어입니다.
     *
     * @param sell 판매 가격
     * @return 설명
     */
    public List<String> canSell(long sell) {

        return translate.msgCollapse(List.of(""
                , "&f[&b!&f] 구매가 불가능한 상품 입니다."
                , "&f[&b!&f] 판매 가격:  " + sell + " 캐시"
                , ""
                , "&f[&b!&f] 쉬프트 + 좌클릭: 64개 판매"

                , ""));
    }

    public String sendCashAllPlayer(long amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("send_cash_all_player")
                .replaceAll("%cash%", String.valueOf(amount)));
    }

    public String removeCash(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("remove_cash")
                .replaceAll("%cash%", String.valueOf(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String setCash(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("set_cash")
                .replaceAll("%cash%", String.valueOf(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String resetCash(Player target) {

        return ChatColor.translateAlternateColorCodes('&', getCashMessage("reset_cash")
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String buyOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("buy_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String sellOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("sell_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String settingBuyPrice() {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("setting_buy_price"));
    }

    public String settingSellPrice() {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("setting_sell_price"));
    }

    public String createCashShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("create_cashshop")
                .replaceAll("%cashshop_name%", name));
    }

    public String recyclebinCashShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("recyclebin_cashshop")
                .replaceAll("%cashshop_name%", name));
    }

    public String deleteCashShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("delete_cashshop")
                .replaceAll("%cashshop_name%", name));
    }

    public String setGuiSizeCashShop(String name, int size) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("set_gui_size")
                .replaceAll("%cashshop_name%", name)
                .replaceAll("%cashshop_gui_size%", String.valueOf(size)));
    }


    public String setNameCashShop(String original, String newName) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("set_gui_name")
                .replaceAll("%cashshop_name%", original)
                .replaceAll("%set_cashshop_name%", newName));
    }

    public List<String> main() {
        return translate.msgCollapse(SkyExcelNetworkCashShopMain.message.getConfig().getStringList("other_message.command_cashshop"));
    }

    public String setBuyPriceCashShop(int price) {
        return ChatColor.translateAlternateColorCodes('&', getCashShopMessage("set_buy_price")
                .replaceAll("%price%", format(price)));
    }

    public String overFlow() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("overflow"));
    }


    public String canNotBuy() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("cannot_buy"));
    }

    public String canNotSell() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("cannot_sell"));
    }

    public String inventoryFull() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("inventoryFull"));
    }

    public String nonePlayer() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("command_none_player"));
    }

    public String impossibleItemBuy() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("impossibleItem_buy"));
    }

    public String impossibleItemSell() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("impossibleItem_sell"));
    }


    public String noneCash() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("command_none_cash"));
    }

    private String getCashMessage(String path) {

        return SkyExcelNetworkCashShopMain.message.getString("cash_message." + path);
    }

    private String getCashShopMessage(String path) {

        return SkyExcelNetworkCashShopMain.message.getString("cashshop_message." + path);
    }

    private String getErrorMessage(String path) {

        return SkyExcelNetworkCashShopMain.message.getString("error_message." + path);
    }

    private String getOtherMessage(String path) {

        return SkyExcelNetworkCashShopMain.message.getString("other_message." + path);
    }


    private String format(long amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        return decFormat.format(amount);
    }
}
