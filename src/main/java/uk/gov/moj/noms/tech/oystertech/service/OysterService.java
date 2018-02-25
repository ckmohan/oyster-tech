package uk.gov.moj.noms.tech.oystertech.service;


import uk.gov.moj.noms.tech.oystertech.domain.TubeStation;
import uk.gov.moj.noms.tech.oystertech.exception.OysterNotFoundException;
import uk.gov.moj.noms.tech.oystertech.repositories.OysterEntity;
import uk.gov.moj.noms.tech.oystertech.repositories.OysterRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OysterService {

    private static final int BUS_FARE = -180;

    @Autowired
    private OysterRepository repository;

    @Autowired
    private TubeFareCalculatorService calculatorService;

    @Autowired
    private OysterConverter oysterConverter;

    public void newOyster(final UUID oysterId) {
        repository.save(new OysterEntity(oysterId));
    }

    public Oyster getOyster(final UUID oysterId) throws OysterNotFoundException {
        return oysterConverter.convert(findOyster(oysterId));
    }

    public void tapTube(final UUID oysterId, final TubeStation tubeStation) throws OysterNotFoundException {

        updateOyster(oysterId, oyster -> {
            final int fare = calculatorService.calculateFare(oyster.getTubeStation(), tubeStation);
            oyster.setTubeStation(oyster.getTubeStation() != null ? null : tubeStation);
            oyster.setBalance(oyster.getBalance() + fare);
        });
    }

    public void tapBus(final UUID oysterId) throws OysterNotFoundException {
        updateOyster(oysterId, oyster -> oyster.setBalance(oyster.getBalance() + BUS_FARE));
    }

    private OysterEntity updateOyster(final UUID oysterId, final Consumer<OysterEntity> consumer) throws OysterNotFoundException {
        final OysterEntity oyster = findOyster(oysterId);
        consumer.accept(oyster);
        repository.save(oyster);
        return oyster;
    }

    private OysterEntity findOyster(final UUID oysterId) throws OysterNotFoundException {
        return Optional.ofNullable(repository.findOne(oysterId))
                .orElseThrow(() -> new OysterNotFoundException("Cannot find oyster."));
    }
}
