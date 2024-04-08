package com.example.demo.designPatterns.adapterDesignPattern;

public class ChargerKoreaAppleXYZ implements AppleCharger{
    @Override
    public void chargePhone() {
        System.out.println("KoreaAppleXYZ charger is charging your phone...");
    }
}
