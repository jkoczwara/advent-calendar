package com.smith.at.scale.day2;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

class IntcodeProgram {

    private static final Integer TERMINATING_OPCODE = 99;
    /**
     * 1 - summing
     * 2 - multiplying
     * 99 - terminating
     *
     * @param input non-empty list representing opcodes
     * @return result of interpreter
     */
    public List<Integer> execute(List<Integer> input) {
        Preconditions.checkArgument(input.size() > 0, "Input cannot be empty list!");
        int iterator = 0;

        while(!TERMINATING_OPCODE.equals(input.get(iterator))) {
            Preconditions.checkArgument(input.size() >= iterator + 4);
            List<Integer> sublist = input.subList(iterator, iterator + 4);
            Integer marker = sublist.get(0);
            Integer whereToPlaceResult = sublist.get(3);

            Integer i1 = sublist.get(1);
            Integer i2 = sublist.get(2);
            Integer arg1 = input.get(i1);
            Integer arg2 = input.get(i2);

            oneLoop(marker, arg1, arg2,
                    (Integer result) -> input.set(whereToPlaceResult, result),
                    fail());

            iterator += 4;
        }

        return input;
    }

    private Supplier<RuntimeException> fail() {
        // Same as () -> new IllegalArgumentException("Unknown opcode");
        return new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new IllegalArgumentException("Unknown opcode");
            }
        };
    }

    private void oneLoop(Integer marker, Integer arg1, Integer arg2, Consumer<Integer> onSuccess, Supplier<? extends RuntimeException> onFailure) {
        Integer toSave = Opcodes.get(marker).map(opcode -> opcode.run(arg1, arg2)).orElseThrow(onFailure);
        onSuccess.accept(toSave);
    }

    enum Opcodes {
        SUM(1, Integer::sum),
        MULTIPLY(2, (Integer arg1, Integer arg2) -> arg1 * arg2);
//        DIVIDE(3, (Integer arg1, Integer arg2) -> arg1 / arg2);

        Integer marker;
        Opcode opcode;

        Opcodes(int marker, Opcode op) {
            this.marker = marker;
            this.opcode = op;
        }

        public static Optional<Opcode> get(Integer marker) {
            return Arrays.stream(Opcodes.values())
                    .filter(i -> i.marker.equals(marker))
                    .findFirst()
                    .map(i -> i.opcode);
        }
    }

//    private static class Multiply implements Opcode {
//
//        @Override
//        public Integer run(Integer arg1, Integer arg2) {
//            return arg1 * arg2;
//        }
//    }
//
//    private static class Sum implements Opcode {
//        @Override
//        public Integer run(Integer arg1, Integer arg2) {
//            return arg1 + arg2;
//        }
//    }

    @FunctionalInterface
    interface Opcode {
        Integer run(Integer arg1, Integer arg2);
    }

}
