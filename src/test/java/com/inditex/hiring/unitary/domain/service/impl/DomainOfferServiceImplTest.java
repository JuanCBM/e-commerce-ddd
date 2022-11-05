package com.inditex.hiring.unitary.domain.service.impl;

import static com.inditex.hiring.utils.DataOfferUtils.BRAND_ID;
import static com.inditex.hiring.utils.DataOfferUtils.END_DATE;
import static com.inditex.hiring.utils.DataOfferUtils.OFFER_ID_ONE;
import static com.inditex.hiring.utils.DataOfferUtils.OFFER_ID_TWO;
import static com.inditex.hiring.utils.DataOfferUtils.PART_NUMBER;
import static com.inditex.hiring.utils.DataOfferUtils.PRIORITY_ZERO;
import static com.inditex.hiring.utils.DataOfferUtils.START_DATE;
import static com.inditex.hiring.utils.DataOfferUtils.createDefaultOffer;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.inditex.hiring.domain.Offer;
import com.inditex.hiring.domain.OfferValidity;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.service.OfferService;
import com.inditex.hiring.domain.service.impl.DomainOfferServiceImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class DomainOfferServiceImplTest {

  @MockBean
  private OfferRepository offerRepository;

  private OfferService domainOfferService;

  @Before
  public void before() {
    this.domainOfferService = new DomainOfferServiceImpl(this.offerRepository);
  }

  @Test
  public void createNewOfferTest() {
    Offer offer = createDefaultOffer(OFFER_ID_ONE, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Mockito.doNothing().when(offerRepository).create(Mockito.any(Offer.class));

    this.domainOfferService.create(offer);

    verify(offerRepository).create(Mockito.any());

  }

  @Test
  public void deleteAllOffersTest() {
    Mockito.doNothing().when(offerRepository).deleteAll();
    this.domainOfferService.deleteAll();

    verify(offerRepository).deleteAll();
  }

  @Test
  public void deleteOfferByIdTest() {
    Mockito.doNothing().when(offerRepository).deleteById(Mockito.anyLong());
    this.domainOfferService.deleteById(OFFER_ID_ONE);

    verify(offerRepository).deleteById(Mockito.anyLong());
  }

  @Test
  public void getAllOfferTest() {
    Offer offer = createDefaultOffer(OFFER_ID_ONE, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Offer offer2 = createDefaultOffer(OFFER_ID_TWO, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Mockito.when(offerRepository.getAll()).thenReturn(Arrays.asList(offer, offer2));

    List<Offer> offers = this.domainOfferService.getAll();
    assertEquals(Arrays.asList(offer, offer2), offers);
  }

  @Test
  public void getOfferByIdTest() {
    Offer offer = createDefaultOffer(OFFER_ID_ONE, START_DATE, END_DATE, PRIORITY_ZERO, BigDecimal.ONE);
    Mockito.when(offerRepository.getById(Mockito.anyLong())).thenReturn(offer);

    Offer offerExecuted = this.domainOfferService.getById(OFFER_ID_ONE);

    assertEquals(offer, offerExecuted);

  }

  @Test
  public void getOfferByPartNumberTest() {
    Offer offer = createDefaultOffer(1L, "2020-06-14T00.00.00Z", "2020-12-31T23.59.59Z", 0, BigDecimal.valueOf(35.50));
    Offer offer2 = createDefaultOffer(2L, "2020-06-14T15.00.00Z", "2020-06-14T18.30.00Z", 1, BigDecimal.valueOf(25.45));
    Offer offer3 = createDefaultOffer(3L, "2020-06-15T00.00.00Z", "2020-06-15T11.00.00Z", 1, BigDecimal.valueOf(30.50));
    Offer offer4 = createDefaultOffer(4L, "2020-06-15T16.00.00Z", "2020-12-31T23.59.59Z", 1, BigDecimal.valueOf(38.95));

    Mockito.when(offerRepository.getByPartNumber(Mockito.anyInt(), Mockito.anyString()))
        .thenReturn(Arrays.asList(offer, offer2, offer3, offer4));

    List<OfferValidity> offerValidities = this.domainOfferService.getByPartNumber((int) BRAND_ID,
        PART_NUMBER);

    assertEquals(6, offerValidities.size());
    assertEquals(BigDecimal.valueOf(35.50), offerValidities.get(0).getPrice());
    assertEquals(BigDecimal.valueOf(25.45), offerValidities.get(1).getPrice());
    assertEquals(BigDecimal.valueOf(35.50), offerValidities.get(2).getPrice());
    assertEquals(BigDecimal.valueOf(30.50), offerValidities.get(3).getPrice());
    assertEquals(BigDecimal.valueOf(35.50), offerValidities.get(4).getPrice());
    assertEquals(BigDecimal.valueOf(38.95), offerValidities.get(5).getPrice());

  }
}
