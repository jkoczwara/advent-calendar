package com.smith.at.scale.day1

import spock.lang.Specification
import spock.lang.Unroll

import java.nio.file.Files
import java.nio.file.Paths

class ModuleMassAwareCalculatorTest extends Specification {

    def underTest = new ModuleMassAwareCalculator()

    @Unroll
    def 'for #mass mass should return #expectedFuel fuel'() {
        expect:
        underTest.calculateFor(mass) == expectedFuel

        where:
        mass || expectedFuel
        14 || 2
        1969 || 966
        100756 || 50346

    }

    def 'Resolve puzzle'() {
        given:
        def items = givenMassOfModules()

        when:
        def result = items.collect{ underTest.calculateFor(it)}.sum()

        then:
        result == 5115845
    }

    def givenMassOfModules() {
        this.getClass()
                .getResource('/input-day-1-extra-fuel.txt')
                .text
                .split("\n")
                .collect { it as long }
    }

}
