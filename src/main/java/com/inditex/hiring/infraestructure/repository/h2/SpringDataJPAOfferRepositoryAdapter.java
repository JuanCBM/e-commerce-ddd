package com.inditex.hiring.infraestructure.repository.h2;

import com.inditex.hiring.domain.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.infraestructure.exception.NoSuchResourceFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SpringDataJPAOfferRepositoryAdapter implements OfferRepository {

  private final SpringDataJPAOfferRepository offerRepository;

  public SpringDataJPAOfferRepositoryAdapter(
      SpringDataJPAOfferRepository offerRepository) {

    this.offerRepository = offerRepository;
  }

  @Override
  public Offer getById(Long id) {
    return offerRepository.findById(id).orElseThrow(NoSuchResourceFoundException::new).toOffer();
  }

  @Override
  public List<Offer> getAll() {
    return offerRepository.findAll().stream().map(OfferEntity::toOffer)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteAll() {
    offerRepository.deleteAll();
  }

  @Override
  public void deleteById(Long id) {
    offerRepository.deleteById(id);
  }

  @Override
  public void create(Offer offer) {
    offerRepository.save(new OfferEntity(offer));
  }

  @Override
  public List<Offer> getByPartNumber(Integer brandId, String partNumber) {
    return offerRepository.findByBrandIdAndPartNumber(brandId, partNumber).stream()
        .map(OfferEntity::toOffer)
        .collect(Collectors.toList());
  }
}
