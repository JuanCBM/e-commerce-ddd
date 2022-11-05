package com.inditex.hiring.infraestructure.configuration;

import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.service.OfferService;
import com.inditex.hiring.domain.service.impl.DomainOfferServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  OfferService orderService(final OfferRepository orderRepository) {
    return new DomainOfferServiceImpl(orderRepository);
  }
}
