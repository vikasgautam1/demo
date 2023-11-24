package com.example.demo.principle.ocp.violation;

import java.util.List;

public class Attacker {
    public void attackWithSuperHeroes(List<SuperHero> superHeroes){
        for(SuperHero superHero: superHeroes){
            superHero.attack();
        }
    }
}
