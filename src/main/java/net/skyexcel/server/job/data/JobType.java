package net.skyexcel.server.job.data;

public enum JobType {
    FARM("농부"), FISHERMAN("낚시꾼"), MINEWORKER("광부"), NONE("없음");
    private String name;

    JobType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
