package net.skyexcel.server.fish.data;

public enum FishRank {
    SPlus("§x§f§b§d§c§1§0S§x§f§b§d§c§1§0S§x§f§b§d§c§1§0+"), S("§x§f§b§f§9§3§dS"), APlus("§x§5§b§f§d§5§2A§x§2§e§c§6§3§9+"), A("§x§5§b§f§d§5§2A"), BPlus("§x§3§e§4§6§f§fB§x§3§1§3§7§c§8+"), B("§x§3§e§4§6§f§fB"), CPlus("§x§c§8§6§d§1§9C§x§b§1§6§1§1§6+"), C("§x§c§8§6§d§1§9C");

    private String name;

    FishRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public boolean equalRank(FishRank rank) {
        return (rank.getName().equalsIgnoreCase(getName()));
    }

    public boolean isPlus() {
        return name.contains("+");
    }
}
