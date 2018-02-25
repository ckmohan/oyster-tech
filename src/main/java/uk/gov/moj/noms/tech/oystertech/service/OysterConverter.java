package uk.gov.moj.noms.tech.oystertech.service;


import uk.gov.moj.noms.tech.oystertech.repositories.OysterEntity;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class OysterConverter {

    public Oyster convert(final OysterEntity entity) {
        final BigDecimal balance = new BigDecimal(entity.getBalance())
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);

        return new Oyster(entity.getId(), balance.toString());
    }

}
