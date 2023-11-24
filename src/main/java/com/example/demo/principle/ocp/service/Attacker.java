package com.example.demo.principle.ocp.service;

import com.example.demo.principle.ocp.handler.SuperHero;

import java.util.List;

public class Attacker {
    public void attackWithSuperHeroes(List<SuperHero> superHeroes){
        for(SuperHero superHero: superHeroes){
            superHero.attack();
        }
    }
}
