package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Offer;
import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequestDto {

    private Double price;


}
