package net.skyexcel.server.job.data.stat;

import skyexcel.data.file.Config;

/**
 * 스탯포인트를 찍을 수 있는 클래스 입니다.
 */
public class Statable {

    private int level;

    private String name;





    public Statable(String name) {
        this.name = name;
    }


    public int getLevel() {
        return level;
    }

    public void setStat(){

    }

    public String getName() {
        return name;
    }
}
