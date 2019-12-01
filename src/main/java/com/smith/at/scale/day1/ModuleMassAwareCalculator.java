package com.smith.at.scale.day1;

public class ModuleMassAwareCalculator implements RocketEquationCalculator {


    @Override
    public long calculateFor(long mass) {
        long massOfRequiredFuel = calculate(mass);
        if (massOfRequiredFuel <= 0) {
            return 0;
        } else {
            return (massOfRequiredFuel + calculateFor(massOfRequiredFuel));
        }
    }

    private long calculate(long mass) {

        return Math.floorDiv(mass, DEFAULT_DIVIDER) - DEFAULT_SUBSTRACTOR;

    }
}
