package net.skyexcel.server.mileage.data.shop;


import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import skyexcel.data.file.util.Stockable;

public class MileageShop extends Stockable {
    public MileageShop(String name) {
        super("path", name, SkyExcelNetworkMileageMain.plugin);
    }
}
