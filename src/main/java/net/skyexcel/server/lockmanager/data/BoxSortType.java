package net.skyexcel.server.lockmanager.data;

public enum BoxSortType {
    ONLINE("온라인 플레이어"), MEMBER("섬원 목록");

    private String name;


    BoxSortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
