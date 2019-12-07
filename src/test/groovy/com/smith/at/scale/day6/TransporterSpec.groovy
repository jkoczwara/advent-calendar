package com.smith.at.scale.day6

import com.smith.at.scale.CalendarSpecification

class TransporterSpec extends CalendarSpecification {

    def "NumberOfHops"() {
        given:
        def inputString = "    COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L\n" +
                "K)YOU\n" +
                "I)SAN"
        List<String> input = inputString.split("\\n") as List<String>
        System system = new Parser().parse(input)

        expect:
        new Transporter().numberOfHops(system, "YOU", "SAN") == 4
    }

    def 'resolve puzzle'() {
        given:
        def inputString = readFromFile('/day6-part2.txt')
        List<String> input = inputString.split("\\n") as List<String>
        System system = new Parser().parse(input)

        expect:
        new Transporter().numberOfHops(system, "YOU", "SAN") == 391
    }
}
