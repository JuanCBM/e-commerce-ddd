package com.inditex.hiring.utils;

import com.inditex.hiring.application.request.OfferRequestDTO;
import com.inditex.hiring.domain.Offer;
import com.inditex.hiring.domain.OfferValidity;
import java.math.BigDecimal;

public class DataOfferUtils {

  public static final String START_DATE = "2020-06-14T00.00.00Z";
  public static final String END_DATE = "2020-12-31T23.59.59Z";
  public static final long OFFER_ID_ONE = 1L;
  public static final long OFFER_ID_TWO = 2L;
  public static final long BRAND_ID = 1L;
  public static final int PRIORITY_ZERO = 0;

  public static final String PART_NUMBER = "0001002";

  public static final int ONE = 1;
  public static final int TEN = 10;

  public static OfferRequestDTO createDefaultOfferRequestDTO(Long offerId, String startDate,
      String endDate) {
    OfferRequestDTO offer = new OfferRequestDTO();
    offer.setOfferId(offerId);
    offer.setBrandId(1);
    offer.setStartDate(startDate);
    offer.setEndDate(endDate);
    offer.setPriceListId(1L);
    offer.setProductPartnumber("0001002");
    offer.setPriority(0);
    offer.setPrice(BigDecimal.TEN);
    offer.setCurrencyIso("EUR");
    return offer;
  }

  public static Offer createDefaultOffer(Long offerId, String startDate, String endDate,
      Integer priority, BigDecimal price) {
    Offer offer = new Offer();
    offer.setOfferId(offerId);
    offer.setBrandId(1);
    offer.setStartDate(DateUtils.toLocalDateTime(startDate));
    offer.setEndDate(DateUtils.toLocalDateTime(endDate));
    offer.setPriceListId(1L);
    offer.setProductPartnumber("0001002");
    offer.setPriority(priority);
    offer.setPrice(price);
    offer.setCurrencyIso("EUR");

    return offer;
  }

  public static OfferValidity createDefaultOfferValidity(String startDate, String endDate,
      BigDecimal price) {
    OfferValidity offer = new OfferValidity();
    offer.setStartDate(DateUtils.toLocalDateTime(startDate));
    offer.setEndDate(DateUtils.toLocalDateTime(endDate));
    offer.setPrice(price);
    offer.setCurrencyIso("EUR");

    return offer;
  }
}