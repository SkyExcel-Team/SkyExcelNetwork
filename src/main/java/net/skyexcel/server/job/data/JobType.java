package net.skyexcel.server.job.data;

public enum JobType {
    MINE("광부"), FISH("낚시꾼"), FARM("농부"), NULL("NULL");


    private String name;

    JobType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
