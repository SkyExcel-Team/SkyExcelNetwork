package net.skyexcel.server.alphachest.data;

public enum Storage {
    DEFAULT_1(StorageType.DEFAULT, 1),
    DEFAULT_2(StorageType.DEFAULT, 2),
    DEFAULT_3(StorageType.DEFAULT, 3),
    DEFAULT_4(StorageType.DEFAULT, 4),

    CASH_1(StorageType.CASH, 1),
    CASH_2(StorageType.CASH, 2),
    CASH_3(StorageType.CASH, 3),
    CASH_4(StorageType.CASH, 4);

    private final StorageType storageType;
    private final Integer storageNum;

    Storage(StorageType storageType, Integer storageNum) {
        this.storageType = storageType;
        this.storageNum = storageNum;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public Integer getStorageNum() {
        return storageNum;
    }
}
