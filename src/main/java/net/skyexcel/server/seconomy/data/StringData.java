package net.skyexcel.server.seconomy.data;

import net.skyexcel.api.util.Translate;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

public class StringData {

    private Translate translate;

    public StringData() {
        translate = new Translate();
    }

    public String myMoney(Player player) {
        SEConomy money = new SEConomy(player);

        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("my_money")
                .replaceAll("%money%", format(money.getLong())));
    }

    public String checkPlayerMoney(Player target) {
        SEConomy money = new SEConomy(target);
        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("check_player_money")
                .replaceAll("%money%", format(money.getLong()))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String sendMoney(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("send_money")
                .replaceAll("%money%", format(amount))
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

    public String sendmoneyAllPlayer(long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("send_money_all_player")
                .replaceAll("%money%", format(amount)));
    }

    public String removemoney(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("remove_money")
                .replaceAll("%money%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String setmoney(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("set_money")
                .replaceAll("%money%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String resetmoney(Player target) {

        return ChatColor.translateAlternateColorCodes('&', getmoneyMessage("reset_money")
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String buyOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("buy_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String sellOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("sell_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String settingBuyPrice() {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("setting_buy_price"));
    }

    public String settingSellPrice() {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("setting_sell_price"));
    }

    public String createmoneyShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("create_moneyshop")
                .replaceAll("%moneyshop_name%", name));
    }

    public String recyclebinmoneyShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("recyclebin_moneyshop")
                .replaceAll("%moneyshop_name%", name));
    }

    public String deletemoneyShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("delete_moneyshop")
                .replaceAll("%moneyshop_name%", name));
    }

    public String setGuiSizemoneyShop(String name, int size) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("set_gui_size")
                .replaceAll("%moneyshop_name%", name)
                .replaceAll("%moneyshop_gui_size%", String.valueOf(size)));
    }


    public String setNamemoneyShop(String original, String newName) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("set_gui_name")
                .replaceAll("%moneyshop_name%", original)
                .replaceAll("%set_moneyshop_name%", newName));
    }

    public List<String> main() {
        return translate.msgCollapse(SkyExcelNetworkSEconomyMain.message.getConfig().getStringList("other_message.command_moneyshop"));
    }

    public String setBuyPricemoneyShop(int price) {
        return ChatColor.translateAlternateColorCodes('&', getmoneyShopMessage("set_buy_price")
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


    public String nonemoney() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("command_none_money"));
    }

    private String getmoneyMessage(String path) {

        return SkyExcelNetworkSEconomyMain.message.getString("money_message." + path);
    }

    private String getmoneyShopMessage(String path) {

        return SkyExcelNetworkSEconomyMain.message.getString("moneyshop_message." + path);
    }

    private String getErrorMessage(String path) {

        return SkyExcelNetworkSEconomyMain.message.getString("error_message." + path);
    }

    private String getOtherMessage(String path) {

        return SkyExcelNetworkSEconomyMain.message.getString("other_message." + path);
    }


    private String format(long amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        return decFormat.format(amount);
    }
}
