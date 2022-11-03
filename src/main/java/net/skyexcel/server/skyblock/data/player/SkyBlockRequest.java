package net.skyexcel.server.skyblock.data.player;

import java.util.ArrayList;
import java.util.List;

/***
 * 요청과 수락을 관리하는 클래스 입니다.
 *
 */
public class SkyBlockRequest {


    private List<Object> from = new ArrayList<>();
    private List<Object> to = new ArrayList<>();
    
    static {
        System.out.println("사용중 테스트");
    }


    /**
     * Request 클래스에게 요청을 보냅니다.
     *
     * @param request
     * @return
     */
    public static boolean send(SkyBlockRequest request, Object to, Object from) {


        if (!request.to.contains(to) && !request.from.contains(from)) {

            request.from.add(from);
            request.to.add(to);


            return true;
        }

        return false;
    }


    /**
     * Request 클래스에게 요청을 보냅니다.
     *
     * @param request
     * @return
     */
    public static boolean is(SkyBlockRequest request, Object to, Object from) {

        if (request.from.contains(to) && request.to.contains(from)) {
            return true;
        }

        return false;
    }

    /**
     * Request 클래스에게 요청을 보냅니다.
     *
     * @param request
     * @return
     */
    public boolean accept(Object to, Object from) {


        if (this.to.contains(to) && this.from.contains(from)) {

            this.from.remove(from);
            this.to.remove(to);
            return true;
        }

        return false;
    }

    /**
     * Request 클래스에게 요청을 보냅니다.
     *
     * @param request
     * @return
     */
    public static boolean accept(SkyBlockRequest request, Object to, Object from) {


        if (request.to.contains(to) && request.from.contains(from)) {

            request.from.remove(from);
            request.to.remove(to);
            return true;
        }

        return false;
    }


    /**
     * Request 클래스에게 요청을 보냅니다.
     *
     * @param request
     * @return
     */
    public static boolean deny(SkyBlockRequest request, Object to, Object from) {
        if (request.to.contains(to) && request.from.contains(from)) {

            request.from.remove(from);
            request.to.remove(to);
            return true;
        }

        return false;
    }
}
