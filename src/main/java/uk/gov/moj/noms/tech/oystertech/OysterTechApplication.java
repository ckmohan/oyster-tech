package uk.gov.moj.noms.tech.oystertech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OysterTechApplication {
    public static void main(String[] args) {
        SpringApplication.run(OysterTechApplication.class, args);
    }
}
