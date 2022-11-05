package com.inditex.hiring.unitary.application.rest;

import static com.inditex.hiring.utils.DataOfferUtils.BRAND_ID;
import static com.inditex.hiring.utils.DataOfferUtils.END_DATE;
import static com.inditex.hiring.utils.DataOfferUtils.OFFER_ID_ONE;
import static com.inditex.hiring.utils.DataOfferUtils.OFFER_ID_TWO;
import static com.inditex.hiring.utils.DataOfferUtils.ONE;
import static com.inditex.hiring.utils.DataOfferUtils.PART_NUMBER;
import static com.inditex.hiring.utils.DataOfferUtils.PRIORITY_ZERO;
import static com.inditex.hiring.utils.DataOfferUtils.START_DATE;
import static com.inditex.hiring.utils.DataOfferUtils.TEN;
import static com.inditex.hiring.utils.DataOfferUtils.createDefaultOffer;
import static com.inditex.hiring.utils.DataOfferUtils.createDefaultOfferRequestDTO;
import static com.inditex.hiring.utils.DataOfferUtils.createDefaultOfferValidity;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.hiring.application.request.OfferRequestDTO;
import com.inditex.hiring.domain.Offer;
import com.inditex.hiring.domain.OfferValidity;
import com.inditex.hiring.domain.service.OfferService;
import com.inditex.hiring.utils.TestUtils;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class OfferControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OfferService offerService;

  @Before
  public void setup() {
  }

  @Test
  public void createNewOfferTest() throws Exception {
    Mockito.doNothing().when(offerService).create(Mockito.any(Offer.class));

    OfferRequestDTO offer = createDefaultOfferRequestDTO(OFFER_ID_ONE, START_DATE, END_DATE);

    mockMvc.perform(post("/offer")
            .content(TestUtils.asJsonString(offer))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void deleteAllOffersTest() throws Exception {
    Mockito.doNothing().when(offerService).deleteAll();

    mockMvc.perform(delete("/offer"))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteOfferByIdTest() throws Exception {
    Mockito.doNothing().when(offerService).deleteById(Mockito.anyLong());

    mockMvc.perform(delete("/offer/" + OFFER_ID_ONE))
        .andExpect(status().isOk());
  }

  @Test
  public void getAllOfferTest() throws Exception {

    Offer offer = createDefaultOffer(OFFER_ID_ONE, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Offer offer2 = createDefaultOffer(OFFER_ID_TWO, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Mockito.when(offerService.getAll()).thenReturn(Arrays.asList(offer, offer2));

    mockMvc.perform(get("/offer/")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].offerId", equalTo((int) OFFER_ID_ONE)))
        .andExpect(jsonPath("$[1].offerId", equalTo((int) (OFFER_ID_TWO))));
  }

  @Test
  public void getOfferByIdTest() throws Exception {

    Offer offer = createDefaultOffer(OFFER_ID_ONE, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Mockito.when(offerService.getById(Mockito.anyLong())).thenReturn(offer);

    mockMvc.perform(get("/offer/" + OFFER_ID_ONE)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.offerId", equalTo((int) OFFER_ID_ONE)));

  }

  @Test
  public void getOfferByPartNumberTest() throws Exception {

    OfferValidity offer = createDefaultOfferValidity(START_DATE, END_DATE, BigDecimal.TEN);
    OfferValidity offer2 = createDefaultOfferValidity(START_DATE, END_DATE, BigDecimal.ONE);

    Mockito.when(offerService.getByPartNumber(Mockito.anyInt(), Mockito.anyString()))
        .thenReturn(Arrays.asList(offer, offer2));

    mockMvc.perform(get("/brand/"+BRAND_ID+"/partnumber/"+ PART_NUMBER +"/offer")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].price", equalTo(TEN)))
        .andExpect(jsonPath("$[1].price", equalTo(ONE)));

  }


}
