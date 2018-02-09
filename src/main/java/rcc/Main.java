package rcc;

import rcc.mc.NuSMV;
import rcc.mc.Response;

public class Main {

    public static void main(String[] args) {

        NuSMV mc = new NuSMV(600);
        //Response res =
        mc.runAsync("tests/test0.out.nusmv", (res) -> {
            System.out.println("State: " + res.getState());
            System.out.println("Output: " + res.getOutput());
        });

    }
}
