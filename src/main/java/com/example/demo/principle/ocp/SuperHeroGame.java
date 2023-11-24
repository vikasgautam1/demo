package com.example.demo.principle.ocp;

import com.example.demo.principle.ocp.handler.BatMan;
import com.example.demo.principle.ocp.handler.CaptainAmerica;
import com.example.demo.principle.ocp.handler.SuperHero;
import com.example.demo.principle.ocp.handler.SuperMan;
import com.example.demo.principle.ocp.service.Attacker;

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
