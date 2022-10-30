package net.skyexcel.server;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private List<Object> from = new ArrayList<>();
    private List<Object> to = new ArrayList<>();

    static {
        new Request(); //메소드를 불러올때, 새로운 객체를 생성
    }

    public static boolean send(Request request, Object to, Object from) {


        if (!request.to.contains(to) && !request.from.contains(from)) {

            request.from.add(from);
            request.to.add(to);


            return true;
        }

        return false;
    }


    public static boolean is(Request request, Object to, Object from) {

        if (request.from.contains(to) && request.to.contains(from)) {
            return true;
        }

        return false;
    }

    public boolean accept(Object to, Object from) {


        if (this.to.contains(to) && this.from.contains(from)) {

            this.from.remove(from);
            this.to.remove(to);
            return true;
        }

        return false;
    }


    public static boolean accept(Request request, Object to, Object from) {


        if (request.to.contains(to) && request.from.contains(from)) {

            request.from.remove(from);
            request.to.remove(to);
            return true;
        }

        return false;
    }


    public static boolean deny(Request request, Object to, Object from) {
        if (request.to.contains(to) && request.from.contains(from)) {

            request.from.remove(from);
            request.to.remove(to);
            return true;
        }

        return false;
    }

}
