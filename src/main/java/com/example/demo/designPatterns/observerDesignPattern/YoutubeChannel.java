package com.example.demo.designPatterns.observerDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel implements Subject{

    String name;

    public YoutubeChannel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    List<Observer> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyChanges(String newVideoName) {
        for(Observer observer: this.subscribers){
            observer.notified(newVideoName);
        }
    }

    public List<Observer> getSubscribersFromName(String name){
        List<Observer> observersMatchingName = new ArrayList<>();
        for(Observer observer: this.subscribers){
            if(name.equalsIgnoreCase(((Subscriber)observer).getName())){
                observersMatchingName.add(observer);
            }
        }
        return observersMatchingName;
    }
}
