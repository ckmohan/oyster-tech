package uk.gov.moj.noms.tech.oystertech.domain;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

public class TubeStationTest {

    @Test
    public void shouldReturnHalbornAndZoneIndex() {
        Assert.assertEquals(TubeStation.HOLBORN, TubeStation.findByName("HOLBORN"));
        Assert.assertEquals(TubeStation.HOLBORN.getZones(), ImmutableList.of(1));
    }

    @Test
    public void shouldReturnEarlsCourtAndZoneIndexes() {
        Assert.assertEquals(TubeStation.EARLS_COURT, TubeStation.findByName("EARLS_COURT"));
        Assert.assertEquals(TubeStation.EARLS_COURT.getZones(), ImmutableList.of(1, 2));
    }
}