package uk.gov.moj.noms.tech.oystertech.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import uk.gov.moj.noms.tech.oystertech.repositories.OysterEntity;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class OysterConverterTest {

    private static final UUID OYSTER_ID = UUID.randomUUID();

    @InjectMocks
    private OysterConverter oysterConverter;

    @Mock
    private OysterEntity oysterEntity;

    @Test
    public void shouldConvertOysterEntityToOysterDomain() {
        when(oysterEntity.getBalance()).thenReturn(100);
        when(oysterEntity.getId()).thenReturn(OYSTER_ID);

        final Oyster oyster = oysterConverter.convert(oysterEntity);

        Assert.assertThat(oyster.getBalance(), is("1.00"));
        Assert.assertThat(oyster.getId(), is(OYSTER_ID));

    }
}