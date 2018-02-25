package uk.gov.moj.noms.tech.oystertech.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import uk.gov.moj.noms.tech.oystertech.exception.OysterNotFoundException;
import uk.gov.moj.noms.tech.oystertech.service.Oyster;
import uk.gov.moj.noms.tech.oystertech.service.OysterService;

import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OysterResource.class, secure = false)
public class OysterResourceTest {


    private static final UUID OYSTER_ID = UUID.fromString("fe722e7a-8bb6-42d4-9ad3-f8e615a8f3f9");

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private OysterResource oysterResource;

    @MockBean
    private OysterService oysterService;

    @Test
    public void shouldGetOysterDetails() throws Exception {

        final Oyster oyster = new Oyster(OYSTER_ID, "3000");
        when(oysterService.getOyster(OYSTER_ID)).thenReturn(oyster);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/oyster/" + OYSTER_ID.toString()).accept(
                MediaType.APPLICATION_JSON);

        final MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        final String expected = "{\"id\":\"fe722e7a-8bb6-42d4-9ad3-f8e615a8f3f9\",\"balance\":\"3000\"}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void shouldAddNewOysterDetails() throws Exception {

        final Oyster oyster = new Oyster(OYSTER_ID, "3000");
        when(oysterService.getOyster(OYSTER_ID)).thenReturn(oyster);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/oyster/" + OYSTER_ID.toString()).accept(
                MediaType.APPLICATION_JSON);

        final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertThat(result.getResponse().getStatus(), is(Response.SC_CREATED));
        verify(oysterService).newOyster(OYSTER_ID);

    }

    @Test
    public void shouldChargeBusFare() throws Exception {

        final Oyster oyster = new Oyster(OYSTER_ID, "3000");
        when(oysterService.getOyster(OYSTER_ID)).thenReturn(oyster);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/oyster/" + OYSTER_ID.toString() + "/tap/bus").accept(
                MediaType.APPLICATION_JSON);

        final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertThat(result.getResponse().getStatus(), is(Response.SC_NO_CONTENT));
        verify(oysterService).tapBus(OYSTER_ID);

    }

    @RequestMapping(value = "/{oysterId}/tap/bus", method = RequestMethod.POST)
    public ResponseEntity<?> tapBus(@PathVariable final String oysterId) throws OysterNotFoundException {
        oysterService.tapBus(UUID.fromString(oysterId));
        return ResponseEntity.noContent().build();
    }

}