package uk.gov.moj.noms.tech.oystertech.web;

import uk.gov.moj.noms.tech.oystertech.domain.TubeStation;
import uk.gov.moj.noms.tech.oystertech.exception.OysterNotFoundException;
import uk.gov.moj.noms.tech.oystertech.service.Oyster;
import uk.gov.moj.noms.tech.oystertech.service.OysterService;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/oyster")
public class OysterResource {

    @Autowired
    private OysterService oysterService;

    @RequestMapping(value = "/{oysterId}", method = RequestMethod.POST)
    public ResponseEntity<?> newOyster(@PathVariable final String oysterId) {
        oysterService.newOyster(UUID.fromString(oysterId));
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/{oysterId}/tap/tube/{stationId}", method = RequestMethod.POST)
    public ResponseEntity<?> tapTube(@PathVariable final String oysterId, @PathVariable final String stationId) throws OysterNotFoundException {
        oysterService.tapTube(UUID.fromString(oysterId), TubeStation.findByName(stationId));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{oysterId}/tap/bus", method = RequestMethod.POST)
    public ResponseEntity<?> tapBus(@PathVariable final String oysterId) throws OysterNotFoundException {
        oysterService.tapBus(UUID.fromString(oysterId));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{oysterId}", method = RequestMethod.GET)
    public ResponseEntity<Oyster> getOyster(@PathVariable final String oysterId) throws OysterNotFoundException {
        return ResponseEntity.ok(oysterService.getOyster(UUID.fromString(oysterId)));
    }
}