package com.example.demo.designPatterns.adapterDesignPattern;

public class Iphone13 {

    private AppleCharger appleCharger;

    public Iphone13(AppleCharger appleCharger) {
        this.appleCharger = appleCharger;
    }

    public void chargeIphone(){
        appleCharger.chargePhone();
        System.out.println("Iphone is being charged..!");
    }
}
