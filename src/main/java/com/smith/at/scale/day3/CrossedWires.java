package com.smith.at.scale.day3;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CrossedWires {

    public int distanceByFn(List<String> firstInstructionSet, List<String> secondInstructionSet,  BiFunction<Coordiante, Set<Coordiante>, Integer> fn) {
        // Object method pattern
        Computer computer = Computer.compute(firstInstructionSet, secondInstructionSet);
        Set<Coordiante> firstPath = computer.getFirstInstructionSet();
        Set<Coordiante> secondPath = computer.getSecondInstructionSet();

        // @ToCheck
        Set<Coordiante> copy = new HashSet<>(firstPath);
        copy.retainAll(secondPath);

        return copy.stream()
                .map(c -> fn.apply(c, secondPath))
                .min(Integer::compareTo)
                .orElse(0);
    }

    public int distanceBySteps(List<String> firstInstructionSet, List<String> secondInstructionSet) {
        return distanceByFn(firstInstructionSet, secondInstructionSet, totalStepsFromCoordinate());
    }

    public int distance(List<String> firstInstructionSet, List<String> secondInstructionSet) {
        return distanceByFn(firstInstructionSet, secondInstructionSet, distanceFromCoordinate());
    }

    private BiFunction<Coordiante, Set<Coordiante>, Integer> totalStepsFromCoordinate() {
        return (c, secondPath) -> {
            // @Ugly
            Function<Coordiante, Coordiante> getter = (Coordiante c2) -> secondPath.stream().filter(c2::equals).findFirst().get();
            return c.getTotalSteps() + getter.apply(c).getTotalSteps();
        };
    }

    private BiFunction<Coordiante, Set<Coordiante>, Integer> distanceFromCoordinate() {
        return (c, secondPath) -> c.getDistance();
    }

    static class Coordiante {
        private final int x;
        private final int y;
        private final int totalSteps;

        Coordiante(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.totalSteps = steps;
        }

        public int getDistance() {
            return Math.abs(x) + Math.abs(y);
        }

        public int getTotalSteps() {
            return totalSteps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordiante that = (Coordiante) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


}
