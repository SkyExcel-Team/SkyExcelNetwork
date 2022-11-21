package net.skyexcel.server.quest.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class QuestCompleteEvent extends Event {

    private final HandlerList HANDLERS_LIST = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}

