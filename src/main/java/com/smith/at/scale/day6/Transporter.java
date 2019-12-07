package com.smith.at.scale.day6;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Transporter {

    public int numberOfHops(System system, String from, String to) {
        List<Planet> pathFromStartingPlanet = system.get(from).map(this::findPathToStart)
                .orElseThrow(fail(from));
        List<Planet> pathFromTargetPlanet = system.get(to).map(this::findPathToStart)
                .orElseThrow(fail(to));

        List<Planet> commonPath = findCommonPath(pathFromStartingPlanet, pathFromTargetPlanet);

        return countHops(pathFromStartingPlanet, pathFromTargetPlanet, commonPath);
    }

    private int countHops(List<Planet> pathFromStartingPlanet, List<Planet> pathFromTargetPlanet, List<Planet> commonPath) {
        int startAndEnd = 2;
        return pathFromStartingPlanet.size() + pathFromTargetPlanet.size() - 2 * commonPath.size() - startAndEnd;
    }

    private List<Planet> findCommonPath(List<Planet> pathFromStartingPlanet, List<Planet> pathFromTargetPlanet) {
        return pathFromStartingPlanet.stream().filter(s -> pathFromTargetPlanet.stream().anyMatch(s2 -> s2.getName().equals(s.getName()))).collect(Collectors.toList());
    }

    private List<Planet> findPathToStart(Planet destinationPlanet) {
        if (destinationPlanet.getParentPlanet().getName().equals(Explorer.STARTING_PLANET)) {
            return Collections.singletonList(destinationPlanet.getParentPlanet());
        } else {
            List<Planet> recursionResult = findPathToStart(destinationPlanet.getParentPlanet());
            return Stream.concat(      // Stream(1-element ++ n-elements)
                    Stream.of(destinationPlanet), // Stream(1-element)
                    recursionResult.stream())     // Stream(n-elements)
                .collect(Collectors.toList());
        }
    }

    private Supplier<IllegalArgumentException> fail(String reason) {
        return () -> new IllegalArgumentException(String.format("Planet '%s' not found", reason));
    }
}
