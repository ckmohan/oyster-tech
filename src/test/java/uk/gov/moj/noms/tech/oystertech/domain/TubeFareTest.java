package uk.gov.moj.noms.tech.oystertech.domain;

import static org.junit.Assert.assertEquals;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.ANY_TWO_ZONES_INSIDE_ZONE_ONE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.ANY_TWO_ZONES_OUTSIDE_ZONE_ONE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.MAX_FARE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.SAME_ZONE_IN_ZONE_ONE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.SAME_ZONE_OUTSIDE_ZONE_ONE;

import org.junit.Test;

public class TubeFareTest {

    @Test
    public void shouldGetResultSetCodeByIndex() {
        assertEquals(MAX_FARE.getFare(), 320);
        assertEquals(SAME_ZONE_IN_ZONE_ONE.getFare(), 250);
        assertEquals(SAME_ZONE_OUTSIDE_ZONE_ONE.getFare(), 200);
        assertEquals(ANY_TWO_ZONES_INSIDE_ZONE_ONE.getFare(), 300);
        assertEquals(ANY_TWO_ZONES_OUTSIDE_ZONE_ONE.getFare(), 225);
    }
}