package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private List<AddressResponseDto> addresses;

    public static UserResponseDto mapEntityToResponseDto(User user){
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .addresses(new ArrayList<>())
                .build();
    }

}
