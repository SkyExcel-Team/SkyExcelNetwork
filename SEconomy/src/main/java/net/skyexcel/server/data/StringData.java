package net.skyexcel.server.data;

import com.google.common.base.Joiner;
import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.util.Translate;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.stream.Collectors;

public class StringData {

    public static void myMoney(Player player) {
        player.sendMessage(Translate.moneyCheck(player,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.my_money"))));
    }

    public static void targetMoney(Player player, OfflinePlayer target) {
        player.sendMessage(Translate.moneyCheckTarget(target,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.check_player_money"))));
    }

    public static void sendMoney(Player player, Player target, long amount) {
        player.sendMessage(Translate.moneyAction(player, target, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.send_money"))));
    }


    public static void sendMoney(Player player, OfflinePlayer target, long amount) {
        player.sendMessage(Translate.moneyAction(player, target, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.send_money"))));
    }

    public static void removeMoney(Player player, OfflinePlayer target, long amount) {
        player.sendMessage(Translate.moneyAction(player, target, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.remove_money"))));
    }

    public static void setMoney(Player player, Player target, long amount) {
        player.sendMessage(Translate.moneyAction(player, target, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.set_money"))));
    }

    public static void resetMoney(Player player, OfflinePlayer target, long amount) {
        player.sendMessage(Translate.moneyAction(player, target, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economy_message.reset_money"))));
    }

    public static void shopBuyOne(Player player, ItemStack item, int amount) {
        player.sendMessage(Translate.itemAction(item, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.buy_one"))));
    }

    public static void shopSellOne(Player player, ItemStack item, int amount) {
        player.sendMessage(Translate.itemAction(item, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.sell_one"))));
    }

    public static void shopBuySet(Player player, ItemStack item, int amount) {
        player.sendMessage(Translate.itemAction(item, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.buy_64"))));
    }

    public static void shopSellSet(Player player, ItemStack item, int amount) {
        player.sendMessage(Translate.itemAction(item, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.sell_64"))));
    }

    public static void shopSettingBuyPrice(Player player) {
        player.sendMessage(Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.setting_buy_price")));
    }

    public static void shopSettingSellPrice(Player player) {
        player.sendMessage(Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.setting_sell_price")));
    }

    public static void shopCreate(Player player, String name) {
        player.sendMessage(Translate.shopAction(name,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.create_economyshop"))));
    }

    public static void shopDelete(Player player, String name) {
        player.sendMessage(Translate.shopAction(name,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.recyclebin_economyshop"))));
    }

    public static void shopDeleteForever(Player player, String name) {
        player.sendMessage(Translate.shopAction(name,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.delete_economyshop"))));
    }

    public static void shopResize(Player player, String name, int size) {
        player.sendMessage(Translate.shopResize(name, size,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.set_gui_size"))));
    }

    public static void shopResize(Player player, String name, String newName) {
        player.sendMessage(Translate.shopRename(name, newName,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.set_gui_name"))));
    }

    public static void shopSetBuyPrice(Player player, int amount) {
        player.sendMessage(Translate.itemAction(null, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.set_buy_price"))));
    }

    public static void shopSetSellPrice(Player player, int amount) {
        player.sendMessage(Translate.itemAction(null, amount,
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("economyshop_message.set_sell_price"))));
    }

    public static void overFlow(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.overflow")));
    }

    public static void canNotBuy(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.cannot_buy")));
    }

    public static void canNotSell(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.cannot_sell")));
    }

    public static void inventoryFull(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.inventory_full")));
    }

    public static void nonePlayer(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.command_none_player")));
    }

    public static void noneMoney(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.command_none_money")));
    }

    public static void impossibleBuyItem(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.cannot_buy_impossible_item")));
    }

    public static void impossibleSellItem(Player player) {
        player.sendMessage(
                Objects.requireNonNull(SkyExcelNetwork.message.getConfig().getString("error_message.cannot_sell_impossible_item")));
    }


    public static void command_guide_economy(Player player) {

        for (String line : SkyExcelNetwork.message.getConfig().getStringList("other_message.command_guide_economy")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }


    public static void command_economy(Player player) {

        for (String line : SkyExcelNetwork.message.getConfig().getStringList("other_message.command_economy")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

}
