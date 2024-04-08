package com.example.demo.designPatterns.adapterDesignPattern;

public class ChargerChinaAndroidXYZ implements AndroidCharger{
    @Override
    public void chargePhone() {
        System.out.println("ChinaAndroidXYZ charger is charging your phone...");
    }
}
