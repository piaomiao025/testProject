package com.man.demo.jvm;

public class SwitchTest {
    public static void main(String[] args) {
        String option;
        if(args.length == 0) {
            option = "0";
        } else {
            option = args[0];
        }
        int optionInt = Integer.valueOf(option);
        switch (optionInt) {
            case 0:
                System.out.println("option 0");
                break;
            case 1:
                System.out.println("option 1");
                break;
            default:
        }
    }
}
