package com.example.demo.controller;

import com.example.demo.config.EnvironmentModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    EnvironmentModifier environmentModifier;

    @Autowired
    Environment environment;

    @GetMapping("/prop-update")
    public String getUpdatedProperty(@RequestParam String key){
        environmentModifier.setEnvironmentProperty("test.prop", key);
        return environment.getProperty("test.prop");
    }
}
