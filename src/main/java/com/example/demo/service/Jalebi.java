package com.example.demo.service;

public class Jalebi {

    // Eager Way of creating Singleton object
    private static Jalebi jalebi = new Jalebi();

    private Jalebi(){

    }

    public static Jalebi getJalebi(){
        if(jalebi == null){
            jalebi = new Jalebi();
        }
        return jalebi;
    }
}
