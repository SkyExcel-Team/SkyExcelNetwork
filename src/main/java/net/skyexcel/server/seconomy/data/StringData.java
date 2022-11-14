package net.skyexcel.server.seconomy.data;

import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import net.skyexcel.server.seconomy.util.Translate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

public class StringData {

    private Translate translate;

    public StringData() {
        translate = new Translate();
    }

    public String mymoneny(Player player) {
        SEConomy moneny = new SEConomy(player);

        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("my_moneny")
                .replaceAll("%moneny%", format(moneny.getLong())));
    }

    public String checkPlayermoneny(Player target) {
        SEConomy moneny = new SEConomy(target);
        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("check_player_moneny")
                .replaceAll("%moneny%", format(moneny.getLong()))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String sendmoneny(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("send_moneny")
                .replaceAll("%moneny%", format(amount))
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

    public String sendmonenyAllPlayer(long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("send_moneny_all_player")
                .replaceAll("%moneny%", format(amount)));
    }

    public String removemoneny(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("remove_moneny")
                .replaceAll("%moneny%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String setmoneny(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("set_moneny")
                .replaceAll("%moneny%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String resetmoneny(Player target) {

        return ChatColor.translateAlternateColorCodes('&', getmonenyMessage("reset_moneny")
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String buyOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("buy_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String sellOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("sell_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String settingBuyPrice() {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("setting_buy_price"));
    }

    public String settingSellPrice() {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("setting_sell_price"));
    }

    public String createmonenyShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("create_monenyshop")
                .replaceAll("%monenyshop_name%", name));
    }

    public String recyclebinmonenyShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("recyclebin_monenyshop")
                .replaceAll("%monenyshop_name%", name));
    }

    public String deletemonenyShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("delete_monenyshop")
                .replaceAll("%monenyshop_name%", name));
    }

    public String setGuiSizemonenyShop(String name, int size) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("set_gui_size")
                .replaceAll("%monenyshop_name%", name)
                .replaceAll("%monenyshop_gui_size%", String.valueOf(size)));
    }


    public String setNamemonenyShop(String original, String newName) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("set_gui_name")
                .replaceAll("%monenyshop_name%", original)
                .replaceAll("%set_monenyshop_name%", newName));
    }

    public List<String> main() {
        return translate.msgCollapse(SkyExcelNetworkSEconomyMain.message.getConfig().getStringList("other_message.command_monenyshop"));
    }

    public String setBuyPricemonenyShop(int price) {
        return ChatColor.translateAlternateColorCodes('&', getmonenyShopMessage("set_buy_price")
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


    public String nonemoneny() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("command_none_moneny"));
    }

    private String getmonenyMessage(String path) {

        return SkyExcelNetworkSEconomyMain.message.getString("moneny_message." + path);
    }

    private String getmonenyShopMessage(String path) {

        return SkyExcelNetworkSEconomyMain.message.getString("monenyshop_message." + path);
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
