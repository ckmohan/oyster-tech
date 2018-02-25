package uk.gov.moj.noms.tech.oystertech.domain;

public enum TubeFare {

    MAX_FARE(320),
    SAME_ZONE_IN_ZONE_ONE(250),
    SAME_ZONE_OUTSIDE_ZONE_ONE(200),
    ANY_TWO_ZONES_INSIDE_ZONE_ONE(300),
    ANY_TWO_ZONES_OUTSIDE_ZONE_ONE(225);

    private final int fare;

    TubeFare(final int fare) {
        this.fare = fare;
    }

    public int getFare() {
        return fare;
    }
}