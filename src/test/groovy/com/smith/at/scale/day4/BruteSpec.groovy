package com.smith.at.scale.day4

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class BruteSpec extends Specification {

    @Subject
    def underTest = new BruteForceOne()

    @Unroll
    def '#input password is #state'() {
        expect:
        underTest.check(input) == result

        where:
        input || result | state
        111111 || false | 'incorrect'
        223450 || false | 'incorrect'
        123789 || false | 'incorrect'
        112233 || true | 'correct'
        123444 || false | 'incorrect'
        111122 || true | 'correct'
    }

    def 'resolve puzzle'() {
        given:
        int lowerBound = 152085
        int upperBound = 670283

        expect:
        underTest.force(lowerBound, upperBound).size() == 1196
    }
}
