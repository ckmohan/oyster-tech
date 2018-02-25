package uk.gov.moj.noms.tech.oystertech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OysterNotFoundException extends Exception {

    public OysterNotFoundException(final String message) {
        super(message);
    }

}
