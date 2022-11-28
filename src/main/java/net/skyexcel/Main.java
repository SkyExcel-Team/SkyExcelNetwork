package net.skyexcel;

public class Main {

    public static void main(String[] args) {

        Percent percent = new Percent();

        double max = 44;
        double complete = 25;


        double oneTotalValue = 44;
        double oneNowValue = 29;
        double onePerc = (double) oneNowValue / (double) oneTotalValue * 100.0;

        System.out.println("oneTotalValue : " + onePerc);


//        // 100이 최대레벨임.
//        for (int i = 0; i < 100; i++) {
//            percent.increase();
//            percent.get();
//
//            if (percent.equalMax()) {
//                System.out.println("100임");
//                break;
//            }
//        }
    }
}
