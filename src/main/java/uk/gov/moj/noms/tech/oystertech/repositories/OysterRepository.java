package uk.gov.moj.noms.tech.oystertech.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OysterRepository extends CrudRepository<OysterEntity, UUID> {

}
