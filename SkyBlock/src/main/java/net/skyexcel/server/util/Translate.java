package net.skyexcel.server.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Translate {


    public static String collapse(String[] args, int from){
        return String.join(" ", Arrays.copyOfRange(args, from, args.length));
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
