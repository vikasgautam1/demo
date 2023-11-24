package com.example.demo;

import com.example.demo.principle.ocp.SuperHeroGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Everything is working fine...!");
		System.out.println(getDateWithHoursBuffer(-24));
		superHeroGame();
	}

	public static Date getDateWithHoursBuffer(Integer hours){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, hours);
		return c.getTime();
	}

	public static void superHeroGame(){
		SuperHeroGame superHeroGame = new SuperHeroGame();
		superHeroGame.start();
	}

}
