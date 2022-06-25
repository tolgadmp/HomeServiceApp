package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Address;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    private String address;
    private String distinc;
    private String city;

    public static Address mapRequestDtoToEntity(AddressRequestDto requestDto){
        return Address.builder()
                .address(requestDto.getAddress())
                .distinc(requestDto.getDistinc())
                .city(requestDto.getCity())
                .build();
    }

}
