package net.skyexcel.server.mileage.data;

import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.mileage.util.Translate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

public class StringData {

    private Translate translate;

    public StringData() {
        translate = new Translate();
    }

    public String mymileage(Player player) {
        Mileage mileage = new Mileage(player);

        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("my_mileage")
                .replaceAll("%mileage%", format(mileage.getLong())));
    }

    public String checkPlayermileage(Player target) {
        Mileage mileage = new Mileage(target);
        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("check_player_mileage")
                .replaceAll("%mileage%", format(mileage.getLong()))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String sendmileage(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("send_mileage")
                .replaceAll("%mileage%", format(amount))
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

    public String sendmileageAllPlayer(long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("send_mileage_all_player")
                .replaceAll("%mileage%", format(amount)));
    }

    public String removemileage(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("remove_mileage")
                .replaceAll("%mileage%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String setmileage(Player target, long amount) {

        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("set_mileage")
                .replaceAll("%mileage%", format(amount))
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String resetmileage(Player target) {

        return ChatColor.translateAlternateColorCodes('&', getmileageMessage("reset_mileage")
                .replaceAll("%player%", target.getDisplayName()));
    }

    public String buyOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("buy_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String sellOne(Player target, int price) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("sell_one")
                .replaceAll("%item_order%", target.getDisplayName())
                .replaceAll("%price%", format(price)));
    }

    public String settingBuyPrice() {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("setting_buy_price"));
    }

    public String settingSellPrice() {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("setting_sell_price"));
    }

    public String createmileageShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("create_mileageshop")
                .replaceAll("%mileageshop_name%", name));
    }

    public String recyclebinmileageShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("recyclebin_mileageshop")
                .replaceAll("%mileageshop_name%", name));
    }

    public String deletemileageShop(String name) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("delete_mileageshop")
                .replaceAll("%mileageshop_name%", name));
    }

    public String setGuiSizemileageShop(String name, int size) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("set_gui_size")
                .replaceAll("%mileageshop_name%", name)
                .replaceAll("%mileageshop_gui_size%", String.valueOf(size)));
    }


    public String setNamemileageShop(String original, String newName) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("set_gui_name")
                .replaceAll("%mileageshop_name%", original)
                .replaceAll("%set_mileageshop_name%", newName));
    }

    public List<String> main() {
        return translate.msgCollapse(SkyExcelNetworkMileageMain.message.getConfig().getStringList("other_message.command_mileageshop"));
    }

    public String setBuyPricemileageShop(int price) {
        return ChatColor.translateAlternateColorCodes('&', getmileageShopMessage("set_buy_price")
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


    public String nonemileage() {
        return ChatColor.translateAlternateColorCodes('&', getErrorMessage("command_none_mileage"));
    }

    private String getmileageMessage(String path) {

        return SkyExcelNetworkMileageMain.message.getString("mileage_message." + path);
    }

    private String getmileageShopMessage(String path) {

        return SkyExcelNetworkMileageMain.message.getString("mileageshop_message." + path);
    }

    private String getErrorMessage(String path) {

        return SkyExcelNetworkMileageMain.message.getString("error_message." + path);
    }

    private String getOtherMessage(String path) {

        return SkyExcelNetworkMileageMain.message.getString("other_message." + path);
    }


    private String format(long amount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        return decFormat.format(amount);
    }
}
