package com.example.demo.designPatterns.adapterDesignPattern;

public class AdapterCharger implements AppleCharger{

    private AndroidCharger androidCharger;

    public AdapterCharger(AndroidCharger androidCharger) {
        this.androidCharger = androidCharger;
    }

    @Override
    public void chargePhone() {
        androidCharger.chargePhone();
        System.out.println("Your phone is charging with adapter");
    }
}
