package com.smith.at.scale.day6;

final class Explorer {

    static final String STARTING_PLANET = "COM";

    private Explorer() {
        // optional throw Error
    }

    public static int explore(System system) {
        Planet starter = system.get(STARTING_PLANET)
                .orElseThrow(() -> new IllegalArgumentException("Starting planet does not exists!"));

        return explorerRecursive(starter, 0);
    }

    private static int explorerRecursive(Planet current, int level) {
        if (current.getOrbiters().size() == 0) {
            return level;
        } else {
            return level + current.getOrbiters().stream()
                    .map((Planet c) -> explorerRecursive(c, level + 1)).mapToInt(Integer::intValue).sum();
        }
    }

}
