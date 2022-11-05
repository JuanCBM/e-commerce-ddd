package com.inditex.hiring.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Offer {

  private Long offerId;

  private Integer brandId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Long priceListId;

  private String productPartnumber;

  private Integer priority;

  private BigDecimal price;

  private String currencyIso;

  public Offer() {
    super();
  }

  public Offer(Long offerId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate,
      Long priceListId,
      String productPartnumber,
      Integer priority, BigDecimal price, String currencyIso) {

    this.offerId = offerId;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceListId = priceListId;
    this.productPartnumber = productPartnumber;
    this.priority = priority;
    this.price = price;
    this.currencyIso = currencyIso;
  }


  public Long getOfferId() {
    return offerId;
  }

  public void setOfferId(Long offerId) {
    this.offerId = offerId;
  }

  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public Long getPriceListId() {
    return priceListId;
  }

  public void setPriceListId(Long priceListId) {
    this.priceListId = priceListId;
  }

  public String getProductPartnumber() {
    return productPartnumber;
  }

  public void setProductPartnumber(String productPartnumber) {
    this.productPartnumber = productPartnumber;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getCurrencyIso() {
    return currencyIso;
  }

  public void setCurrencyIso(String currencyIso) {
    this.currencyIso = currencyIso;
  }

}
