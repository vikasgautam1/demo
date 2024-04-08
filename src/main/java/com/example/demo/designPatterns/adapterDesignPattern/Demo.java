package com.example.demo.designPatterns.adapterDesignPattern;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Program started...");

        AppleCharger appleCharger = new ChargerKoreaAppleXYZ();
        Iphone13 iphone13 = new Iphone13(appleCharger);
        iphone13.chargeIphone();

        System.out.println("---------");
        // What if we don't have Iphone charger i.e ChargerKoreaAppleXYZ
        // Then Adapter design pattern comes into play

        AppleCharger appleCharger1 = new AdapterCharger(new ChargerChinaAndroidXYZ());
        Iphone13 anotherIphone13 = new Iphone13(appleCharger1);
        anotherIphone13.chargeIphone();
    }
}
