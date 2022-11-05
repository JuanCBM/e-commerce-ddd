package com.inditex.hiring.domain.repository;

import com.inditex.hiring.domain.Offer;
import java.util.List;

public interface OfferRepository {

  Offer getById(Long id);

  List<Offer> getAll();

  void deleteAll();

  void deleteById(Long id);

  void create(Offer offer);

  List<Offer> getByPartNumber(Integer brandId, String partNumber);

}
