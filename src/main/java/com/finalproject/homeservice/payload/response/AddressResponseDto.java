package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.Address;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private String address;
    private String distinc;
    private String city;

    public static AddressResponseDto mapEntityToResponseDto(Address address){
        return AddressResponseDto
                .builder()
                .address(address.getAddress())
                .distinc(address.getDistinc())
                .city(address.getCity())
                .build();
    }
}
