package com.smith.at.scale.day6;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class System {
    private final Map<String, Planet> system = new HashMap<>();

    public Planet getOrCreate(String name) {
        if (!system.containsKey(name)) {
            Planet newPlanet = new Planet(name);
            system.put(name, newPlanet);
            return newPlanet;
        } else {
            return system.get(name);
        }
    }

    public Optional<Planet> get(String name) {
        return Optional.ofNullable(system.get(name));
    }
}
