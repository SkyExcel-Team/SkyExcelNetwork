package net.skyexcel.server.chatchannel.data;

public enum ChatChannel {
    GLOBAL("전체"),
    LOCAL("지역"),
    SKYBLOCK("섬"),
    DEBUG("디버그");

    private String name;


    ChatChannel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
