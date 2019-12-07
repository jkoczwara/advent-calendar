package com.smith.at.scale.day6;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;

/*
    COM)B
    B)C
    C)D
    D)E
    E)F
    B)G
    G)H
    D)I
    E)J
    J)K
    K)L
 */
class Parser {

    public System parse(List<String> inputs) {
        System system = new System();

        inputs.stream()
                .map(String::trim)
                .map(i -> i.split("\\)"))
                .forEach(i -> {
                    Preconditions.checkArgument(i.length == 2, "Wrong arguments, expected two arguments but got: " + Arrays.toString(i));
                    Planet target = system.getOrCreate(i[0]);
                    Planet orbiter = system.getOrCreate(i[1]);

                    target.addOrbiter(orbiter);
                });

        return system;
    }

}
