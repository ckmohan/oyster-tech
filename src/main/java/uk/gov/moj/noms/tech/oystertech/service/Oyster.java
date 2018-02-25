package uk.gov.moj.noms.tech.oystertech.service;

import java.util.UUID;

public class Oyster {

    private final UUID id;
    private final String balance;

    public Oyster(final UUID id, final String balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }
}