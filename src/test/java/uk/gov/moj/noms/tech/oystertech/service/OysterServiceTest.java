package uk.gov.moj.noms.tech.oystertech.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import uk.gov.moj.noms.tech.oystertech.exception.OysterNotFoundException;
import uk.gov.moj.noms.tech.oystertech.repositories.OysterEntity;
import uk.gov.moj.noms.tech.oystertech.repositories.OysterRepository;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class OysterServiceTest {

    private static final UUID OYSTER_ID = UUID.randomUUID();

    @InjectMocks
    private OysterService oysterService;

    @Mock
    private OysterConverter oysterConverter;

    @Mock
    private OysterRepository repository;

    @Mock
    private OysterEntity entity;

    @Mock
    private Oyster oyster;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Test
    public void shouldThrowOysterNotFoundException() throws OysterNotFoundException {

        when(oysterConverter.convert(entity)).thenReturn(oyster);
        when(repository.findOne(OYSTER_ID)).thenReturn(null);

        expectedException.expect(OysterNotFoundException.class);
        expectedException.expectMessage("Cannot find oyster.");

        oysterService.getOyster(OYSTER_ID);

    }


    @Test
    public void shouldFindOyster() throws OysterNotFoundException {

        when(repository.findOne(OYSTER_ID)).thenReturn(entity);
        when(oysterConverter.convert(entity)).thenReturn(oyster);

        final Oyster actualOyster = oysterService.getOyster(OYSTER_ID);

        Assert.assertThat(actualOyster, is(oyster));
    }

}