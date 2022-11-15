package net.skyexcel.server.job.data.stat;

public class Statable {

    private int level;

    private String name;

    public Statable(String name) {
        this.name = name;
    }


    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
