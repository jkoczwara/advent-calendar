package com.smith.at.scale.day3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Computer {

    private List<String> firstInstructionSet;
    private List<String> secondInstructionSet;

    private Set<CrossedWires.Coordiante> firstPath;
    private Set<CrossedWires.Coordiante> secondPath;

    private Computer(List<String> firstPath, List<String> secondPath) {
        this.firstInstructionSet = firstPath;
        this.secondInstructionSet = secondPath;
        this.invoke();
    }

    public static Computer compute(List<String> firstInstructionSet, List<String> secondInstructionSet) {
        return new Computer(firstInstructionSet, secondInstructionSet);
    }

    public Set<CrossedWires.Coordiante> getSecondInstructionSet() {
        return secondPath;
    }

    public Set<CrossedWires.Coordiante> getFirstInstructionSet() {
        return firstPath;
    }

    private void invoke() {
        // lambda reference
        // this::parseCoordinate
        // <location_of_method>::<name_of_method>
        firstPath = execute(parse(firstInstructionSet));
        secondPath = execute(parse(secondInstructionSet));
    }

    private List<Instruction> parse(List<String> is) {
        return is.stream().map(InstructionFactory::parseCoordinate).collect(Collectors.toList());
    }

    private Set<CrossedWires.Coordiante> execute(List<Instruction> instructions) {
        int x = 0;
        int y = 0;
        Set<CrossedWires.Coordiante> result = new HashSet<>();
        int steps = 0;
        for (Instruction instruction : instructions) {
            if (instruction.isRight()) {
                for(int i = x + 1; i <= x + instruction.getDistance(); i++) {
                    steps++;
                    result.add(new CrossedWires.Coordiante(i, y, steps));
                }
                x = x + instruction.getDistance();
            } else if (instruction.isLeft()) {
                for(int i = x- 1; i >= x - instruction.getDistance(); i--) {
                    steps++;
                    result.add(new CrossedWires.Coordiante(i, y, steps));
                }
                x = x - instruction.getDistance();
            } else if (instruction.isUp()) {
                for(int i = y+ 1; i <= y + instruction.getDistance(); i++) {
                    steps++;
                    result.add(new CrossedWires.Coordiante(x, i, steps));
                }
                y = y + instruction.getDistance();
            } else if (instruction.isDown()) {
                for(int i = y- 1; i >= y - instruction.getDistance(); i--) {
                    steps++;
                    result.add(new CrossedWires.Coordiante(x, i, steps));
                }
                y = y - instruction.getDistance();
            }
        }
        return result;
    }

}