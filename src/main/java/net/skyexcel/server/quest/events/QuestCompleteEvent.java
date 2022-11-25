package net.skyexcel.server.quest.events;

import net.skyexcel.server.quest.struct.Quest;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QuestCompleteEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Player player;

    private Quest quest;



    public QuestCompleteEvent(Player player) {
        this.player = player;
    }

    public QuestCompleteEvent(Player player, Quest quest) {
        this.player = player;
        this.quest = quest;
    }


    public Player getPlayer() {
        return player;
    }

    public Quest getQuest() {
        return quest;
    }

    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }


}

