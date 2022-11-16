package net.skyexcel.server.job.data;

import java.util.List;

public class JobMeta {

    private String displayName;

    private List<String> description;


    public JobMeta(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getDescription() {
        return description;
    }
}
