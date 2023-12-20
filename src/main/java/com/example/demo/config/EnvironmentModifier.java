package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EnvironmentModifier {

    private final ConfigurableEnvironment environment;

    @Autowired
    public EnvironmentModifier(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    public void setEnvironmentProperty(String propertyName, String propertyValue) {
        // Create or update the property using MapPropertySource
        Map<String, Object> properties = new HashMap<>();
        properties.put(propertyName, propertyValue);

        MapPropertySource propertySource = new MapPropertySource("dynamicPropertySource", properties);
        environment.getPropertySources().addFirst(propertySource);
    }
}

