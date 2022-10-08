package net.skyexcel.server.util;

public class Translate {
    public static String msgCollapse(String[] args, int index) {
        StringBuilder string = new StringBuilder();
        if (args.length > index) {
            for (int i = index; i < args.length; i++) {
                if (args.length != index) {
                    if (i == index) {
                        string.append(args[i]);
                    } else {
                        string.append(" " + args[i]);
                    }
                }
            }
        }
        return string.toString();
    }
}
