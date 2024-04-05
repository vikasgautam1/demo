package com.example.demo.designPatterns.observerDesignPattern;

public interface Subject {

    void subscribe(Observer observer);

    void unSubscribe(Observer observer);

    void notifyChanges(String newVideoName);
}
