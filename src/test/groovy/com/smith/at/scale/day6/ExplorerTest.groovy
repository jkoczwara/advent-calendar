package com.smith.at.scale.day6

import com.smith.at.scale.CalendarSpecification

class ExplorerTest extends CalendarSpecification {

    def 'counts checksum of system'() {
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
        System system = new Parser().parse(input)

        expect:
        Explorer.explore(system) == 42
    }

    def 'resolve puzzle'() {
        given:
        def inputString = readFromFile('/day6.txt')
        List<String> input = inputString.split("\\n") as List<String>
        System system = new Parser().parse(input)

        expect:
        Explorer.explore(system) == 151345
    }

}
