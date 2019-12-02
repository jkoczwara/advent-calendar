package com.smith.at.scale.day2

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class IntcodeProgramSpec extends Specification {

    /*
    Here are the initial and final states of a few more small programs:

    1,0,0,0,99 becomes          2,0,0,0,99 (1 + 1 = 2).
    2,3,0,3,99 becomes          2,3,0,6,99 (3 * 2 = 6).
    2,4,4,5,99,0 becomes        2,4,4,5,99,9801 (99 * 99 = 9801).
    1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
     */
    @Subject
    def intcodeProgram = new IntcodeProgram()

    @Unroll
    def '#input becomes #output'() {
        expect:
        intcodeProgram.execute(input) == output

        where:
        input || output
        [1,0,0,0,99] || [2,0,0,0,99]
        [2,3,0,3,99] || [2,3,0,6,99]
        [2,4,4,5,99,0] || [2,4,4,5,99,9801]
        [1,1,1,4,99,5,6,0,99] || [30,1,1,4,2,5,6,0,99]
    }

    @Unroll
    def '#input should fail'() {
        when:
        intcodeProgram.execute(input)

        then:
        thrown(IllegalArgumentException.class)

        where:
        input | _
        []  | _
        [2] | _
        [2,4,4] | _
        [3,1,1,4,99,5,6,0,99] | _
    }

    def 'resolve puzzle'() {
        given:
        def input = givenHardProblem()
        /*
        To do this, before running the program, replace position 1 with the value 12
        and replace position 2 with the value 2
         */
        input[1] = 12
        input[2] = 2

        expect:
        intcodeProgram.execute(input) == [3895705, 12, 2, 2, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 36, 1, 19, 5, 37, 2, 23, 13, 185, 1, 10, 27, 189, 2, 31, 6, 378, 1, 5, 35, 379, 1, 39, 10, 383, 2, 9, 43, 1149, 1, 47, 5, 1150, 2, 51, 9, 3450, 1, 13, 55, 3455, 1, 13, 59, 3460, 1, 6, 63, 3462, 2, 13, 67, 17310, 1, 10, 71, 17314, 2, 13, 75, 86570, 1, 5, 79, 86571, 2, 83, 9, 259713, 2, 87, 13, 1298565, 1, 91, 5, 1298566, 2, 9, 95, 3895698, 1, 99, 5, 3895699, 1, 2, 103, 3895701, 1, 10, 107, 0, 99, 2, 14, 0, 0]
    }

    def 'resolve puzzle with given output'() {
        expect:
        hackItRandom() == [19690720, 64, 17, 2, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 192, 1, 19, 5, 193, 2, 23, 13, 965, 1, 10, 27, 969, 2, 31, 6, 1938, 1, 5, 35, 1939, 1, 39, 10, 1943, 2, 9, 43, 5829, 1, 47, 5, 5830, 2, 51, 9, 17490, 1, 13, 55, 17495, 1, 13, 59, 17500, 1, 6, 63, 17502, 2, 13, 67, 87510, 1, 10, 71, 87514, 2, 13, 75, 437570, 1, 5, 79, 437571, 2, 83, 9, 1312713, 2, 87, 13, 6563565, 1, 91, 5, 6563566, 2, 9, 95, 19690698, 1, 99, 5, 19690699, 1, 2, 103, 19690716, 1, 10, 107, 0, 99, 2, 14, 0, 0]
    }

    def hackIt() {
        def original = givenHardProblem()
        for (i in 0..99) {
            for (j in 0..99) {
                def input = original.collect()

                input[1] = i
                input[2] = j
                def result = intcodeProgram.execute(input)
                if (result[0] == 19690720) {
                    return result
                }
            }
        }
        assert false : "Sad Panda :("
    }

    def hackItRandom() {
        def original = givenHardProblem()
        def random = new Random()
        while(true) {
            def input = original.collect()

            input[1] = random.nextInt(100)
            input[2] = random.nextInt(100)
            def result = intcodeProgram.execute(input)
            if (result[0] == 19690720) {
                return result
            }
        }
    }

    def givenHardProblem() {
        this.getClass()
                .getResource('/input-day-2.txt')
                .text
                .split(",")
                .collect { it as Integer }
    }

}
