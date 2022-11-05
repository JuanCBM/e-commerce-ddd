package com.inditex.hiring.domain.service;

import com.inditex.hiring.domain.Offer;
import com.inditex.hiring.domain.OfferValidity;
import java.util.List;

public interface OfferService {

  void create(Offer offer);

  void deleteAll();

  void deleteById(Long id);

  List<Offer> getAll();

  Offer getById(Long id);

  List<OfferValidity> getByPartNumber(Integer brandId, String partnumber);

}
