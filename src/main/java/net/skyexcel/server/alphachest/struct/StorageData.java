package net.skyexcel.server.alphachest.struct;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageData {
    public static Map<UUID, Storage> storageHashMap = new HashMap<>();

    public static Map<UUID, CashStorage> cashStorageHashMap = new HashMap<>();
}
