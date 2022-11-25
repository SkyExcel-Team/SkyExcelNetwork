package net.skyexcel.server.quest.gui.button;

import net.skyexcel.server.items.data.Items;

public class Button extends Items {

    private int slot;

    public Button(int slot) {
        super("");
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
