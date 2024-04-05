package com.example.demo.designPatterns.observerDesignPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException {
        YoutubeChannel youtubeChannel = new YoutubeChannel("classifiedYt");
        List<String> subscribersName = Arrays.asList("Aman", "Rajat", "Joju");
        for(String subscriberName: subscribersName){
            youtubeChannel.subscribe(new Subscriber(subscriberName));
        }

        System.out.println(youtubeChannel.getName() + " is uploading new video...");
        System.out.println("New video uploaded, Notifying the subscribers...");
        youtubeChannel.notifyChanges("Nirwana");
        System.out.println("All subscribers are notified!");

        // A subscriber Aman is unsubscribing...
        youtubeChannel.unSubscribe(youtubeChannel.getSubscribersFromName("Aman").get(0));

        System.out.println("");
        System.out.println(youtubeChannel.getName() + " is uploading another new video...");
        System.out.println("New video uploaded, Notifying the subscribers...");
        youtubeChannel.notifyChanges("Nirwana2.0");
        System.out.println("All subscribers are notified!");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Youtube channel name is: " + youtubeChannel.getName());
            System.out.println("Press 1 to upload a new video and notify subscribers");
            System.out.println("Press 2 to create a new subscriber");
            System.out.println("Press 3 to unsubscribe a subscriber");
            System.out.println("Press 4 to exit");

            String input = null;
            int pressedNumber = 0;
            try{
                input = bufferedReader.readLine();
                pressedNumber = Integer.parseInt(input);
            }
            catch (Exception ex){
                System.out.println("Error while converting --> " + ex.getMessage());
            }

            if(pressedNumber == 1){
                System.out.println("Enter new video name: ");
                String newVideoName = bufferedReader.readLine();
                System.out.println("New video uploaded: " + newVideoName);
                youtubeChannel.notifyChanges(newVideoName);
            }
            else if(pressedNumber == 2){
                System.out.println("Enter name of the new subscriber: ");
                String newSubscriberName = bufferedReader.readLine();
                youtubeChannel.subscribe(new Subscriber(newSubscriberName));
                System.out.println("New subscriber added: " + newSubscriberName);
            }
            else if(pressedNumber == 3){
                System.out.println("Enter name of the subscriber to unsubscribe: ");
                String subscriberName = bufferedReader.readLine();
                for(Observer observer: youtubeChannel.getSubscribersFromName(subscriberName)){
                    youtubeChannel.unSubscribe(observer);
                }
                System.out.println("All the subscribers with name: " + subscriberName + " are unsubscribed");
            }
            else if(pressedNumber == 4){
                System.out.println("Thank you for using this app..!");
                break;
            }
            else{
                System.out.println("Wrong key pressed: " + input + " Exiting...!");
                break;
            }
        }
    }
}
