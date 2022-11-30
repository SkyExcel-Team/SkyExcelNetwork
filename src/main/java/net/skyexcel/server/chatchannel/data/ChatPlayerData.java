package net.skyexcel.server.chatchannel.data;

import net.skyexcel.server.chatchannel.gui.ChatLogGUI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatPlayerData {
    public static Map<UUID, ChatLog> chatLogMap = new HashMap<>();
    public static Map<UUID, ChatLogGUI> chatLogGUIMap = new HashMap<>();
}
