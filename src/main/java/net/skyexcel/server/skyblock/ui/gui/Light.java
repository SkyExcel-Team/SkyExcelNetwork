package net.skyexcel.server.skyblock.ui.gui;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockSupport;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Light implements org.bukkit.block.data.type.Light {


    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(int level) {

    }

    @Override
    public int getMaximumLevel() {
        return 0;
    }

    @Override
    public int getMinimumLevel() {
        return 0;
    }

    @Override
    public boolean isWaterlogged() {
        return false;
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {

    }

    @Override
    public @NotNull Material getMaterial() {
        return null;
    }

    @Override
    public @NotNull String getAsString() {
        return null;
    }

    @Override
    public @NotNull String getAsString(boolean hideUnspecified) {
        return null;
    }

    @Override
    public @NotNull BlockData merge(@NotNull BlockData data) {
        return null;
    }

    @Override
    public boolean matches(@Nullable BlockData data) {
        return false;
    }

    @Override
    public @NotNull BlockData clone() {
        return null;
    }

    @Override
    public @NotNull SoundGroup getSoundGroup() {
        return null;
    }

    @Override
    public boolean isSupported(@NotNull Block block) {
        return false;
    }

    @Override
    public boolean isSupported(@NotNull Location location) {
        return false;
    }

    @Override
    public boolean isFaceSturdy(@NotNull BlockFace face, @NotNull BlockSupport support) {
        return false;
    }

    @Override
    public boolean isRandomlyTicked() {
        return false;
    }

    @Override
    public boolean isPreferredTool(@NotNull ItemStack tool) {
        return false;
    }
}
