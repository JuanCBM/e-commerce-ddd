package com.inditex.hiring.infraestructure.repository.h2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataJPAOfferRepository extends JpaRepository<OfferEntity, Long> {

  @Query(value = "SELECT o FROM OfferEntity o WHERE o.brandId = :brandId and o.productPartnumber = :partNumber")
  List<OfferEntity> findByBrandIdAndPartNumber(@Param("brandId") Integer brandId,
      @Param("partNumber") String partNumber);
}
