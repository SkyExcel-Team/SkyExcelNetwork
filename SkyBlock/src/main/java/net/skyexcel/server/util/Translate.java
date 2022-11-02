package net.skyexcel.server.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Translate {

    public static String parseTime(long time) {
        long gameTime = time;
        long hours = gameTime / 1000 + 6;
        long minutes = (gameTime % 1000) * 60 / 1000;
        String ampm = "AM";
        if (hours >= 12) {
            hours -= 12;
            ampm = "오후";
        }
        if (hours >= 12) {
            hours -= 12;
            ampm = "오전";
        }
        if (hours == 0) hours = 12;
        String mm = "0" + minutes;
        mm = mm.substring(mm.length() - 2, mm.length());
        return hours + ":" + mm + " " + ampm;
    }
    
    public static String getDate() {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Date date = new Date();
        String nowTime = format2.format(date);
        return nowTime;
    }
    public static String decal(long amount){
        DecimalFormat decFormat = new DecimalFormat("###,###");

        return decFormat.format(amount);
    }
}
