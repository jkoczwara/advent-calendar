package com.smith.at.scale.day1

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/*
The Elves quickly load you into a spacecraft and prepare to launch.

At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper. They haven't determined the amount of fuel required yet.

Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.

For example:

For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
For a mass of 1969, the fuel required is 654.
For a mass of 100756, the fuel required is 33583.
The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.
 */
class RocketEquation extends Specification {

    @Subject
    private underTest = new TyrranyRocketEquationCalculator()

    @Unroll
    def "#message"() {
        expect:
        underTest.calculateFor(mass) == expectedResult

        where:
        mass   || expectedResult | message
        12     || 2              | 'For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2'
        14     || 2              | 'For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.'
        1969   || 654            | 'For a mass of 1969, the fuel required is 654.'
        100756 || 33583          | 'For a mass of 100756, the fuel required is 33583.'
    }

    def 'resolve puzzle'() {
        given:
        def input = givenMassOfModules()

        expect:
        input.collect { underTest.calculateFor(it as int) }.sum() == 3412496
    }

    def givenMassOfModules() {
        this.getClass()
                .getResource('/input-day-1-for-sure.txt')
                .text
                .split("\n")
                .collect { it as long }
    }

}
