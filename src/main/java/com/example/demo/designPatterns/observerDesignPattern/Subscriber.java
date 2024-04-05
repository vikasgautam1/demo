package com.example.demo.designPatterns.observerDesignPattern;

public class Subscriber implements Observer{

    String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void notified(String newVideoName) {
        System.out.println(this.name + " is notified successfully for new video: " + newVideoName);
    }
}
