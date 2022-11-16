package net.skyexcel.server;

public class test {
    public static void main(String[] args) throws Exception {
        int max = 80; //77

        for (int i = 1; i < max; i++) {
            String test = "{\"predicate\": {\"custom_model_data\": " + i + "}, \"model\": \"item/fish/" + i + "\"},";
            System.out.println(test);
        }


    }
}
