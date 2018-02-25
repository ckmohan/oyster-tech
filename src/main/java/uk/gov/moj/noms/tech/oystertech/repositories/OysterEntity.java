package uk.gov.moj.noms.tech.oystertech.repositories;


import uk.gov.moj.noms.tech.oystertech.domain.TubeStation;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class OysterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TubeStation tubeStation;

    @Column(nullable = false)
    private int balance;

    protected OysterEntity() {
    }

    public OysterEntity(final UUID id) {
        this.id = id;
        this.balance = 3000;
    }

    public UUID getId() {
        return id;
    }

    public TubeStation getTubeStation() {
        return tubeStation;
    }

    public void setTubeStation(final TubeStation tubeStation) {
        this.tubeStation = tubeStation;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }
}