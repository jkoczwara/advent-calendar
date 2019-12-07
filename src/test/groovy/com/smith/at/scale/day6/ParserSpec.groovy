package com.smith.at.scale.day6

import com.smith.at.scale.CalendarSpecification
import spock.lang.Specification
import spock.lang.Subject

class ParserSpec extends CalendarSpecification {

    @Subject
    Parser underTest = new Parser()

    def 'parsers creates system'() {
        given:
        def inputString = "    COM)B\n" +
                "    B)C\n" +
                "    C)D\n" +
                "    D)E\n" +
                "    E)F\n" +
                "    B)G\n" +
                "    G)H\n" +
                "    D)I\n" +
                "    E)J\n" +
                "    J)K\n" +
                "    K)L"
        List<String> input = inputString.split("\\n") as List<String>

        when:
        System result = underTest.parse(input)

        then:
        thenPlanetHasNumberOfOrbiters(result, 'COM', 1)
        thenPlanetHasNumberOfOrbiters(result, 'B', 2)
    }

    def 'parses puzzle'() {
        given:
        def inputString = readFromFile('/day6.txt')
        List<String> input = inputString.split("\\n") as List<String>

        when:
        System result = underTest.parse(input)

        then:
        result.get("COM").isPresent()
    }

    private static boolean thenPlanetHasNumberOfOrbiters(System result, String name, int numberOfPlanets) {
        def com = result.get(name)
        return com.isPresent() && com.get().orbiters.size() == numberOfPlanets
    }


}
