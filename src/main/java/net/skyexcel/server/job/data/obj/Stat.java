package net.skyexcel.server.job.data.obj;

import skyexcel.data.file.Config;

public class Stat {

    private String name;

    private Config config;


    public Stat(String name, Config config) {
        this.name = name;
        this.config = config;
    }
}
