package net.skyexcel.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Translate {
    public static String getDate() {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Date date = new Date();
        String nowTime = format2.format(date);
        return nowTime;
    }
}
