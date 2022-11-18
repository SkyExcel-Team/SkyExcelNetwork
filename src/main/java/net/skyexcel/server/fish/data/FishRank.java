package net.skyexcel.server.fish.data;

public enum FishRank {
    SPlus("S+"), S("S"), APlus("A+"), A("A"), BPlus("B+"), B("B"), CPlus("C+"), C("C"), DPlus("D+"), D("D");

    private String name;

    FishRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
