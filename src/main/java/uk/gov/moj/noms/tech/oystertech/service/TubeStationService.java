package uk.gov.moj.noms.tech.oystertech.service;



import uk.gov.moj.noms.tech.oystertech.domain.TubeStation;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TubeStationService {

    public Optional<TubeStation> findTubeStation(final String tubeStation) {
        return Arrays.stream(TubeStation.values())
                .filter(value -> value.name().equals(tubeStation))
                .findFirst();
    }
}
