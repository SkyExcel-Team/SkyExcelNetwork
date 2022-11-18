package net.skyexcel.server.job.data;

import java.util.List;

public class Stat {

    private StatMeta statMeta;

    private int stat;

    public Stat(String name) {
        statMeta = new StatMeta(name, List.of());
    }


    public int getStat() {
        return stat;
    }

    public StatMeta getStatMeta() {
        return statMeta;
    }
}
