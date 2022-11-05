package com.inditex.hiring.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OfferValidity {

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private BigDecimal price;

  private String currencyIso;

  public OfferValidity() {
    super();
  }

  public OfferValidity(LocalDateTime startDate, LocalDateTime endDate, BigDecimal price,
      String currencyIso) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.currencyIso = currencyIso;
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
