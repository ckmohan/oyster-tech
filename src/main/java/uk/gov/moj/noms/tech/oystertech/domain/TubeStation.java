package uk.gov.moj.noms.tech.oystertech.domain;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum TubeStation {

    HOLBORN("Holborn", ImmutableList.of(1)),
    EARLS_COURT("Earl's Court", ImmutableList.of(1, 2)),
    WIMBLEDON("Wimbledon", ImmutableList.of(3)),
    HAMMERSMITH("Hammersmith", ImmutableList.of(2));

    private final String description;

    private final List<Integer> zones;

    TubeStation(final String description, final List<Integer> zones) {
        this.description = description;
        this.zones = zones;
    }

    public static TubeStation findByName(final String name) {
        return Arrays.stream(TubeStation.values())
                .filter(tubeStation -> tubeStation.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getZones() {
        return zones;
    }
}