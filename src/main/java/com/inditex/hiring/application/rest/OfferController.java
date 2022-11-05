package com.inditex.hiring.application.rest;

import com.inditex.hiring.application.request.OfferRequestDTO;
import com.inditex.hiring.application.response.OfferByPartNumberResponseDTO;
import com.inditex.hiring.application.response.OfferResponseDTO;
import com.inditex.hiring.domain.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * You can change this controller but please do not change ends points signatures & payloads.
 */
@RestController
public class OfferController {

  private final OfferService offerService;

  public OfferController(OfferService offerService) {
    this.offerService = offerService;
  }

  @Operation(summary = "Create new offer")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Offer created"
      ),
  })
  @PostMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewOffer(@RequestBody @Valid OfferRequestDTO offerRequestDTO) {
    offerService.create(offerRequestDTO.toOffer());
  }

  @Operation(summary = "Delete all offers")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Offers deleted")
  }
  )
  @DeleteMapping(value = "/offer")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllOffers() {
    offerService.deleteAll();
  }

  @Operation(summary = "Delete an offer by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Offer deleted")
  }
  )
  @DeleteMapping(value = "/offer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteOfferById(@PathVariable Long id) {
    offerService.deleteById(id);
  }

  @Operation(summary = "Get all the offers")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Offers found",
          content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = OfferResponseDTO.class))
              )
          }),
  })
  @GetMapping(value = "/offer")
  @ResponseStatus(HttpStatus.OK)
  public List<OfferResponseDTO> getAllOffers() {
    return offerService.getAll().stream().map(OfferResponseDTO::new)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Get offer by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Offer found",
          content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = OfferResponseDTO.class))
              )
          }),
      @ApiResponse(responseCode = "404", description = "Offer not found",
          content = @Content)
  })
  @GetMapping(value = "/offer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public OfferResponseDTO getOfferById(@PathVariable Long id) {
    return new OfferResponseDTO(offerService.getById(id));
  }

  @Operation(summary = "Get offers by brandId and partnumber")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Offers found",
          content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = OfferByPartNumberResponseDTO.class))
              )
          }),
  })
  @GetMapping(value = "brand/{brandId}/partnumber/{partnumber}/offer", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<OfferByPartNumberResponseDTO> getOfferByPartNumber(@PathVariable Integer brandId,
      @PathVariable String partnumber) {
    return offerService.getByPartNumber(brandId, partnumber)
        .stream()
        .map(OfferByPartNumberResponseDTO::new)
        .collect(Collectors.toList());
  }

}
