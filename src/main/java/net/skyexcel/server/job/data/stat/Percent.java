package net.skyexcel.server.job.data.stat;

import java.util.Random;

public interface Percent {

    default boolean chance(double percent) {
        double result = percent / 100.0 * 100.0;
        int rand = (new Random()).nextInt(100) + 1;
        return (double) rand <= result;
    }
}
