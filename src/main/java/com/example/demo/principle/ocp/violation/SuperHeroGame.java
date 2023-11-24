package com.example.demo.principle.ocp.violation;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroGame {

    public void start(){
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes.add(new SuperMan());
        superHeroes.add(new BatMan());
        superHeroes.add(new CaptainAmerica());
        superHeroes.add(new BatMan());
        Attacker attacker = new Attacker();
        attacker.attackWithSuperHeroes(superHeroes);
    }
}
