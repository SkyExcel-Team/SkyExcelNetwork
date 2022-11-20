package net.skyexcel;

import lombok.Getter;

public class Percent {

    private int S = 0;
    private int A = 0;
    private int B = 0;
    @Getter
    private int C = 100;


    public void increase() {
        C -= 3;
        System.out.println(C / 100);
    }


    public void get() {
        System.out.println("S: " + S);
        System.out.println("A: " + A);
        System.out.println("B: " + B);
        System.out.println("C: " + C);
    }

    public boolean equalMax() {
        return (S + A + B + C) == 100;
    }
}
