package com.example.demo;

import com.example.demo.config.EnvironmentModifier;
import com.example.demo.principle.lsp.violation.SalaryDisburserSimulator;
import com.example.demo.principle.ocp.violation.SuperHeroGame;
import com.example.demo.service.PartitionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	Environment env;

	@Autowired
	EnvironmentModifier environmentModifier;

	public static void main(String[] args) throws JsonProcessingException, ParseException {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Everything is working fine...!");
		System.out.println(getDateWithHoursBuffer(-24));
		ocpSuperHeroGame();
		lspSalaryDisburserSimulator();
		//PartitionService.generatePartitions();
	}

	public static Date getDateWithHoursBuffer(Integer hours){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, hours);
		return c.getTime();
	}

	private static void ocpSuperHeroGame(){
		SuperHeroGame superHeroGame = new SuperHeroGame();
		superHeroGame.start();
	}

	private static void lspSalaryDisburserSimulator(){
		SalaryDisburserSimulator salaryDisburserSimulator = new SalaryDisburserSimulator();
		salaryDisburserSimulator.simulate();
	}

	@PostConstruct
	public void run(){
		System.out.println("Running post construct method...");
		System.out.println(env.getProperty("test.prop"));
		environmentModifier.setEnvironmentProperty("test.prop", "test2");
		System.out.println("Prop after update: " + env.getProperty("test.prop"));
	}

}
