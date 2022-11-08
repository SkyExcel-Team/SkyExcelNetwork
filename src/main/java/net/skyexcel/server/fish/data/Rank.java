package net.skyexcel.server.fish.data;

import net.skyexcel.server.items.data.Items;
import org.bukkit.entity.Player;

public class Rank {

    private int first = 3;
    private int second = 2;
    private int third = 1;

    private Items[] items = {new Items("금메달"),new Items("은메달"),new Items("동메달")};
    public Rank(){

    }
    public void giveReward(Player player, int rank){

        player.getInventory().addItem(items[rank].getItemStack());
    }
}
