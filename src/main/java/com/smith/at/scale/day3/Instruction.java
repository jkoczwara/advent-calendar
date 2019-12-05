package com.smith.at.scale.day3;

class Instruction {
    private enum Direction {
        Right, Left, Up, Down
    }

    private final Direction direction;
    private final int distance;

    private Instruction(Direction direction, int distance) {
        this.direction = direction;
        this.distance = distance;
    }

    // Utils
    boolean isRight() {
        return direction.equals(Direction.Right);
    }
    boolean isLeft() {
        return direction.equals(Direction.Left);
    }
    boolean isUp() {
        return direction.equals(Direction.Up);
    }
    boolean isDown() {
        return direction.equals(Direction.Down);
    }

    // Factories
    static Instruction right(String distance) {
        int parsed = Integer.parseInt(distance);
        return new Instruction(Direction.Right, parsed);
    }

    static Instruction left(String distance) {
        int parsed = Integer.parseInt(distance);
        return new Instruction(Direction.Left, parsed);
    }

    static Instruction up(String distance) {
        int parsed = Integer.parseInt(distance);
        return new Instruction(Direction.Up, parsed);
    }

    static Instruction down(String distance) {
        int parsed = Integer.parseInt(distance);
        return new Instruction(Direction.Down, parsed);
    }

    public int getDistance() {
        return distance;
    }
}
