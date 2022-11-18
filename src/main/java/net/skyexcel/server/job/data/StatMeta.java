package net.skyexcel.server.job.data;

import java.util.List;

public class StatMeta {
    private String displayName;
    private List<String> description;

    public StatMeta(String displayName, List<String> description) {
        this.displayName = displayName;
        this.description = description;
    }


    public String getDisplayName() {
        return displayName;
    }

    public List<String> getDescription() {
        return description;
    }
}
