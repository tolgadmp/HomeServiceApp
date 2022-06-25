package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.Offer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponseDto {

    private Double price;
    private long jobId;

    public static OfferResponseDto mapEntityToResponseDto(Offer offer){
        return OfferResponseDto
                .builder()
                .price(offer.getPrice())
                .jobId(offer.getJob().getId())
                .build();
    }

}
