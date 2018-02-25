package uk.gov.moj.noms.tech.oystertech.service;


import static java.util.Collections.disjoint;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.ANY_TWO_ZONES_INSIDE_ZONE_ONE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.MAX_FARE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.SAME_ZONE_IN_ZONE_ONE;
import static uk.gov.moj.noms.tech.oystertech.domain.TubeFare.SAME_ZONE_OUTSIDE_ZONE_ONE;

import uk.gov.moj.noms.tech.oystertech.domain.TubeFare;
import uk.gov.moj.noms.tech.oystertech.domain.TubeStation;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

@Service
public class TubeFareCalculatorService {

    public int calculateFare(final TubeStation origin, final TubeStation destination) {
        if (origin == null) {
            return -MAX_FARE.getFare();
        }

        final List<TubeFare> fares = Lists.newArrayList();
        if (!disjoint(origin.getZones(), destination.getZones())) {
            if (origin.getZones().contains(1)) {
                fares.add(SAME_ZONE_IN_ZONE_ONE);
            } else {
                fares.add(SAME_ZONE_OUTSIDE_ZONE_ONE);
            }
        } else {
            fares.add(ANY_TWO_ZONES_INSIDE_ZONE_ONE);
        }

        return MAX_FARE.getFare() - fares.stream().mapToInt(TubeFare::getFare).min().orElse(0);
    }
}
