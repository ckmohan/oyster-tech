package uk.gov.moj.noms.tech.oystertech.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.moj.noms.tech.oystertech.domain.TubeStation;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OysterRepositoryTest {

    private static final UUID OYSTER_ID = UUID.randomUUID();

    @Autowired
    private OysterRepository oysterRepository;

    @Before
    public void setUp() throws Exception {
        final OysterEntity oysterEntity = new OysterEntity(OYSTER_ID);
        oysterEntity.setBalance(30);
        oysterEntity.setTubeStation(TubeStation.HOLBORN);
        oysterRepository.save(oysterEntity);
    }

    @Test
    public void shouldFindOneRecord() {

        OysterEntity repositoryOne = oysterRepository.findOne(OYSTER_ID);

        assertThat(repositoryOne.getId(), is(OYSTER_ID));
        assertThat(repositoryOne.getTubeStation(), is(TubeStation.HOLBORN));
        assertThat(repositoryOne.getBalance(), is(30));
    }
}