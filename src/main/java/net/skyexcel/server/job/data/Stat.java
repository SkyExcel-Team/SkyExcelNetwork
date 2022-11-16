package net.skyexcel.server.job.data;

public class Stat {

    private StatMeta statMeta;

    private int stat;

    public Stat(String name){
        statMeta = new StatMeta(name);
    }


    public int getStat() {
        return stat;
    }

    public StatMeta getStatMeta() {
        return statMeta;
    }
}
