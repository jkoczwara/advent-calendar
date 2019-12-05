package com.smith.at.scale.day3;

final class InstructionFactory {

    private InstructionFactory() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static Instruction parseCoordinate(String coordinate) {
        if (coordinate.startsWith("R")) {
            return Instruction.right(coordinate.substring(1));
        } else if (coordinate.startsWith("L")) {
            return Instruction.left(coordinate.substring(1));
        } else if (coordinate.startsWith("D")) {
            return Instruction.down(coordinate.substring(1));
        } else if (coordinate.startsWith("U")) {
            return Instruction.up(coordinate.substring(1));
        } else {
            throw new IllegalArgumentException("Cannot parse the coordinate");
        }
    }

}
