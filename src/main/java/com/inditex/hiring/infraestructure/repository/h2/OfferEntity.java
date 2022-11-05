package com.inditex.hiring.infraestructure.repository.h2;

import com.inditex.hiring.domain.Offer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OfferEntity extends EntityBase<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long offerId;

  private Integer brandId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Long priceListId;

  private String productPartnumber;

  private Integer priority;

  private BigDecimal price;

  private String currencyIso;

  public OfferEntity(Offer offer) {
    this.offerId = offer.getOfferId();
    this.brandId = offer.getBrandId();
    this.startDate = offer.getStartDate();
    this.endDate = offer.getEndDate();
    this.priceListId = offer.getPriceListId();
    this.productPartnumber = offer.getProductPartnumber();
    this.priority = offer.getPriority();
    this.price = offer.getPrice();
    this.currencyIso = offer.getCurrencyIso();
  }

  public Offer toOffer() {
    Offer offer = new Offer();
    offer.setOfferId(this.offerId);
    offer.setBrandId(this.brandId);
    offer.setStartDate(this.startDate);
    offer.setEndDate(this.endDate);
    offer.setPriceListId(this.priceListId);
    offer.setProductPartnumber(this.productPartnumber);
    offer.setPriority(this.priority);
    offer.setPrice(this.price);
    offer.setCurrencyIso(this.currencyIso);

    return offer;
  }

}
