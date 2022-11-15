package net.skyexcel.server.job.data;

import java.util.List;

public class StatMeta {
    private String displayName;
    private List<String> description;

    public StatMeta(String displayName) {
        this.displayName = displayName;
    }


    public void setDescription(List<String> description) {
        this.description = description;
    }
}
