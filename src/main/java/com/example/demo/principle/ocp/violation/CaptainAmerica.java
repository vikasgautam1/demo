package com.example.demo.principle.ocp.violation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CaptainAmerica implements SuperHero {
    @Override
    public void attack() {
        log.info("CaptainAmerica attacking...");
    }
}
