package net.skyexcel.server.job.data;

public class Stat {

    private StatMeta statMeta;
    public Stat(String name){
        statMeta = new StatMeta(name);
    }

    public StatMeta getStatMeta() {
        return statMeta;
    }
}
