package com.inditex.hiring.domain.service.impl;

import static com.inditex.hiring.utils.Constants.EUR;
import static com.inditex.hiring.utils.DateUtils.isBetweenRightOpenInterval;

import com.inditex.hiring.domain.Offer;
import com.inditex.hiring.domain.OfferValidity;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.service.OfferService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DomainOfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public DomainOfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public void create(Offer offer) {
    this.offerRepository.create(offer);
  }

  @Override
  public void deleteAll() {
    this.offerRepository.deleteAll();
  }

  @Override
  public void deleteById(Long id) {
    this.offerRepository.deleteById(id);
  }

  @Override
  public List<Offer> getAll() {
    return this.offerRepository.getAll();
  }

  @Override
  public Offer getById(Long id) {
    return this.offerRepository.getById(id);
  }

  @Override
  public List<OfferValidity> getByPartNumber(Integer brandId, String partNumber) {
    List<OfferValidity> offerValidities = new ArrayList<>();

    List<Offer> offers = this.offerRepository.getByPartNumber(brandId, partNumber);

    Set<LocalDateTime> localDateTimeSet = getIntervalDates(offers);

    int i = 0;
    for (LocalDateTime localDateTime : localDateTimeSet) {
      if (i != 0 && i != localDateTimeSet.size() - 1) {
        OfferValidity previousOfferValidity = offerValidities.get(offerValidities.size() - 1);

        previousOfferValidity.setEndDate(localDateTime.minusSeconds(1L));
      }

      if (i == localDateTimeSet.size() - 1) {
        OfferValidity previousOfferValidity = offerValidities.get(offerValidities.size() - 1);

        previousOfferValidity.setEndDate(localDateTime);
      }

      if (i != localDateTimeSet.size() - 1) {
        Offer validOffer = getValidOffer(offers, localDateTime);
        OfferValidity offerValidity = new OfferValidity();

        offerValidity.setStartDate(localDateTime);
        offerValidity.setPrice(
            validOffer != null ? validOffer.getPrice() : BigDecimal.valueOf(-1L));
        offerValidity.setCurrencyIso(validOffer != null ? validOffer.getCurrencyIso() : EUR);

        offerValidities.add(offerValidity);
        i++;
      }
    }

    return joinCommonIntervals(offerValidities);
  }

  private Set<LocalDateTime> getIntervalDates(List<Offer> offers) {
    Set<LocalDateTime> localDateTimeSet = new TreeSet<>();

    offers.stream().forEach(offer -> {
      localDateTimeSet.add(offer.getStartDate());
      localDateTimeSet.add(offer.getEndDate());
    });

    return localDateTimeSet;
  }

  private Offer getValidOffer(List<Offer> offers, LocalDateTime localDateTime) {
    Offer validOffer = null;

    for (Offer offer : offers) {
      if (isBetweenRightOpenInterval(localDateTime, offer.getStartDate(), offer.getEndDate())) {
        if (validOffer != null) {
          if (offer.getPriority() > validOffer.getPriority()) {
            validOffer = offer;
          }
        } else {
          validOffer = offer;
        }
      }
    }

    return validOffer;

  }

  private List<OfferValidity> joinCommonIntervals(List<OfferValidity> offers) {

    List<OfferValidity> offerValidities = new ArrayList<>();
    offerValidities.add(offers.get(0));

    for (OfferValidity offerValidity : offers) {
      OfferValidity previousOfferValidity = offerValidities.get(offerValidities.size() - 1);

      if (previousOfferValidity.getPrice().equals(offerValidity.getPrice())) {
        if (offerValidity.getEndDate() != null) {
          previousOfferValidity.setEndDate(offerValidity.getEndDate());
        }
      } else {
        offerValidities.add(offerValidity);
      }
    }

    return offerValidities;

  }

}
