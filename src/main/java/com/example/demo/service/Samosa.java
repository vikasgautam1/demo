package com.example.demo.service;

import java.io.Serializable;

public class Samosa implements Serializable, Cloneable {

    // Lazy Way of creating Singleton object
    private static Samosa samosa;

    private Samosa(){
        // Reflection api solution:
//        if(samosa != null){
//            throw new RuntimeException("Trying to break singleton design pattern");
//        }
    }

    public static Samosa getSamosa(){
        if(samosa == null){
            synchronized (Samosa.class){
                if(samosa == null) {
                    samosa = new Samosa();
                }
            }
        }
        return samosa;
    }

    // Deserialization Solution:
//    public Object readResolve(){
//        return samosa;
//    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
        // Cloning solution:
//        return samosa;
    }
}
