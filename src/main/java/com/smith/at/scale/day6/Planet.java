package com.smith.at.scale.day6;

import java.util.*;

class Planet {

    private final String name;
    private Planet parentPlanet;
    private int seqNumber;
    private final List<Planet> orbiters = new LinkedList<>();

    public Planet(String name) {
        this.name = name;
    }

    public void addOrbiter(Planet orbiter) {
        this.orbiters.add(orbiter);
        orbiter.setParentPlanet(this);
    }

    private void setParentPlanet(Planet parentPlanet) {
        this.parentPlanet = parentPlanet;
    }

    public List<Planet> getOrbiters() {
        return orbiters;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public int getSeqNumber() {
        return this.seqNumber;
    }

    public Planet getParentPlanet() {
        return parentPlanet;
    }

    public String getName() {
        return name;
    }
}

